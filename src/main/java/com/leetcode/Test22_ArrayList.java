package com.leetcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Test22_ArrayList {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(10);
        System.out.println(list);
        System.out.println(list.get(1));
        list.set(1, 15);
        list.remove(4);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);

        System.out.println(Collections.binarySearch(list, 7));

        Collections.swap(list, 1, 3);
        System.out.println(list);

        Collections.rotate(list, 2);
        System.out.println(list);
    }
}
