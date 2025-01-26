package com.answer.binarysearch;

public class Q162_Find_Peak_Element {
    /**
     * Approach 1: Linear Scan
     * 由于题目保证了 nums[i]!=nums[i+1]，那么数组 nums 中最大值两侧的元素一定严格小于最大值本身。因此，最大值所在的位置就是一个可行的峰值位置。
     * 我们对数组 nums 进行一次遍历，找到最大值对应的位置即可。
     */
    public int findPeakElement(int[] nums) {
        int index = 0;

        for(int i = 0; i< nums.length; i++){
            if(nums[i] > nums[index]){
                index = i;
            }
        }
        return index;
    }
    /**
     * Approach 3: Recursive Binary Search
     */
    public int findPeakElement_1(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int left, int right){
        if(left == right){
            return left;
        }
        int mid = (left + right) >>> 1;

        if(nums[mid] < nums[mid + 1]){
            return search(nums, mid + 1, right);
        }else{
            return search(nums, left, mid);
        }
    }
    /**
     * Approach 3: Iterative Binary Search
     */
    public int findPeakElement_2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = (left + right) >>> 1;
            if(nums[mid] < nums[mid + 1]){
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
    /**
     * Template II
     */
    public int findPeakElement_3(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while(l < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] <= nums[n - 1]){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
