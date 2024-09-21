package test;

import java.util.Collections;
import java.util.Comparator;

@java.lang.FunctionalInterface
public interface FunctionalInterface<T> {

    void test();



    boolean equals(Object obj);


    public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
        return Collections.reverseOrder();
    }

    public static void reverseOrder1() {
        System.out.println("this is fuck");
    }

}
