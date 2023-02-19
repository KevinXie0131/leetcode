package com.learn.binarysearch;

public class BinarySearchTemplate_II {
    /**
     * Initial Condition: left = 0, right = length - 1
     * Termination: left == right
     * Searching Left: right = mid
     * Searching Right: left = mid+1
     */
    int binarySearch(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0;
        int right = nums.length;

        while(left < right){
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        // Post-processing:
        // End Condition: left == right
        if(left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }
}
