package com.learn.template;

public class Binary_Search_1 {
    /**
     * 二分查找: 重复元素，最左边的插入点
     */
    public int fn(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
