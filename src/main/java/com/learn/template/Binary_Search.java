package com.learn.template;

public class Binary_Search {

    /**
     * 二分查找
     */
    public int fn(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // 根据题意补充代码
                return mid;
            }
            if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // left 是插入点
        return left;
    }
}
