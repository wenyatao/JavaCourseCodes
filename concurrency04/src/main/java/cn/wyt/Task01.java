package cn.wyt;

public class Task01 implements Runnable{

    private static Integer result;

    @Override
    public void run() {
        System.out.println("run开始执行");
        fibo(36);
        System.out.println("run开始结束");
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;

        return  result = fibo(a-1) + fibo(a-2);

    }


    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
