package com.nov.console.threaddemo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    public static void main(String[] args) {

        System.out.println("还有几个人没有准备好呢？" + cyclicBarrier.getNumberWaiting() + ",当前时间:" + System.currentTimeMillis());


        Player aPlayer = new Player("A", 400L);
        Player bPlayer = new Player("B", 600L);
        Player cPlayer = new Player("C", 900L);
        Player dPlayer = new Player("D", 1210L);

        aPlayer.start();
        bPlayer.start();
        cPlayer.start();
        dPlayer.start();
        System.out.println("还有几个人没有准备好呢？" + cyclicBarrier.getNumberWaiting() + ",当前时间:" + System.currentTimeMillis());

    }

    private static class Player extends Thread {
        private String name;
        private Long offWorkerTime;

        public Player(String name, Long offWorkerTime) {
            this.name = name;
            this.offWorkerTime = offWorkerTime;
        }

        @Override
        public void run() {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(name + ":我上号了" + ",当前时间:" + System.currentTimeMillis());
                    try {
                        System.out.println(name + "：已经有" + cyclicBarrier.getNumberWaiting() + "个人准备好,我是第" + (cyclicBarrier.getNumberWaiting() + 1) + "个。" + ",当前时间:" + System.currentTimeMillis());
                        cyclicBarrier.await();
                        System.out.println(name + ":开打开打" + ",当前时间:" + System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }, this.offWorkerTime);
        }
    }
}
