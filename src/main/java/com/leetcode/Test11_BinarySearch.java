package com.leetcode;

public class Test11_BinarySearch {

    public static void main(String[] args) {
        int[] array = {-2,-1,0,2,4,5,6,8,9,10,22};
        int result = searchExact(array, 5);
        System.out.println("searchExact: " + result);

    //    int[] array1 = {1,1,2,2,6,7};
        int[] array1 = {1,1,2,2,2,2,6,7};
        int result1 = searchFirstOccurrence(array1, 2);
        System.out.println("searchFirstOccurrence: " + result1);

        int[] array2 = {2, 5, 5, 5, 6, 6, 8, 9, 9, 9};
        int result2 = searchLastOccurrence(array2, 5);
        System.out.println("searchLastOccurrence: " + result2);

        int[] array3 = {-1,0,3,5,9,12};
        int result3 = binarySearchRecursion(array3, 9);
        System.out.println("binarySearchRecursion: " + result3);
    }

    public static int searchExact (int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] == target) {
                return middle;
            } else if (array[middle] > target) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    public static int searchFirstOccurrence (int[] array, int target) {
        int left = 0, right = array.length - 1;
        int result = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] == target) {
                result = middle;
                right = middle - 1;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return result;
    }

    public static int searchLastOccurrence (int[] array, int target) {
        int left = 0, right = array.length - 1;
        int result = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] == target) {
                result = middle;
                left = middle + 1;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return result;
    }

    /**
     *
     */
    public static int binarySearchRecursion(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int result = recursion(nums, target, left, right);
        return result;

    }

    public static int recursion(int[] nums, int target, int left, int right){
        if(left > right){
            return -1;
        }
        int mid = (left + right) >>> 1;
        if(nums[mid] == target){
            return mid;
        }
        else if(nums[mid] < target){
            return recursion(nums, target, mid + 1, right);
        } else {
            return recursion(nums, target, left, mid - 1);
        }

    }
}