package com.leetcode.binarysearch;

import java.util.Arrays;

public class Test38_Find_First_and_Last_Position {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        //    int[] nums = {1};
        int target = 8;
        int[] result = searchRange(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] searchRange(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1,-1};
        }
        int index = binarySearch(nums, target);
        if(index == -1){
            return new int[]{-1,-1};
        }
        int left = index;
        int right = index;
        while(left>=1 && nums[left] ==nums[left - 1]){
            left--;
        }
        while(right < nums.length - 1 && nums[right] ==nums[right + 1]){
            right++;
        }
        return new int[]{left,right};
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 不变量：左闭右闭区间

        while (left <= right) { // 不变量：左闭右闭区间
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1; // 不变量：左闭右闭区间
            }
        }
        return -1; // 不存在
    }
}
