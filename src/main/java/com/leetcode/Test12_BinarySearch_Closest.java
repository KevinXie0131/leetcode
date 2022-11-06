package com.leetcode;

public class Test12_BinarySearch_Closest {
    public static void main(String[] args) {
        int[] array = {-1,1,6,8,9,22};
        int result = searchClosest(array, 21);
        System.out.println("searchClosest: " + result);

        int[] array1 = {-1,1,6,8,9,22};
        int result1 = searchClosestGeneral(array1, 9);
        System.out.println("searchClosestGeneral: " + result1);
    }

    public static int searchClosest (int[] array, int target) {
        if (target <= array[0])
            return 0;
        if (target >= array[array.length - 1])
            return array.length - 1;

        int left = 0, right = array.length - 1;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return (array[left] - target) < (target - array[right]) ? left : right;
    }

    public static int searchClosestGeneral (int[] array, int target) {
        if (target <= array[0])
            return 0;
        if (target >= array[array.length - 1])
            return array.length - 1;

        int left = 0, right = array.length - 1;

        while (left < right - 1) {
            int middle = left + (right - left) / 2;
            if (array[middle] < target) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return Math.abs(array[left] - target) < Math.abs(array[right] - target) ? left : right;
    }
}
