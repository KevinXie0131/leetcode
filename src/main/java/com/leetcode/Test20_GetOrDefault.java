package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class Test20_GetOrDefault {

    public static void main(String[] args) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        String[] keys = {"a", "b", "c", "a", "a", "b"};
        for (String key : keys) {
            ArrayList list = map.getOrDefault(key, new ArrayList<String>());
            list.add(key);
            map.put(key, list);
        }

        System.out.println(map);

        HashMap<String, Integer> count = new HashMap<>();
        for (String key : keys) {
            Integer num = count.getOrDefault(key, 0);
            num++;
            count.put(key, num);
        }
        System.out.println(count);

    }
}
