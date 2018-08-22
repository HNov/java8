package com.nov.console;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Mian {

    private boolean aBoolean;

    public static void main(String[] args) {
        List();
        hashMap();
        concurrentHashMap();
        System.out.println("Hello Word!");

    }


    public static void List() {
        List<User> userList = new ArrayList<User>();

        userList.add(new User(1, "A"));
        userList.add(new User(2, "B"));
        userList.add(new User(3, "C"));
        userList.add(new User(3, "C1"));

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        list1.add("1");
        list1.add("1");
        list1.add("2");
        list1.add("3");


        list2 = list1.stream().filter(s -> s != "1").collect(Collectors.toList());
        System.out.println(list2.toString());
    }


    public static void hashMap() {
        Map<String, Integer> map = new HashMap<String, Integer>(32, 0.75f);

        map.put("悟空", 30);
        map.put("悟空", 31);
        int value = map.get("悟空");
        System.out.println(value);
        for (int i = 1; i < 100; i++) {
            map.put("key" + i, i);
        }
        int length = 32;
        long start1 = System.currentTimeMillis();
        for (int i = 1; i < 1000; i++) {

            //求余数
            int model = i & (length - 1);
            System.out.println(" h & (length - 1)=" + model);
        }
        long start2 = System.currentTimeMillis();

        for (int i = 1; i < 1000; i++) {
            int model = i % length;
            System.out.println(" h % length=" + model);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("& 耗时：" + (start2 - start1));
        System.out.println("% 耗时：" + (endTime - start2));
        System.out.println("时差：" + ((endTime - start2) - (start2 - start1)));
    }


    public static void string() {
        String str = "";

        str.hashCode();
    }

    public static void concurrentHashMap() {

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int i = 1; i < 1000; i++) {
            concurrentHashMap.put(i, i);
        }
        for (int i = 1; i < 1000; i++) {
            System.out.println("【" + i + "】 Value：" + concurrentHashMap.get(i));
        }
    }
}

