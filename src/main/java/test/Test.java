package test;

public class Test {


    public static void main(String[] args) {
        int height =0;//高度

        int maxSize = 1;//完全二叉堆最大数量
        int lenght =7;//当前二叉堆数量
        while (maxSize-1<lenght) {
            height++;
            maxSize = maxSize<<1;
        }
        System.out.println(height);
        System.out.println(maxSize-1);
        System.out.println(maxSize/2-1);
    }
}
