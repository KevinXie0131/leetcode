package com.leetcode;

import java.util.HashSet;

public class Test23_Set {
    public static void main(String[] args) {

        HashSet<Integer> set1 = new HashSet();
        HashSet<Integer> set2 = new HashSet();

        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(4);
        set2.add(5);

        set1.retainAll(set2);
        System.out.println(set1);
    }
}
