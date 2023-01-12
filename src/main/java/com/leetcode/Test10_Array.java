package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test10_Array {

    public static void main(String[] args) {

        int[] array0 = new int[] {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(array0));
        int[] array0_Cloned = Arrays.copyOf(array0, 3);
        System.out.println(Arrays.toString(array0_Cloned));
        int[] array0_Cloned1 = Arrays.copyOfRange(array0, 1, 3);
        System.out.println(Arrays.toString(array0_Cloned1));

        int[] array1 = new int[5];
        Arrays.fill(array1, -1);
        for (int arr : array1) {
            System.out.println(arr);
        }
        
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
        list.set(1, 13);
        list.remove(list.size() - 1);
        System.out.println("subList-> " + list.subList(0,2));
        System.out.println(list.stream().mapToInt(i -> i).toArray());

        List<Integer> listCopy = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 6));
        System.out.println(list);
        System.out.println("indexOf ->"  + list.indexOf(3));
        System.out.println("contains ->"  + list.contains(3));
        System.out.println("contains ->"  + list.contains(6));
        System.out.println("containsAll ->"  + list.containsAll(listCopy));

        List<int[]> list2 = new ArrayList<>();
        list2.add(new int[]{1,2,3});
        list2.add(new int[]{1,2,4});
        System.out.println(list2.get(1).length);

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

        int[][] intArray2D = new int[2][3];
        intArray2D = new int[][]{{2, 3}, {1, 4}, {3, 5}};
        int[] temp = intArray2D[0];
        intArray2D[0] = intArray2D[2];
        intArray2D[2] = temp;
        System.out.println(Arrays.deepToString(intArray2D));

        int[] intArraySearch = new int[]{2, 5, 6, 8, 10, 11, 15};
        System.out.println(Arrays.binarySearch(intArraySearch, 11));

        func(intArraySearch);
        System.out.println(Arrays.toString(intArraySearch));
    }

    static public void func(int[] intArraySearch){
        intArraySearch = new int[]{1, 1, 1, 1};
        System.out.println(Arrays.toString(intArraySearch));
    }
}
