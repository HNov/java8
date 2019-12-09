package com.nov.console.threaddemo;

public class ThreadLocalDemo {

    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("AAA");
        Object obj = threadLocal.get();
        ThreadLocal threadLocal1=new ThreadLocal();

        threadLocal1.set("BBB");
         obj = threadLocal1.get();
        System.out.println(obj.toString());


    }
}
