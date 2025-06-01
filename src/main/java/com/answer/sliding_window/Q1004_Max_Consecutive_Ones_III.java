package com.answer.sliding_window;

public class Q1004_Max_Consecutive_Ones_III {
    /**
     * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can
     * flip at most k 0's.
     * 最大连续1的个数 III: 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     */
    public static void main(String[] args) {
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        /**
         * 输出：6
         * 解释：[1,1,1,0,0,1,1,1,1,1,1] index=5/10 从 0 翻转到 1，最长的子数组长度为 6。
         */
        System.out.println(longestOnes(nums, k));
    }

    /**
     * Similar with Q487 Max Consecutive Ones II
     *
     * Approach: Sliding Window
     */
    public static int longestOnes(int[] nums, int k) {
        int max = 0, count = 0;
        int right = 0, left = 0;
        int len = nums.length;

        while(right < len){
            if(nums[right] == 0){
                count++;

                while(count > k){
                    if(nums[left] == 0){
                        count--;
                    }
                    left++;
                }
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }

}
