package com.leetcode;

import java.util.*;

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

        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 5));
        System.out.println(set1);
        System.out.println(set1.contains(2));
        set1.remove(2);
        System.out.println(set1);
        System.out.println(set1.contains(2));
        Iterator iter = set1.iterator();
        while (iter.hasNext()) {
            Integer value = (Integer)iter.next();
            if (value == 3) {
                iter.remove();
            }
        }
        System.out.println(set1);

    }
}
