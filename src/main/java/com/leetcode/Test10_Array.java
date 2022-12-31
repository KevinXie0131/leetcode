package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test10_Array {

    public static void main(String[] args) {

        int[] array0 = new int[] {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(array0));

        int[] array1 = new int[5];
        Arrays.fill(array1, -1);
        for (int arr : array1) {
            System.out.println(arr);
        }

        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
        List<Integer> listCopy = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 6));
        System.out.println(list);
        System.out.println("indexOf ->"  + list.indexOf(3));
        System.out.println("contains ->"  + list.contains(3));
        System.out.println("contains ->"  + list.contains(6));
        System.out.println("containsAll ->"  + list.containsAll(listCopy));

        List<Integer> list1 = Arrays.asList(1, 3, 4, 5, 6);
        System.out.println(list1);

        Integer[] arr1 = list.toArray(new Integer[0]);
        System.out.println(arr1[1]);

        Integer[] arr2 = list1.toArray(new Integer[0]);
        System.out.println(arr2[4]);

        char[] chArray = new char[] {'b', 'a', 'c'};
        String st = String.valueOf(chArray);
        System.out.println(st);
        System.out.println(new String(chArray));
        char[] chArray1 = st.toCharArray();
        System.out.println(chArray1);

        int[] intArray = new int[4];
        Arrays.fill(intArray, 3);
        System.out.println(Arrays.toString(intArray));

    }
}
