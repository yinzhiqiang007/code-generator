package concurrent;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Quinn
 * @date 2018/3/21
 * @package com.quinn.util
 */
public class ThreadPoolUtil {


    private static int corePoolSize = 3;
//    public static ExecutorService threadPool = Executors.newFixedThreadPool(3);

    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    public static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) throws InterruptedException {

        getThreadPoolInfo(threadPool);
        for (int i = 0; i < 20; i++) {
            getThreadPoolInfo(threadPool);
        }
        Thread.sleep(10000);
        getThreadPoolInfo(threadPool);


    }

    public static Map<String, Object> getThreadPoolInfo(ThreadPoolExecutor threadPool) {
        Map<String, Object> threadPoolMap = new HashMap<>();
        threadPoolMap.put("getPoolSize", threadPool.getPoolSize());
        threadPoolMap.put("getTaskCount", threadPool.getTaskCount());
        threadPoolMap.put("isShutdown", threadPool.isShutdown());
        threadPoolMap.put("getCorePoolSize", threadPool.getCorePoolSize());
        threadPoolMap.put("getMaximumPoolSize", threadPool.getMaximumPoolSize());
        threadPoolMap.put("getQueue_size", threadPool.getQueue().size());
        threadPoolMap.put("getActiveCount", threadPool.getActiveCount());
        threadPoolMap.put("getCompletedTaskCount", threadPool.getCompletedTaskCount());
        threadPoolMap.put("getLargestPoolSize", threadPool.getLargestPoolSize());
        System.out.println(JSON.toJSONString(threadPoolMap));
        return threadPoolMap;
    }

    public static Map<String, Object> getThreadPoolInfo() {
        Map<String, Object> m1 = getThreadPoolInfo(threadPool);
        Map<String, Object> all = new HashMap<>();
        all.put("threadPool", m1);
        return all;
    }


}
