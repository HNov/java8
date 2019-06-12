package com.nov.console.threaddemo;

import java.util.concurrent.*;

/**
 * 多线程Demo
 * 实现多线程的四种方式：
 * 1.继承Thread类
 * 2.实现Runnable接口
 */
public class ThreadTest {
    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(1);


    public static void main(String[] args) {
        Thread myThread = new MyThread();
        myThread.start();

        Thread myRunable = new Thread(new MyRunnable());
        myRunable.start();


        Future<String> futur = threadPoolExecutor.submit(new MyCallAble());
        try {
            System.out.println("当前线程ID:" + Thread.currentThread() + "," + (String) futur.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程ID:" + Thread.currentThread() + "," + "MyThread.run()");
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("当前线程ID:" + Thread.currentThread() + "," + "MyRunnable.run()");
        }
    }


    static class MyCallAble implements Callable {

        @Override
        public String call() throws Exception {
            System.out.println("当前线程ID:"+Thread.currentThread()+","+"MyCallAble.call()");
            return "MyCallAble.result,处理线程："+Thread.currentThread();
        }
    }
}
