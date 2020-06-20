package concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 信号量
 */
public class SemaphoreTest implements java.io.Serializable  {

    private int a = 0;

    /**
     * 银行存钱类
     */
    class Bank {
        private int account = 100;

        public int getAccount() {
            return account;
        }

        public void save(int money) {
            account += money;
        }
    }

    /**
     * 线程执行类，每次存10块钱
     */
    class NewThread implements Runnable {
        private Bank bank;
        private Semaphore semaphore;

        public NewThread(Bank bank, Semaphore semaphore) {
            this.bank = bank;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            int b = a++;
            if (semaphore.availablePermits() > 0) {
                System.out.println("线程" + b + "启动，进入银行,有位置立即去存钱");
            } else {
                System.out.println("线程" + b + "启动，进入银行,无位置，去排队等待等待");
            }
            try {
                semaphore.acquire();
                bank.save(10);
                System.out.println(b + "账户余额为：" + bank.getAccount());
                Thread.sleep(3000);
                System.out.println("线程" + b + "存钱完毕，离开银行");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 建立线程，调用内部类，开始存钱
     */
    public void useThread() throws InterruptedException {
        Bank bank = new Bank();
        // 定义10个新号量
        Semaphore semaphore = new Semaphore(2);
        System.out.println(semaphore.availablePermits());
        // 建立一个缓存线程池


        // 建立20个线程
        for (int i = 0; i < 10; i++) {
            // 执行一个线程
            ThreadPoolUtil.threadPool.submit(new Thread(new NewThread(bank, semaphore)));

        }

        // 关闭线程池
//        es.shutdown();

//        ThreadTest.sleep(1000);
        // 从信号量中获取两个许可，并且在获得许可之前，一直将线程阻塞
//        semaphore.acquireUninterruptibly();
        System.out.println("到点了，工作人员要吃饭了");
//         释放两个许可，并将其返回给信号量
//        semaphore.release(2);
        System.out.println(semaphore.getQueueLength());



    }



    public static Unsafe createUnsafe() {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static int mark = 4;
    static void calc(int sum1,int die){

        int i = 60/15;
        int sum2 = 0 ;
        if(sum1%i==0){
            sum2 = sum1/i;
        }else{
            sum2 = sum1/i+1;
        }
        mark--;
        if(mark==0){
            System.out.println("sum2:"+sum2);
        }
        System.out.println("m:"+sum2);
        die++;
//        System.out.println(number+"&&"+sum2);
        if(i>=sum2+1){
            System.out.println(i+"&&&"+sum2);
        }
        if(die==4){
            System.out.println(die+"&"+sum2);
        }
        System.out.println(die);
        if(sum2>1){
            calc(sum2,die);
        }


    }



    public static void main(String[] args)  {
        calc(2000,0);




    }
}
