package com.nov.console.threaddemo.future.completablefuture;


public class CompletableFutureTest {



    public static void main(String[] args) {
        PriceService priceService = new PriceService();
        long start = System.currentTimeMillis();
        System.out.println(priceService.findPrices("iPhone7"));
        System.out.println("顺序流执行服务耗时:" + ( System.currentTimeMillis() - start ));

        start=System.currentTimeMillis();
        System.out.println(priceService.findPricesByParallelStream("iPhone7"));
        System.out.println("平行流执行服务耗时:" + ( System.currentTimeMillis() - start ));

        start=System.currentTimeMillis();
        System.out.println(priceService.findPricesByCompletableFuture("iPhone7"));
        System.out.println("并行流执行服务耗时:" + ( System.currentTimeMillis() - start ));


        start=System.currentTimeMillis();
        System.out.println(priceService.findPricesByCompletableFuture2("iPhone7"));
        System.out.println("并行流自定义线程池执行服务耗时:" + ( System.currentTimeMillis() - start ));
    }


}
