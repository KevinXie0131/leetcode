package com.answer.greedy;

public class Q53_Maximum_Subarray {
    public static void main(String[] args) {
        int[] nums = {-2, -1};
        System.out.println(maxSubArray(nums));
    }

    /**
     * Greedy
     * 局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
     * 全局最优：选取最大“连续和”
     */
    public static int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            max = Math.max(max, sum);
            if(sum < 0){
                sum = 0;
            }
        }

        return max;
    }
    /**
     * Brute force
     * Time Limit Exceeded
     */
    public int maxSubArray_1(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) { // 设置起始位置
            count = 0;
            for (int j = i; j < nums.length; j++) { // 每次从起始位置i开始遍历寻找最大值
                count += nums[j];
                result = count > result ? count : result;
            }
        }
        return result;
    }
    /**
     * Dynamic Programming
     */
    public int maxSubArray_3(int[] nums) {
        int result = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for(int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);

            if(dp[i] > result){
                result = dp[i];
            }
        }

        return result;
    }
}
