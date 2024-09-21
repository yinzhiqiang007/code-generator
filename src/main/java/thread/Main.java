package thread;

import concurrent.ThreadPoolUtil;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Main {
    public static void main1(String[] args) throws ExecutionException, InterruptedException {
//        Future future1 = ThreadPoolUtil.threadPool.submit(new TestCallable(1) );
//        Future future2 = ThreadPoolUtil.threadPool.submit(new TestCallable(2) );
//        System.out.println(future1.get());
        int i = 1;
        int a = 1;
        int s = 1;
        int ii = 1;

        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i);
//        System.out.println(ii++);
//        System.out.println(++i);
        s = s++;
        a = ++a;
        System.out.println(s);
        System.out.println(a);
    }

    volatile static Thread t0, t1, t2;

    public static void main(String[] args) throws InterruptedException {
        //使用join
        useJoin();
        t0.start();

        t1.start();
        t2.start();
        t0.join();
        t1.join();
        t2.join();
        System.out.println("done");
    }

    //使用join
    private static void useJoin() {
        t0 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName() + "执行完成");
            }
        });
        t1 = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(2000L);
                System.out.println(Thread.currentThread().getName() + "执行完成");
            }
        });
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "执行完成");
            }
        });

    }
}
