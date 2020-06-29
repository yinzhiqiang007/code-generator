package test;

import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapTest {

    static Integer[] heap = new Integer[]{10, 7, 2, 5, 1, 3, 5};

    static int index = 0;
    static int level = 0;

    private static void leftIndex(int i) {
        index = 2 * i + 1;
        System.out.println("leftIndex:" + heap[index]);
        level++;

    }

    private static void rightIndex(int i) {
        index++;
        System.out.println("rightIndex:" + heap[index]);


    }


    public static void main(String[] args) {

//        do{
//            leftIndex(level);
//            rightIndex(level);
//        }while (index<heap.length-1);

        Queue<Integer> heap = new PriorityQueue<>(5,Comparator.reverseOrder());//最大堆

        heap.add(3);
        heap.add(435);
        heap.add(45);
        heap.add(34);
        heap.add(345);
//        heap.add(445);

        Integer[] aa = new Integer[heap.size()];

        heap.toArray(aa);

        System.out.println(heap.element());
        heap.remove();
        heap.remove();
        System.out.println(heap.element());
        System.out.println(JSON.toJSONString(aa));


    }
}
