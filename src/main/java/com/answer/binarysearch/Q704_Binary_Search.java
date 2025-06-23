package com.answer.binarysearch;

public class Q704_Binary_Search {
    /**
     * 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
     * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.
     * 你必须编写一个具有 O(log n) 时间复杂度的算法。
     * You must write an algorithm with O(log n) runtime complexity.
     *
     * 示例 1:
     *  输入: nums = [-1,0,3,5,9,12], target = 9
     *  输出: 4
     *  解释: 9 出现在 nums 中并且下标为 4 / 9 exists in nums and its index is 4
     * 示例 2:
     *  输入: nums = [-1,0,3,5,9,12], target = 2
     *  输出: -1
     *  解释: 2 不存在 nums 中因此返回 -1 / 2 does not exist in nums so return -1
     *
     * 提示：你可以假设 nums 中的所有元素是不重复的。
     *      n 将在 [1, 10000]之间。
     *      nums 的每个元素都将在 [-9999, 9999]之间。
     *      All the integers in nums are unique.
     *      nums is sorted in ascending order.
     */
    public static void main(String[] args) {
       int[] nums = {-1,0,3,5,9,12};
       int target = 9;

      int result =  search0(nums, target);
      System.out.println(result);
    }

    public static int search0(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;// 左闭右闭
        while(left <= right){ // 左闭右闭
            int mid = left + ((right- left) >> 1);

            if(target < nums[mid]){
                right = mid - 1; // 左闭右闭
            }else if(target > nums[mid]){
                left = mid +1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 左闭右闭

        while(left <= right){  // 左闭右闭
            int mid = (left + right) >>> 1;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1; // 左闭右闭
            }
        }
        return -1;
    }
    /**
     *
     */
    public int search_1(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 左闭右开

        while(left < right){  // 左闭右开
            int mid = (left + right) >>> 1;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid; // 左闭右开
            }
        }
        return -1;
    }
    /**
     * 递归
     */
    public int search_2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;  // 左闭右闭

        int result = recursion(nums, target, left, right);
        return result;
    }

    public int recursion(int[] nums, int target, int left, int right) {
        if (left > right) {  // 左闭右闭
            return -1;
        }
        int mid = (left + right) >>> 1;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return recursion(nums, target, mid + 1, right);
        } else {
            return recursion(nums, target, left, mid - 1);  // 左闭右闭
        }
    }
    // 递归
    public int recursion1 (int[] nums, int target, int left, int right){
        if(left > right){
            return -1;
        }
        int mid = left + ((right- left) >> 1);

        if(target < nums[mid]){
            return recursion(nums, target, left, mid - 1);
        }else if(target > nums[mid]){
            return recursion(nums, target, mid + 1, right);
        }else{
            return mid;
        }
    }
}
