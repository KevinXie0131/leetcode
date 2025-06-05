package com.answer.two_pointers;

import java.util.Arrays;

public class Q977_Squares_of_a_Sorted_Array {
    /**
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number
     * sorted in non-decreasing order.
     * 一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * nums is sorted in non-decreasing order. nums 已按 非递减顺序 排序
     * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n)
     * solution using a different approach?
     * 进阶：请你设计时间复杂度为 O(n) 的算法解决本问题
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：nums = [-4,-1,0,3,10]
         *  输出：[0,1,9,16,100]
         *  解释：平方后，数组变为 [16,1,0,9,100] 排序后，数组变为 [0,1,9,16,100]
         */
        int[] nums = {-4,-1,0,3,10};
        int[] res = sortedSquares(nums);
        System.out.println(Arrays.toString(res));
    }
    /**
     *
     */
   static public int[] sortedSquares(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        for(int left = 0, right = len-1, index = len-1; left <= right;){
            if(nums[left] * nums[left] < nums[right] * nums[right]){
                result[index] = nums[right] * nums[right];
                right--;
            } else{
                result[index] = nums[left] * nums[left];
                left++;
            }
            index--;
        }

        return result;
    }
    /**
     *
     */
    public int[] sortedSquares_0(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                // 正数的相对位置是不变的， 需要调整的是负数平方后的相对位置
                result[index] = nums[left] * nums[left];
                left++;
            } else {
                result[index] = nums[right] * nums[right];
                right++;
            }
            index--;
        }
        return result;
    }
}
