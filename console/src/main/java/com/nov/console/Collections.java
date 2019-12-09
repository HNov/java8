package com.nov.console;

import java.lang.reflect.Array;
import java.util.*;

public class Collections {

    public static void main(String[] args) {

        parseToList();
    }

    public static void parseToList() {

        String[] str = new String[]{"aa", "bb"};
        Arrays.asList(str);
        LinkedHashMap();

    }


    public static void Lists() {


        List<String> strings = new ArrayList<>();
        strings.add(1, "l");


        List<String> linkedList = new LinkedList<>();
        linkedList.add("ll");

        List<String> vector = new Vector();
        vector.add("v");

        java.util.Collections.sort(linkedList);

    }


    public static void LinkedHashMap() {
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put(1, "A");
        linkedHashMap.put(2, "B");
        linkedHashMap.put(3, "C");

        Set<Integer> integerSet = linkedHashMap.keySet();
        for (Integer key : integerSet) {
            String val = linkedHashMap.get(key);
            System.out.println(val);
        }


    }


}
