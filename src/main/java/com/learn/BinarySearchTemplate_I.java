package com.learn;

import java.util.Arrays;

public class BinarySearchTemplate_I {
    /**
     * Template I - Iteration
     *
     * Initial Condition: left = 0, right = length-1
     * Termination: left > right
     * Searching Left: right = mid-1
     * Searching Right: left = mid+1
     */
    public int search_1(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = (left + right) >>> 1; // Prevent (left + right) overflow
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        // End Condition: left > right
        return -1;
    }
    /**
     * Recursion
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int result = recursion(nums, target, left, right);
        return result;

    }

    public int recursion(int[] nums, int target, int left, int right){
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
