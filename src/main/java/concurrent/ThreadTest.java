package concurrent;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class ThreadTest {


    //仓库最大容量
    private final int MAX_SIZE = 100;
    //仓库存储的载体
    private LinkedList list = new LinkedList();

    public static void main(String[] args) throws Exception {
        Queue queue = new PriorityQueue(Comparator.reverseOrder());


       int a=0;
       int b=0;
       int c=0;
       for(int i=0;i<10;i++){
           a=a++;
           b=++b;
       }
        System.out.println(a);
        System.out.println(c);
        System.out.println(b);


        int mid = (1 + 10 + 1) >>> 1;
        System.out.println(mid);



    }

    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length;j++){
                int t = nums[i]+nums[j];
                if(t==target){
                    arr[0]=i;
                    arr[1]=j;
                }

            }

        }
        return arr;

    }

}
