package com.answer.sliding_window;

public class Q643_Maximum_Average_Subarray_I {
    /**
     * You are given an integer array nums consisting of n elements, and an integer k.
     * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
     * Any answer with a calculation error less than 10-5 will be accepted.
     * 子数组最大平均数 I: 一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。任何误差小于 10-5 的答案都将被视为正确答案。
     *
     */
    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        /**
         * 输出：12.75
         * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
         */
        System.out.println(findMaxAverage_2(nums, k));
    }
    /**
     * Approach #2 Sliding Window
     */
    public double findMaxAverage(int[] nums, int k) {
        int i = 0;
        int j = 0;
        int len = nums.length;
        int max = 0;
        int value = 0;

        for(; j < k; j++){
            value += nums[j];
        }
        max = value;

        while(j < len){
            value -= nums[i++];
            value += nums[j++];
            max = Math.max(max, value);
        }

        return max * 1.0 / k;
    }
    /**
     * Another form of sliding window
     */
    public double findMaxAverage_1(int[] nums, int k) {
        int len = nums.length;
        int max = 0;
        int sum = 0;

        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        max = sum;
        for(int i = k; i < len; i++){
            sum += nums[i];
            sum -= nums[i - k];
            max = Math.max(max, sum);
        }

        return max * 1.0 / k;
    }
    /**
     * Approach #1 Cumulative Sum
     */
    public static double findMaxAverage_2(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];

        for (int i = 1; i < nums.length; i++){
            sum[i] = sum[i - 1] + nums[i];
        }
        // 1,12,-5,-6,50,3
        // 1,13,8,2,52,55
        double res = sum[k - 1] * 1.0 / k;

        for (int i = k; i < nums.length; i++) {
            res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
        }
        return res;
    }
}
