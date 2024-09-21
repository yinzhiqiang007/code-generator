package test;



import java.util.*;

public class Test1 {


    public static void main(String[] args) {
        int[] a = new int[]{5,3,1,3,2,3};
        System.out.println(solution(a));
    }

    public static void calc(Map<Integer, Integer> map, Integer key) {
        if (map.get(key) == null) {
            map.put(key, 1);
        } else {
            map.put(key, map.get(key) + 1);
        }

    }

    public static int solution(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (i + 1 < a.length) {
                int key = a[i] + a[i + 1];
                calc(map, key);
            }
        }
        List<Integer[]> list = new ArrayList<>();
        Set<Integer> keySet = map.keySet();
        for (Integer key : keySet) {
            if (list.size() == 0) {
                Integer[] arr = new Integer[]{key, map.get(key)};
                list.add(arr);
            } else {
                Integer[] arr = list.get(0);
                if (arr[1] < map.get(key)) {
                    list.clear();
                    list.add(new Integer[]{key, map.get(key)});
                } else if (arr[1] == map.get(key)) {
                    list.add(new Integer[]{key, map.get(key)});
                }
            }
        }

        int count1 = 0;
        int count2 = 0;
        count1 = count1(a,list.get(0)[0]);
        if(list.size()>1){
            count2 = count1(a,list.get(1)[0]);
        }
        if(count1<count2){
            count1 =count2;
        }


        return count1;

    }

    public static Integer count1(int[] a, Integer key) {

        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if(i+1<a.length){
                if(a[i]+a[i+1]==key){
                    c=c+1;
                    i=i+1;
                }
            }
        }
        return c;
    }


}
