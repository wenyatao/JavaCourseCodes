package cn.wyt;

import java.util.concurrent.Callable;

public class TaskNotify implements Runnable {
    private Object object;


    public TaskNotify(Object object){
        this.object = object;
    }


    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);

    }

    @Override
    public void run() {
        System.out.println("run开始执行");
        synchronized (object){
            try {
                System.out.println("notify休眠2秒");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer result = fibo(36);
            Homework.threadLocal.set(result);
            System.out.println("开始通知");
            object.notify();
            System.out.println("通知结束");
        }

        System.out.println("run开始结束");

    }
}
