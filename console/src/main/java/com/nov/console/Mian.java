package com.nov.console;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mian {

    private boolean aBoolean;

    public static void main(String[] args) {
        List();
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

}

