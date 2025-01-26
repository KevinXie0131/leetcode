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
     * Approach 3: Iterative Binary Search 二分查找
     * 如果 nums[i]<nums[i+1]，那么我们往右走；
     * 如果 nums[i]>nums[i+1]，那么我们往左走。
     *
     * 如果nums[mid] > nums[mid + 1]，那么在[l, mid]这个区间内一定存在一个峰值。因为[l,mid]这一段如果是单调递减的话，
     * 那么nums[l]就是峰值，否则第一个出现上升的点就是峰值。
     *
     * 如果nums[mid] < nums[mid + 1]，那么在[mid+1, r]这个区间内一定存在一个峰值。
     * 因为[mid+1,r]这一段如果是单调递增的话，那么nums[r]就是峰值，否则第一个出现下降的点就是峰值。
     */
    public int findPeakElement_2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right){
            int mid = (left + right) >>> 1;
            if(nums[mid] < nums[mid + 1]){
                left = mid + 1; // 因为峰值至少为mid + 1
            } else {
                // 假设当前分割点 mid 满足关系 num[mid]>nums[mid+1] 的话，一个很简单的想法是 num[mid] 可能为峰值，
                // 而 nums[mid+1] 必然不为峰值，于是让 r=mid，从左半部分继续找峰值。
                right = mid; // 因为right没有-1， 所以left < right
            }
        }
        return left;
        // return right; // works too
        // return nums[right] > nums[left] ? right : left; //  works too. and safer

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
