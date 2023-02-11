package com.leetcode;

import java.util.LinkedHashSet;
import java.util.*;

public class Test9_LinkedHashSet {

    public static void main(String[] args) {
        Set<Integer> set = new LinkedHashSet<>();

        set.add(3);
        set.add(2);
        set.add(1);
        set.add(2);
        set.add(5);

        for (Integer value : set) {
            System.out.println(value);
        }

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("b", "b2");
        map.put("e", "e5");
        map.put("a", "a1");
        map.put("c", "c3");
        map.put("d", "d4");
        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("------------------------");
        map.remove("a");
        map.put("a", "A1");
        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        LinkedHashMap<String, String> map_1 = new LinkedHashMap<>();
        map_1.put("b", "b2");
        map_1.put("e", "e5");
        map_1.put("c", "c3");
        map_1.put("d", "d4");
        map_1.put("a", "A1");
        System.out.println(map.equals(map_1));
    }
}
