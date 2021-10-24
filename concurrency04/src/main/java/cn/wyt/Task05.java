package cn.wyt;

import java.util.concurrent.CountDownLatch;

public class Task05 implements Runnable {
    private CountDownLatch countDownLatch;
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>(); //为什么获取不到值
    private static Integer result;
    public Task05(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }


    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        result = fibo(a-1) + fibo(a-2);
        return result;

    }

    @Override
    public void run() {
        System.out.println("run开始执行");
       // threadLocal.set(fibo(36));
        fibo(36);
        countDownLatch.countDown();

    }


    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
