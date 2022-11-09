package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Test14_HashSet {

    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();

        list.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
        list.add(new ArrayList<Integer>(Arrays.asList(1, 3)));
        list.add(new ArrayList<Integer>(Arrays.asList(1, 3)));
        list.add(new ArrayList<Integer>(Arrays.asList(0, 3)));
        System.out.println(list);

        HashSet<List<Integer>> set = new HashSet<>(list);
        System.out.println(set);

        List<List<Integer>> list1 = new ArrayList<>(set);
        System.out.println(list1);
    }
}
