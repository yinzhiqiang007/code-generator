package generator;

import java.io.*;

public class StreamFileTest {


    public static void main1(String[] args) throws IOException {
//        String path = "E:\\xiaoyi\\万物互联\\file\\set_color_flash.dat";
        String path = "E:\\xiaoyi\\万物互联\\file\\get_color_flash.dat";
        File file = new File(path);
        System.out.println(file.length());
        FileInputStream in1 = new FileInputStream(file);
        DataInputStream data_in = new DataInputStream(in1);

        byte[] itemBuf = new byte[data_in.available()];
        data_in.read(itemBuf);

        System.out.println("===================================");


        byte[] bytes1 = new byte[]{0,89};
        System.out.println(Utility.byte2Short(bytes1));

        byte[] bytes2 = new byte[]{0,-104};
        System.out.println(Utility.byte2Short(bytes2));

        System.out.println((byte)150);


        byte[] bytes = new byte[]{4, 104, 17, 111, 86, 02, 12, 1};

        System.out.println(Utility.byte2Long(bytes));




    }

    public static void main(String[] args) {

        DataOutputStream dos = null;
        try {
            //创建输出流
            FileOutputStream fos = new FileOutputStream("E:\\xiaoyi\\万物互联\\file\\1111.dat");
            dos = new DataOutputStream(fos);

            byte[] b = new byte[]{1,0,0,6,0,89,4,0,1,2,3,0,-104,20,0,-40,30,1,24,40};
            System.out.println(b.length);
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

