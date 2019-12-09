package com.nov.console.threaddemo.future.completablefuture;


import java.util.List;
import java.util.stream.Collectors;

public class Quote {
    private final String shop;
    private final double price;
    private final Discount discount;

    public Quote(String shop, double price, Discount discount) {
        this.shop = shop;
        this.price = price;
        this.discount = discount;
    }


    public String getShop() {
        return shop;
    }

    public double getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }


    public static Quote parse(String content) {
        String[] items = content.split(":");
        return new Quote(items[0], Double.parseDouble(items[1]), Discount.valueOf(items[2]));
    }





}
