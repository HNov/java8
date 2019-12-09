package com.nov.console.threaddemo.future.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PriceService {

    private List<Shop> shops = Arrays.asList(
            new Shop("shop1"),
            new Shop("shop2"),
            new Shop("shop3"),
            new Shop("shop4"),
            new Shop("shop5"),
            new Shop("shop6"),
            new Shop("shop7"),
            new Shop("shop8"));


    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }


    public List<String> findPricesByParallelStream(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }


    public List<String> findPricesByCompletableFuture(String product) {

        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture
                        .supplyAsync(() -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product))))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());

    }


    public List<String> findPricesByCompletableFuture2(String product) {

        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));

        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)), executor))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

/**     连接多个异步任务  */

    //顺序流
    public List<String> findDiscountPrices(String product) {

        return shops.stream()
                .map(shop -> shop.getStrPrice(product))
                .map(Quote::parse)
                .map(DiscountService::applyDiscount)
                .collect(Collectors.toList());
    }

    //CompletableFuture实现
    public List<String> findDiscountPricesBysupplyAsync(String product) {

        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));

        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getStrPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> DiscountService.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }




}
