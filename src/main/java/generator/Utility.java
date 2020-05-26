package generator;

/**
 * 各基础类型与byte之间的转换
 *
 * @author shanl
 */
public class Utility {

    /**
     * 将short转成byte[2]
     *
     * @param a
     * @return
     */
    public static byte[] short2Byte(short a) {
        byte[] b = new byte[2];

        b[0] = (byte) (a >> 8);
        b[1] = (byte) (a);

        return b;
    }

    /**
     * 将short转成byte[2]
     *
     * @param a
     * @param b
     * @param offset b中的偏移量
     */
    public static void short2Byte(short a, byte[] b, int offset) {
        b[offset] = (byte) (a >> 8);
        b[offset + 1] = (byte) (a);
    }

    /**
     * 将byte[2]转换成short
     *
     * @param b
     * @return
     */
    public static short byte2Short(byte[] b) {
        return (short) (((b[0] & 0xff) << 8) | (b[1] & 0xff));
    }

    /**
     * 将byte[2]转换成short
     *
     * @param b
     * @param offset
     * @return
     */
    public static short byte2Short(byte[] b, int offset) {
        return (short) (((b[offset] & 0xff) << 8) | (b[offset + 1] & 0xff));
    }

    /**
     * long转byte[8]
     *
     * @param a
     * @param b
     * @param offset b的偏移量
     */
    public static void long2Byte(long a, byte[] b, int offset) {
        b[offset + 0] = (byte) (a >> 56);
        b[offset + 1] = (byte) (a >> 48);
        b[offset + 2] = (byte) (a >> 40);
        b[offset + 3] = (byte) (a >> 32);

        b[offset + 4] = (byte) (a >> 24);
        b[offset + 5] = (byte) (a >> 16);
        b[offset + 6] = (byte) (a >> 8);
        b[offset + 7] = (byte) (a);
    }

    /**
     * byte[8]转long
     *
     * @param b
     * @param offset b的偏移量
     * @return
     */
    public static long byte2Long(byte[] b, int offset) {
        return ((((long) b[offset + 0] & 0xff) << 56)
                | (((long) b[offset + 1] & 0xff) << 48)
                | (((long) b[offset + 2] & 0xff) << 40)
                | (((long) b[offset + 3] & 0xff) << 32)

                | (((long) b[offset + 4] & 0xff) << 24)
                | (((long) b[offset + 5] & 0xff) << 16)
                | (((long) b[offset + 6] & 0xff) << 8)
                | (((long) b[offset + 7] & 0xff) << 0));
    }

    /**
     * byte[8]转long
     *
     * @param b
     * @return
     */
    public static long byte2Long(byte[] b) {
        return
                ((b[0] & 0xff) << 56) |
                        ((b[1] & 0xff) << 48) |
                        ((b[2] & 0xff) << 40) |
                        ((b[3] & 0xff) << 32) |

                        ((b[4] & 0xff) << 24) |
                        ((b[5] & 0xff) << 16) |
                        ((b[6] & 0xff) << 8) |
                        (b[7] & 0xff);
    }

    /**
     * long转byte[8]
     *
     * @param a
     * @return
     */
    public static byte[] long2Byte(long a) {
        byte[] b = new byte[4 * 2];

        b[0] = (byte) (a >> 56);
        b[1] = (byte) (a >> 48);
        b[2] = (byte) (a >> 40);
        b[3] = (byte) (a >> 32);

        b[4] = (byte) (a >> 24);
        b[5] = (byte) (a >> 16);
        b[6] = (byte) (a >> 8);
        b[7] = (byte) (a >> 0);

        return b;
    }

    /**
     * byte数组转int
     *
     * @param b
     * @return
     */
    public static int byte2Int(byte[] b) {
        return ((b[0] & 0xff) << 24) | ((b[1] & 0xff) << 16)
                | ((b[2] & 0xff) << 8) | (b[3] & 0xff);
    }

    /**
     * byte数组转int
     *
     * @param b
     * @param offset
     * @return
     */
    public static int byte2Int(byte[] b, int offset) {
        return ((b[offset++] & 0xff) << 24) | ((b[offset++] & 0xff) << 16)
                | ((b[offset++] & 0xff) << 8) | (b[offset++] & 0xff);
    }

    /**
     * int转byte数组
     *
     * @param a
     * @return
     */
    public static byte[] int2Byte(int a) {
        byte[] b = new byte[4];
        b[0] = (byte) (a >> 24);
        b[1] = (byte) (a >> 16);
        b[2] = (byte) (a >> 8);
        b[3] = (byte) (a);

        return b;
    }

    /**
     * int转byte数组
     *
     * @param a
     * @param b
     * @param offset
     * @return
     */
    public static void int2Byte(int a, byte[] b, int offset) {
        b[offset++] = (byte) (a >> 24);
        b[offset++] = (byte) (a >> 16);
        b[offset++] = (byte) (a >> 8);
        b[offset++] = (byte) (a);
    }

    //这个函数将float转换成byte[]
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }
        return dest;
    }


    //这个函数将byte转换成float
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    public static void main(String[] args) {
        boolean b = true;

    }

}
