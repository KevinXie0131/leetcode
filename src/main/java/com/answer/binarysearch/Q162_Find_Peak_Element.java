package com.answer.binarysearch;

public class Q162_Find_Peak_Element {
    /**
     * 寻找峰值
     * A peak element is an element that is strictly greater than its neighbors.
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * You must write an algorithm that runs in O(log n) time.
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     *
     * 示例 1：
     *  输入：nums = [1,2,3,1]
     *  输出：2
     *  解释：3 是峰值元素，你的函数应该返回其索引 2。
     * 示例 2：
     *  输入：nums = [1,2,1,3,5,6,4]
     *  输出：1 或 5
     *  解释：你的函数可以返回索引 1，其峰值元素为 2；或者返回索引 5， 其峰值元素为 6。
     * 对于所有有效的 i 都有 nums[i] != nums[i + 1] nums[i] != nums[i + 1] for all valid i.
     */
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
            if(nums[mid] > nums[mid + 1]){
                r = mid;
            }
            else{
                l = mid + 1;
            }
        }
        return l;
    }
}
