package cn.wyt;

import java.util.concurrent.Callable;

public class TaskCallable implements Callable<Integer> {

    private static int fibo(int a) {
        if ( a < 2)
            return 1;

        return fibo(a-1) + fibo(a-2);

    }


    @Override
    public Integer call() throws Exception {
        System.out.println("run开始执行");
        Integer result =  fibo(36);
        System.out.println("run开始结束");
        return result;

    }
}
