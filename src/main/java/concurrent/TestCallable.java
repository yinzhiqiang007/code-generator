package concurrent;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.concurrent.Callable;

public class TestCallable implements Callable {

    private int a;
    private Map<String,Integer> map;

    public  TestCallable(int i){
        a=i;
    }

    public  TestCallable(int i, Map<String,Integer> map){
        a=i;
        this.map = map;
    }
    @Override
    public Object call() throws Exception {

        if(a==2){
        }
        Thread.sleep(a*1000);
//        System.out.println(JSON.toJSONString(map));

        try {
//            System.out.println(1/0);
//            System.out.println(1111);
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put(a+"",a);

        System.out.println(JSON.toJSONString(map));

//        System.out.println(ThreadTest.currentThread().getName()+" run ..."+a);
        CountDownLatchTest.countDownLatch.countDown();
        return null;
    }
}
