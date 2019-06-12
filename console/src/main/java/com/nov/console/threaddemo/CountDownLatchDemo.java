package com.nov.console.threaddemo;


import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private static CountDownLatch start = new CountDownLatch(1);
    private static CountDownLatch end;

    public static void main(String[] args) {
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        for (int index = 0; index < threadCount; index++) {
            Thread thread = new Thread(new Worker(start, end), "Thread" + index);
            thread.start();
        }
        start.countDown();
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All Worker is Worked!");

    }


    static class Worker implements Runnable {

        private CountDownLatch start;
        private CountDownLatch end;

        public Worker(CountDownLatch start, CountDownLatch end) {

            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {

            try {
                this.start.await();
                Thread thread = Thread.currentThread();
                System.out.println("The Thread: " + thread.getName() + "is Worked:");
                this.end.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
