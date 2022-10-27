package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Test3_hashmap {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet();

        set.add(5);
        set.add(1);
        set.add(8);
        set.add(2);
        set.add(9);

        for (Integer a : set) {
            System.out.println(a);
        }

        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("c","c");
        hashMap.put("a","a");
        hashMap.put("b","b");
        hashMap.put("e","e");
        hashMap.put("d","d");
        hashMap.put("f"," old f");

        hashMap.computeIfAbsent("key5",
                k -> "2000" + "33000");

        hashMap.computeIfPresent("d",
                (key, val) -> val + 100);

        for (String key : hashMap.keySet()) {
            System.out.println("Key " + key);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("entry " + entry.getKey() + " " + hashMap.get(entry.getKey()));
        }

       String mapDefault =  hashMap.getOrDefault("ff","this is f");
        System.out.println("mapDefault " + mapDefault);
     }
}
