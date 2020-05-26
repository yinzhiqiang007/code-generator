package concurrent;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 计数器
 */
public class CountDownLatchTest {

    static CountDownLatch countDownLatch = new CountDownLatch(4);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Map<String,Integer> map = new HashMap<>();
        map.put("a",123);
        Map<String,Integer> map1 =map;
        map1.put("b",213);

        ThreadPoolUtil.threadPool.submit(new TestCallable(1,map) );
        ThreadPoolUtil.threadPool.submit(new TestCallable(3,map) );
        ThreadPoolUtil.threadPool.submit(new TestCallable(4,map) );
        ThreadPoolUtil.threadPool.submit(new TestCallable(2,map) );
//        System.out.println(future.get());

//        countDownLatch.await();
//        System.out.println(countDownLatch.getCount());
        System.out.println(JSON.toJSONString(map));
        System.out.println("=================");

        ThreadPoolUtil.threadPool.shutdown();






    }
}
