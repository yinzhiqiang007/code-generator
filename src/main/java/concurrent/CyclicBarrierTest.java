package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏
 */
public class CyclicBarrierTest {
    static int n = 7;

    public static CyclicBarrier endExce(int nn){
        CyclicBarrier c = new CyclicBarrier(nn);
        return c;
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier c = endExce(n);

        for (int i = 1; i < n; i++) {
            new Writer(c).start();
        }
//        c.await();
        c.await();
        System.out.println("================1");

        c = endExce(2);
        new Writer(c).start();

        c.await();
        System.out.println("================2");
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                Thread.sleep(1000);      //以睡眠来模拟写入数据操作
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程写入完毕，继续处理其他任务...");
        }
    }

}
