package com.leetcode;

import java.util.LinkedHashSet;
import java.util.Set;

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
    }
}
