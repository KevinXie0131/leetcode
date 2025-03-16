package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q674_Longest_Continuous_Increasing_Subsequence {
    /**
     * 本题相对于昨天的动态规划：300.最长递增子序列 最大的区别在于“连续”。本题要求的是最长连续递增序列
     * 概括来说：不连续递增子序列的跟前0-i 个状态有关，连续递增的子序列只跟前一个状态有关
     *
     * Dynamic Programming
     * Continuous Increasing Subsequence 连续递增子序列
     * dp[i]表示以num[i]这个数结尾的最长递增子序列的长度
     */
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length]; // dp[i]：以下标i为结尾的连续递增的子序列长度为dp[i]。
        Arrays.fill(dp, 1); // 以下标i为结尾的连续递增的子序列长度最少也应该是1
        // 如果 nums[i] > nums[i - 1]，那么以 i 为结尾的连续递增的子序列长度 一定等于 以i - 1为结尾的连续递增的子序列长度 + 1 。
        // 注意这里就体现出和动态规划：300.最长递增子序列 的区别！
        // 因为本题要求连续递增子序列，所以就只要比较nums[i]与nums[i - 1]，而不用去比较nums[j]与nums[i] （j是在0到i之间遍历）。
        // 既然不用j了，那么也不用两层for循环，本题一层for循环就行，比较nums[i] 和 nums[i - 1]。
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i + 1] > nums[i]){ // 连续记录
                dp[i + 1] = dp[i] + 1;
            }

            // max = max > dp[i + 1] ? max : dp[i + 1];
        }

        int max = 1;
        for(int i : dp){
            max = Math.max(max, i);
        }
        return max;
    }
    /**
     * 动态规划 状态压缩
     */
    public int findLengthOfLCIS_3(int[] nums) {
        // 记录以 前一个元素结尾的最长连续递增序列的长度 和 以当前 结尾的......
        int beforeOneMaxLen = 1, currentMaxLen = 0;
        // res 赋最小值返回的最小值1
        int res = 1;
        for (int i = 1; i < nums.length; i ++) {
            currentMaxLen = nums[i] > nums[i - 1] ? beforeOneMaxLen + 1 : 1;
            beforeOneMaxLen = currentMaxLen;
            res = Math.max(res, currentMaxLen);
        }
        return res;
    }
    /**
     * Approach #1: Sliding Window [Accepted]
     */
    public int findLengthOfLCIS_1(int[] nums) {
        int max = 0;
        int start  = 0;

        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i - 1] >= nums[i]){
                start  = i;
            }
            max = Math.max(max, i - start  + 1);
        }

        return max;
    }
    /**
     * Greedy 贪心
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 发现贪心好像更简单一点，而且空间复杂度仅是O(1)。
     */
    public int findLengthOfLCIS_2(int[] nums) {
        int result = 1; // 连续⼦序列最少也是1
        int count = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) { // 连续记录
                count++;
            } else { // 不连续，count从头开始
                count = 1;
            }
            if (count > result) {
                result = count;
            }
        }
        return result;
    }
}
