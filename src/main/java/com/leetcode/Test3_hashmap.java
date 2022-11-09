package com.leetcode;

import java.util.ArrayList;
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

        HashMap<String, ArrayList<String>> parents = new HashMap<String, ArrayList<String>>();
        ArrayList<String> emptyList = new ArrayList<>();
        parents.putIfAbsent("first",  emptyList);
        parents.get("first").add("1111");
        parents.putIfAbsent("third",   new ArrayList<>());

        parents.computeIfAbsent("second", key -> new ArrayList<>()).add("2222");
        parents.computeIfAbsent("second", key -> new ArrayList<>()).add("2222a");

        for (String key : hashMap.keySet()) {
            System.out.println("Key " + key);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("entry " + entry.getKey() + " " + hashMap.get(entry.getKey()));
        }

        String mapDefault =  hashMap.getOrDefault("ff","this is f");
        System.out.println("mapDefault " + mapDefault);

        HashMap<String, Integer> products = new HashMap<>();
        String[] inventory = {"Shirt", "TShirt", "Shirt", "Shoe", "Shoe", "TShirt", "Shoe", "Sneaker"};
        for (String item : inventory) {
           if (!products.containsKey(item)) {
            //    products.computeIfAbsent(item, key -> 1);
                products.putIfAbsent(item, 1);
            } else {
                products.computeIfPresent(item, (key, value) -> value + 1);
            }
        }
        System.out.println("products " + products);

     }
}
