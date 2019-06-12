package com.nov.console.threaddemo;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private static Semaphore semaphore = new Semaphore(4);

    public static void main(String[] args) throws InterruptedException {
        EatChickenPlayer m416 = new EatChickenPlayer("m416");
        EatChickenPlayer AWM = new EatChickenPlayer("AWM");
        EatChickenPlayer SKS = new EatChickenPlayer("SKS");
        EatChickenPlayer win94 = new EatChickenPlayer("win94");
        EatChickenPlayer M24 = new EatChickenPlayer("M24");
        m416.start();
        AWM.start();
        SKS.start();
        win94.start();
        M24.start();
    }

    private static class EatChickenPlayer extends Thread {

        private String name;


        public EatChickenPlayer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
//            synchronized (semaphore) {
            if (semaphore.tryAcquire()) {
                System.out.println(name + ":我进入游戏啦,剩余名额" + semaphore.availablePermits() + ",快来~");
            } else {
                System.out.println(name + ":卧槽，队伍满了,剩余名额" + semaphore.availablePermits() + "人");
            }
            //}
        }
    }


}