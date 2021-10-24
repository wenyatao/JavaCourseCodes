package cn.wyt;

import java.util.concurrent.*;

public class Homework {

    public static  InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        test06();
    }

    /**
     * 主线程等待
     * @throws InterruptedException
     */
    public static void test01() throws InterruptedException {
        long start=System.currentTimeMillis();
        Task01 task01 = new Task01();
        new Thread(task01).start();
        while (task01.getResult() == null){
            Thread.sleep(1000);
        }
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+task01.getResult());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程

    }

    /**
     * join
     * @throws InterruptedException
     */
    public static void test02() throws InterruptedException {
        long start=System.currentTimeMillis();
        System.out.println("开始时间："+ start + " ms");
        Task01 task01 = new Task01();
        Thread thread = new Thread(task01);
        thread.start();
        thread.join();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+task01.getResult());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程

    }

    /**
     * 实现Callable接口 + FutureTask
     * @throws InterruptedException
     */
    public static void test03() throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        System.out.println("开始时间："+ start + " ms");
        TaskCallable taskCallable = new TaskCallable();
        FutureTask futureTask = new FutureTask(taskCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        if(!futureTask.isDone()) System.out.println("task执行中");
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+futureTask.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

    }

    /**
     * 实现Callable接口 + future+ 线程池
     * @throws InterruptedException
     */
    public static void test04() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long start=System.currentTimeMillis();
        System.out.println("开始时间："+ start + " ms");
        TaskCallable taskCallable = new TaskCallable();
        Future future = executorService.submit(taskCallable);
        if(!future.isDone()) System.out.println("task执行中");
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+future.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

    }

    /**
     * wait,notify
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void test05() throws InterruptedException, ExecutionException {
        final Object object = new Object();
        long start=System.currentTimeMillis();
        System.out.println("开始时间："+ start + " ms");

        TaskNotify taskNotify = new TaskNotify(object);
        new Thread(taskNotify).start();

        synchronized (object){
            try {
                System.out.println("开始等待");
                object.wait();
                System.out.println("等待结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+threadLocal.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

    }

    /**
     *countDownLatch
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void test06() throws InterruptedException, ExecutionException {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        long start=System.currentTimeMillis();
        System.out.println("开始时间："+ start + " ms");
        Task05 task05 = new Task05(countDownLatch);
        new Thread(task05).start();
        countDownLatch.await();
        // 确保  拿到result 并输出
        //System.out.println("异步计算结果为："+task05.threadLocal.get()); //为什么拿不到值
        System.out.println("异步计算结果为："+task05.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        // 然后退出main线程

    }


    //...Lock CyclicBarrier  signal 后续补充








}
