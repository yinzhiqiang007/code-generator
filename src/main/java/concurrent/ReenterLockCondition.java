package concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();


    @Override
    public void run() {
        lock.lock();
        System.out.println("lock.lock");
        try {
            Thread.sleep(1000);

            condition.await();
            Thread.sleep(1000);
            System.out.println("线程继续执行");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            lock.unlock();
        }

    }



    public static void main(String[] args) throws InterruptedException {


        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        System.out.println(1);



        Thread.sleep(1000);

        System.out.println(2);
        lock.lock();

        System.out.println(3);
        condition.signal();
        lock.unlock();
        System.out.println(4);
    }
}
