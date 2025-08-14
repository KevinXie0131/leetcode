package com.answer.sliding_window;

public class Q643_Maximum_Average_Subarray_I {
    /**
     * You are given an integer array nums consisting of n elements, and an integer k.
     * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
     * Any answer with a calculation error less than 10-5 will be accepted.
     * 子数组最大平均数 I: 一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。任何误差小于 10^-5 的答案都将被视为正确答案。
     */
    public static void main(String[] args) {
        int[] nums = {1,12,-5,-6,50,3};
        int k = 4;
        /**
         * 输出：12.75
         * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
         */
        System.out.println(findMaxAverage_2a(nums, k));
    }
    /**
     * Approach #2 Sliding Window 滑动窗口
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
     * 定长滑窗套路
     *  我总结成三步：入-更新-出。
     *      入：下标为 i 的元素进入窗口，窗口元素和 s 增加 nums[i]。如果 i<k−1 则重复第一步。
     *      更新：更新答案。本题由于窗口长度固定为 k，可以统计窗口元素和的最大值 maxS，最后返回的时候再除以 k。
     *      出：下标为 i−k+1 的元素离开窗口，窗口元素和 s 减少 nums[i−k+1]。
     * 以上三步适用于所有定长滑窗题目。
     */
    public double findMaxAverage3(int[] nums, int k) {
        int maxS = Integer.MIN_VALUE; // 窗口元素和的最大值
        int s = 0; // 维护窗口元素和

        for (int i = 0; i < nums.length; i++) {
            // 1. 进入窗口
            s += nums[i];
            if (i < k - 1) { // 窗口大小不足 k
                continue;
            }
            // 2. 更新答案
            maxS = Math.max(maxS, s);
            // 3. 离开窗口
            s -= nums[i - k + 1];
        }
        return (double) maxS / k;
    }
    /**
     * Approach #1 Cumulative Sum
     * 前缀和，由于是求解连续的K个数平均值最大值，因此可以采用前缀和求解
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
    /**
     * 前缀和 another form
     */
    public static double findMaxAverage_2a(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++){
            sum[i + 1] = sum[i] + nums[i];
        }
        //   1,12,-5,-6,50,3
        // 0,1,13, 8, 2,52,55
        double res = sum[k] * 1.0 / k;

        for (int i = k + 1; i < nums.length + 1; i++) {
            res = Math.max(res, (sum[i] - sum[i - k]) * 1.0 / k);
        }
        return res;
    }
}
