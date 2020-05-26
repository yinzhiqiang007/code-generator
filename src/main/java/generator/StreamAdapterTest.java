package generator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class StreamAdapterTest {

    public final static int GET = 0;
    public final static int SET = 1;
    public final static int RESP = 2;
    public final static int POST = 3;
    public final static int ACK = 4;


    /**
     * type_id_t start
     **/
    public final static int TYPE_ID_string_t = 0;
    public final static int TYPE_ID_bool_t = 1;
    public final static int TYPE_ID_int8_t = 2;
    public final static int TYPE_ID_int16_t = 3;
    public final static int TYPE_ID_int32_t = 4;
    public final static int TYPE_ID_int64_t = 5;
    public final static int TYPE_ID_uint8_t = 6;
    public final static int TYPE_ID_uint16_t = 7;
    public final static int TYPE_ID_uint32_t = 8;
    public final static int TYPE_ID_uint64_t = 9;
    public final static int TYPE_ID_float32_t = 10;
    public final static int TYPE_ID_float64_t = 11;
    /** type_id_t end **/

    /**
     * length_size_t start 值byte[] 长度
     **/
    public final static int LENGTH_SIZE_NIL = 0;
    public final static int LENGTH_SIZE_UINT8 = 1;
    public final static int LENGTH_SIZE_UINT16 = 2;
    public final static int LENGTH_SIZE_UINT32 = 3;

    /**
     * length_size_t end
     **/


    public enum TYPE_ID_T {
        TYPE_ID_string_t(0),
        TYPE_ID_bool_t(1),
        TYPE_ID_int8_t(2),
        TYPE_ID_int16_t(3),
        TYPE_ID_int32_t(4),
        TYPE_ID_int64_t(5),
        TYPE_ID_uint8_t(6),
        TYPE_ID_uint16_t(7),
        TYPE_ID_uint32_t(8),
        TYPE_ID_uint64_t(9),
        TYPE_ID_float32_t(10),
        TYPE_ID_float64_t(11);

        private int code;

        TYPE_ID_T(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public enum LENGTH_SIZE_T {
        LENGTH_SIZE_NIL(0),
        LENGTH_SIZE_UINT8(1),
        LENGTH_SIZE_UINT16(2),
        LENGTH_SIZE_UINT32(3);

        private int code;

        LENGTH_SIZE_T(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public enum VALUE_TYPE {
        BYTE(1),
        SHORT(2),
        INT(4),
        LONG(8);

        private int code;

        VALUE_TYPE(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static void main1(String[] args) {
        //Original String
        String string = "hello world";
        //Convert to byte[]
        byte[] bytes = string.getBytes();


        byte[] bytes1 = new byte[]{0, 4};


        //Convert back to String
        String s = new String(bytes);
        String s1 = new String(bytes1);

        //Check converted string against original String
        System.out.println("Decoded String : " + s);
        System.out.println("Decoded String : " + s1);
        System.out.println(Utility.byte2Short(bytes1));

    }


    public static void main(String[] args) {
        byte b = 1;
        byte[] bytes2 = new byte[]{0, -96};
        System.out.println(Utility.byte2Short(bytes2));

        byte[] bytes3 = Utility.short2Byte((short) 26641);
        System.out.println(bytes3[0] + "," + bytes3[1]);


//        BigInteger bbi = new BigInteger(String.valueOf("11111111111111111111111111111111"), 2);
        BigInteger bbi = new BigInteger(String.valueOf("1111111111111111"), 2);
//        BigInteger bbi = new BigInteger(bytes2);
        System.out.println(bbi.toString());


    }

    public static void main3(String[] args) throws IOException {


//        byte[] bytes = new byte[]{3, 2, 0, 3,
//                01, -115, 4, 104, 17, 111, 86,
//                02, 12, 1, 2,
//        };
//
//        byte[] bytes = new byte[]{3, 2, 3, 3,
//                01, -115,  4, 104, 17, 111, 86};

        String path = "E:\\xiaoyi\\万物互联\\file3\\get_color_flash.dat";
        File file = new File(path);
        System.out.println(file.length());
        FileInputStream in1 = new FileInputStream(file);
        DataInputStream data_in = new DataInputStream(in1);
        byte[] itemBuf = new byte[data_in.available()];
        data_in.read(itemBuf);

        System.out.print("[");
        for(byte bb:itemBuf){
            System.out.print(bb+",");
        }
        System.out.println("]");

        String json = handlerStream2Json(itemBuf);


//        String json = "{\"module\":3,\"cmd\":3,\"fields\":[{\"typeId\":3,\"id\":6,\"value\":[4463,22016]}],\"version\":3,\"seq\":2}";
//        String json = "{\"module\":3,\"cmd\":3,\"fields\":[{\"typeId\":5,\"id\":6,\"value\":[317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737,317522943611636737]}],\"version\":3,\"seq\":2}";
        Map<String, Object> paramMap = JSON.parseObject(json);
        List<Byte> byteList = handlerJson2Stream(paramMap);
        String s = JSON.toJSONString(byteList);

        System.out.println(s);
    }

    /**
     * 获取消息体单个值占用byte数量
     *
     * @param typeId
     * @return
     */
    private static int getValueByteSize(int typeId) {
        int valueByteSize = 1;
        switch (typeId) {
            case TYPE_ID_string_t:
            case TYPE_ID_bool_t:
            case TYPE_ID_int8_t:
            case TYPE_ID_uint8_t:
                valueByteSize = VALUE_TYPE.BYTE.code;
                break;
            case TYPE_ID_int16_t:
            case TYPE_ID_uint16_t:
                valueByteSize = VALUE_TYPE.SHORT.code;
                break;
            case TYPE_ID_int32_t:
            case TYPE_ID_uint32_t:
            case TYPE_ID_float32_t:
                valueByteSize = VALUE_TYPE.INT.code;
                break;
            case TYPE_ID_int64_t:
            case TYPE_ID_uint64_t:
            case TYPE_ID_float64_t:
                valueByteSize = VALUE_TYPE.LONG.code;
                break;
            default:
                break;
        }
        return valueByteSize;
    }

    /**
     * 根据valueByteSize将byte数组的值转换成10进制
     * valueByteSize ： 1=byte类型，2=short类型，4=int类型，8=long类型
     *
     * @param valueArr
     * @param valueByteSize
     * @return
     */
    private static List getValue(byte[] valueArr, int valueByteSize) {
        List shortList = new ArrayList();
        for (int i = 0; i < valueArr.length; ) {
            Object value = null;
            if (valueByteSize == VALUE_TYPE.BYTE.code) {
                value = i;
            } else if (valueByteSize == VALUE_TYPE.SHORT.code) {
                value = Utility.byte2Short(valueArr, i);
            } else if (valueByteSize == VALUE_TYPE.INT.code) {
                value = Utility.byte2Int(valueArr, i);
            } else if (valueByteSize == VALUE_TYPE.LONG.code) {
                value = Utility.byte2Long(valueArr, i);
            }
            shortList.add(value);
            i = i + valueByteSize;
        }
        return shortList;
    }


    /**
     * byte[0-3] 消息头
     * byte[4-n] 消息体
     * 0-9=tag
     * 10-13=typeId
     * 14-15=lengthSize
     *
     * @param bytes
     * @return
     */
    private static List<Map<String, Object>> handlerStreamParam2Json(List<Map<String, Object>> dataList, byte[] bytes, int index) {
        if (null == dataList) {
            dataList = new ArrayList<Map<String, Object>>();
        }

        short tag = Utility.byte2Short(bytes, index);
        byte property = bytes[index + 1];
        int lengthSize = property & 3;
        int typeId = property >> 2 & 15;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", tag >> 6);
        paramMap.put("typeId", typeId);


        int begin = index + 2;
        int last;
        int valueByteSize = getValueByteSize(typeId);// 获取每个值所占用的字节数

        if (LENGTH_SIZE_NIL == lengthSize) {
            if (valueByteSize == VALUE_TYPE.BYTE.code) {
                paramMap.put("value", bytes[begin]);
            } else if (valueByteSize == VALUE_TYPE.SHORT.code) {
                paramMap.put("value", Utility.byte2Short(bytes, begin));
            } else if (valueByteSize == VALUE_TYPE.INT.code) {
                paramMap.put("value", Utility.byte2Int(bytes, begin));
            } else if (valueByteSize == VALUE_TYPE.LONG.code) {
                paramMap.put("value", Utility.byte2Long(bytes, begin));
            }
            last = begin + valueByteSize;
        } else {
            int length = 0;
            int beginIndex = 0;
            if (lengthSize == LENGTH_SIZE_UINT8) {
                length = bytes[begin];
                beginIndex = 1;
            } else if (lengthSize == LENGTH_SIZE_UINT16) {
                length = (int) Utility.byte2Short(bytes, begin);
                beginIndex = 2;
            } else if (lengthSize == LENGTH_SIZE_UINT32) {
                length = Utility.byte2Int(bytes, begin);
                beginIndex = 4;
            }

            int valueBegin = begin + beginIndex;
            int valueEnd = valueBegin + length;
            last = valueEnd;
            byte[] valueArr = Arrays.copyOfRange(bytes, valueBegin, valueEnd);
            paramMap.put("value", getValue(valueArr, valueByteSize));

        }
        dataList.add(paramMap);
        if (last + 3 <= bytes.length) {
            handlerStreamParam2Json(dataList, bytes, last);
        }
        return dataList;
    }

    /**
     * 流转json
     *
     * @param bytes
     * @return
     */
    public static String handlerStream2Json(byte[] bytes) {
        if (bytes.length < 4) {
            throw new RuntimeException("data exception");
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("cmd", (int) bytes[0] & 15);//请求类型
        resultMap.put("seq", (int) bytes[1]);//序号
        resultMap.put("fid", Utility.byte2Short(bytes, 2));//功能id
        resultMap.put("ver", bytes[0] >> 4);//版本


        int cmd = bytes[0] & 15;
        int paraIndex = 4;
        switch (cmd) {
            case SET:
            case RESP:
            case POST:
                resultMap.put("fields", handlerStreamParam2Json(null, bytes, paraIndex));
                break;
            case ACK:
                resultMap.put("error", (int) bytes[4]);//异常
                break;
            default:
                break;
        }
        String result = JSON.toJSONString(resultMap);
        System.out.println(result);
        return result;
    }

    /**
     * json转二进制
     *
     * @param paramMap
     * @return
     */
    public static List<Byte> handlerJson2Stream(Map<String, Object> paramMap) {
        if (null == paramMap || paramMap.size() < 4) {
            throw new RuntimeException("data exception");
        }
        List<Byte> byteList = new ArrayList<Byte>();

        int cmd = Integer.parseInt(paramMap.get("cmd").toString());
        int var = Integer.parseInt(paramMap.get("ver").toString());

        int c = var << 4;
        int byte0 = c | cmd;  // 版本+命令id

        byteList.add((byte) byte0);
        byteList.add(Byte.valueOf(paramMap.get("seq").toString()));//序号
        byte[] fid = Utility.short2Byte(Short.parseShort((paramMap.get("fid").toString())));
        concatByte(byteList, fid);

        switch (cmd) {
            case SET:
            case RESP:
            case POST:
                List<Byte> result = handlerJsonParam2Stream((List<Map<String, Object>>) paramMap.get("fields"));
                byteList.addAll(result);
                break;
            default:
                break;
        }
        outputStream(byteList);
        return byteList;
    }




    private static List<Byte> handlerJsonParam2Stream(List<Map<String, Object>> dataList) {
        List<Byte> byteList = new ArrayList<Byte>();
        for (Map<String, Object> map : dataList) {
            int propertyId = Integer.parseInt(map.get("id").toString());
            int typeId = Integer.parseInt(map.get("typeId").toString());
            int lengthSize = 0;
            int valueByteSize = getValueByteSize(typeId);
            if (map.get("value") instanceof Integer || map.get("value") instanceof Long || map.get("value") instanceof Short) {
                lengthSize = LENGTH_SIZE_NIL;
            } else if (map.get("value") instanceof JSONArray) {
                List<Integer> valueArr = (List<Integer>) map.get("value");

                int lengthValue = valueArr.size() * valueByteSize;
                if (lengthValue < (Byte.MAX_VALUE - Byte.MIN_VALUE)) {
                    lengthSize = LENGTH_SIZE_UINT8;
                } else if (lengthValue < (Short.MAX_VALUE - Short.MIN_VALUE)) {
                    lengthSize = LENGTH_SIZE_UINT16;
                } else {
                    lengthSize = LENGTH_SIZE_UINT32;
                }
            }
            int p1 = propertyId << 6;
            int p2 = typeId << 2;
            int p3 = p1 | p2;
            int p4 = p3 | lengthSize;

            byte[] tag = Utility.short2Byte((short) p4);
            concatByte(byteList, tag);


            if (lengthSize == LENGTH_SIZE_NIL) {
                // value
                if (valueByteSize == VALUE_TYPE.BYTE.code) {
                    byte value = Byte.parseByte(map.get("value").toString());
                    byteList.add(value);
                } else if (valueByteSize == VALUE_TYPE.SHORT.code) {
                    byte[] values = Utility.short2Byte(Short.parseShort(map.get("value").toString()));
                    concatByte(byteList, values);
                } else if (valueByteSize == VALUE_TYPE.INT.code) {
                    byte[] values = Utility.int2Byte(Integer.parseInt(map.get("value").toString()));
                    concatByte(byteList, values);
                } else if (valueByteSize == VALUE_TYPE.LONG.code) {
                    byte[] values = Utility.long2Byte(Long.parseLong(map.get("value").toString()));
                    concatByte(byteList, values);
                }
            } else {
                List<Long> valueArr = (List<Long>) map.get("value");

                List<Byte> valueList = new ArrayList<Byte>();
                for (Object v : valueArr) {
                    long i = Long.parseLong(String.valueOf(v));
                    if (valueByteSize == VALUE_TYPE.BYTE.code) {
                        valueList.add((byte) i);
                    } else if (valueByteSize == VALUE_TYPE.SHORT.code) {
                        byte[] values = Utility.short2Byte((short) i);
                        concatByte(valueList, values);
                    } else if (valueByteSize == VALUE_TYPE.INT.code) {
                        byte[] values = Utility.int2Byte((int) i);
                        concatByte(valueList, values);
                    } else if (valueByteSize == VALUE_TYPE.LONG.code) {
                        byte[] values = Utility.long2Byte(i);
                        concatByte(valueList, values);
                    }
                }
                //长度 lengthSize
                if (valueList.size() < (Byte.MAX_VALUE - Byte.MIN_VALUE)) {
                    byteList.add(Byte.parseByte(String.valueOf(valueList.size())));//长度 lengthSize
                } else if (valueList.size() < (Short.MAX_VALUE - Short.MIN_VALUE)) {
                    concatByte(byteList, Utility.short2Byte((short) valueList.size()));
                } else {
                    concatByte(byteList, Utility.int2Byte(valueList.size()));
                }
                //值
                byteList.addAll(valueList);// value
            }
        }
        return byteList;

    }

    private static void concatByte(List<Byte> byteList, byte[] bytes) {
        for (byte b : bytes) {
            byteList.add(b);
        }
    }

    private static void outputStream(List<Byte> byteList){
        DataOutputStream dos = null;
        byte[] b = new byte[byteList.size()];
        for(int i =0; i<byteList.size();i++){
            b[i] = byteList.get(i);
        }
        try {
            //创建输出流
            FileOutputStream fos = new FileOutputStream("E:\\xiaoyi\\万物互联\\file\\1111.dat");
            dos = new DataOutputStream(fos);
            dos.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (dos!=null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
