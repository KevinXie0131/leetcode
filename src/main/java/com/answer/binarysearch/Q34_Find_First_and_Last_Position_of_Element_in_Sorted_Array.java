package com.answer.binarysearch;

import java.util.Arrays;

public class Q34_Find_First_and_Last_Position_of_Element_in_Sorted_Array {
    public static void main(String[] args) {
/*        int[] nums = {1, 2, 2};
        int target = 2;*/
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange_3(nums, target)));

    }
    public int[] searchRange(int[] nums, int target) {
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
        while(right<nums.length - 1 && nums[right] ==nums[right + 1]){
            right++;
        }
        return new int[]{left,right};
    }

    public int binarySearch(int[] nums, int target) {
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

    public static int[] searchRange_3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                int l = mid, r = mid;
                while (l >= 0 && target == nums[l]) {
                    l--;
                }
                while (r <= nums.length - 1 && target == nums[r]) {
                    r++;
                }
                return new int[]{++l, --r};
            } else if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target == nums[left] && target == nums[right]) return new int[]{left, right};
        if (target == nums[left]) return new int[]{left, left};
        if (target == nums[right]) return new int[]{right, right};
        return new int[]{-1, -1};

    }
}
