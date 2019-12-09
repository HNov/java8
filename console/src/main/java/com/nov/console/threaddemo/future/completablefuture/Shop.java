package com.nov.console.threaddemo.future.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Shop {

    private final String shopName;
    private static Random random = new Random(1);


    public Shop(String shopName) {
        this.shopName = shopName;

    }

    public String getShopName() {
        return shopName;
    }

    //省略掉一些代码，只看关键部分

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * 100;
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> futurePrice.complete(calculatePrice(product))).start();
        return futurePrice;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }




    public String getStrPrice(String product) {
        double price = calculatePrice(product);
        Discount discount = Discount.values()[random.nextInt(Discount.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, discount);
    }



    public static void main(String[] args) throws Exception {
        Shop shop = new Shop("BestShop");
        long start = System.currentTimeMillis();
        Future<Double> futurePrice = shop.getPriceAsync("some product");
        System.out.println("调用返回,耗时:" + (System.currentTimeMillis() - start));
        double price = futurePrice.get();
        System.out.println("价格返回,耗时" + (System.currentTimeMillis() - start));
    }




}
