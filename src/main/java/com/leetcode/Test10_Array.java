package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test10_Array {

    public static void main(String[] args) {

        int[] array1 = new int[5];
        Arrays.fill(array1, -1);
        for (int arr : array1) {
            System.out.println(arr);
        }

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
        System.out.println(list);

        List<Integer> list1 = Arrays.asList(1, 3, 4, 5, 6);
        System.out.println(list1);

        Integer[] arr1 = list.toArray(new Integer[0]);
        System.out.println(arr1[1]);

        Integer[] arr2 = list1.toArray(new Integer[0]);
        System.out.println(arr2[4]);
    }
}
