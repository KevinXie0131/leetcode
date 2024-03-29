package com.leetcode;

import java.util.*;

public class Test3_hashmap {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet();

        set.add(5);
        set.add(1);
        set.add(8);
        set.add(2);
        set.add(9);
        set.add(0);
        set.remove(2);

        for (Integer a : set) {
            System.out.print(a + " ");
        }
        System.out.println();
        System.out.println("ceiling: " + set.ceiling(5));
        System.out.println("floor: " + set.floor(5));
        System.out.println("higher: " + set.higher(5));
        System.out.println("lower: " + set.lower(5));
        System.out.println("ceiling 3: " + set.ceiling(3));
        System.out.println("floor 3: " + set.floor(3));
        System.out.println("higher 3: " + set.higher(3));
        System.out.println("lower 3: " + set.lower(3));

        System.out.println(set.first());
        System.out.println(set.last());
        System.out.println(set.pollFirst());
        System.out.println(set.pollLast());
        System.out.println(set);

        HashMap<String, String> hashMap = new HashMap();
        System.out.println(hashMap.put("c","c"));
        System.out.println(hashMap.put("c","c"));
        hashMap.put("a","a");
        hashMap.put("b","b");
        hashMap.remove("b");
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
        HashMap<String, Integer> products1 = new HashMap<>();
        String[] inventory = {"Shirt", "TShirt", "Shirt", "Shoe", "Shoe", "TShirt", "Shoe", "Sneaker"};
        for (String item : inventory) {
           if (!products.containsKey(item)) {
            //    products.computeIfAbsent(item, key -> 1);
                products.putIfAbsent(item, 1);
            } else {
                products.computeIfPresent(item, (key, value) -> value + 1);
            }

            products1.put(item, products1.getOrDefault(item, 0) + 1);
        }
        System.out.println("products " + products);
        System.out.println("products1 " + products1);

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        System.out.println("pairs " + pairs);

        System.out.println("TreeMap");
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("c", 2);
        treeMap.put("d", 3);
        treeMap.put("a", 1);
        treeMap.put("b", 5);
        System.out.println(treeMap);
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.lowerKey("c"));
        System.out.println(treeMap.higherKey("c"));
        System.out.println(treeMap.floorKey("c"));
        System.out.println(treeMap.ceilingKey("c"));
        System.out.println(treeMap.firstEntry());
        System.out.println(treeMap.lastEntry());
        System.out.println(treeMap.pollFirstEntry());
        System.out.println(treeMap);
        System.out.println(treeMap.pollLastEntry());
        System.out.println(treeMap);
        treeMap.replace("b", 15);
        System.out.println(treeMap);
        treeMap.remove("b");
        System.out.println(treeMap);
    }
}
