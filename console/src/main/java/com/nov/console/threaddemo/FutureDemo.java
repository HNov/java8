package com.nov.console.threaddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FutureDemo {

    /**
     * 固定的4线程，线程池
     */
    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //生成任务
        CollectTask AWM = new CollectTask("AWM");
        CollectTask m416 = new CollectTask("m416");
        CollectTask SKS = new CollectTask("SKS");
        CollectTask win94 = new CollectTask("win94");
        //生成任务交给线程池
        Future AWM_future = threadPoolExecutor.submit(AWM);
        Future m416_future = threadPoolExecutor.submit(m416);
        Future SKS_future = threadPoolExecutor.submit(SKS);
        Future win94_future = threadPoolExecutor.submit(win94);

        //结果放在一起
        List<Future> futureList = new ArrayList<>();
        futureList.add(AWM_future);
        futureList.add(m416_future);
        futureList.add(SKS_future);
        futureList.add(win94_future);

        //结果统一在一起
        List<Equipment> taskResultList = new ArrayList<>();
        for (Future<List<Equipment>> future : futureList) {
            taskResultList.addAll(future.get());
        }

        //打印出来看看都有些啥
        Map<Integer, Integer> tolal = taskResultList.stream().collect(Collectors.groupingBy(
                Equipment::getType, Collectors.summingInt(Equipment::getNum)));
        tolal.entrySet();
        for (Map.Entry<Integer, Integer> entry : tolal.entrySet()) {
            if (entry.getKey() == 1) {
                System.out.println("总共有子弹:" + entry.getValue());
            }
            if (entry.getKey() == 2) {
                System.out.println("总共有枪:" + entry.getValue());
            }
            if (entry.getKey() == 3) {
                System.out.println("总共有药包:" + entry.getValue());
            }
        }


    }
        static class Equipment {
        /**
         * 这里简单模拟一下，
         * 1、子弹 2、枪械 3、药包
         */
        private Integer type;

        /**
         * 数量
         */
        private Integer num;

        public Equipment(Integer type, Integer num) {
            this.type = type;
            this.num = num;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }


    }

    /**
     * 舔装备人物类
     */
    static class CollectTask implements Callable {
        private String name;

        public CollectTask(String name) {
            this.name = name;
        }

        @Override
        public List<Equipment> call() throws Exception {
            return generatorEquipment(name);
        }


        public static List<Equipment> generatorEquipment(String name) {

            List<Equipment> list = new ArrayList<>();

            Random r = new Random();
            //子弹
            Equipment bullet = new Equipment(1, r.nextInt(100));
            list.add(bullet);
            System.out.println(name + ":捡到子弹" + bullet.num + "发");
            //枪
            Equipment gun = new Equipment(2, r.nextInt(3));
            System.out.println(name + ":捡到枪" + gun.num + " 把");
            list.add(gun);
            //药包
            Equipment bandage = new Equipment(3, r.nextInt(10));
            System.out.println(name + ":捡到绷带" + bandage.num + " 个");
            list.add(bandage);
            return list;
        }
    }
}
