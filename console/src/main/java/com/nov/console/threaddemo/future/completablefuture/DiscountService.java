package com.nov.console.threaddemo.future.completablefuture;

import java.text.NumberFormat;

public class DiscountService {

    private Quote quote;

    public static String applyDiscount(Quote quote) {
        return quote.getShop() + " price is " + apply(quote.getPrice(), quote.getDiscount());
    }

    private static String apply(double price, Discount discount) {
        delay();
        return NumberFormat.getInstance().format(price * (100 - discount.getPercentage()) / 100);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
