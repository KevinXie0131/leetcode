package com.answer.dynamic_programming;

public class Q213_House_Robber_II {
    /**
     * Approach 1: Dynamic Programming
     *
     * The problem becomes to rob either House[1]-House[n-1] or House[2]-House[n],
     * depending on which choice offers more money. Now the problem has degenerated to the House Robber.
     * 所有的房屋都 围成一圈，两间相邻的房屋不能偷，求能够偷到的最高金额。
     * 首尾元素只选一个, 或者都不选
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int result1 = robRange(nums, 0, nums.length - 2); // 考虑包含首元素，不包含尾元素
        int result2 = robRange(nums, 1, nums.length - 1); // 考虑包含尾元素，不包含首元素
        return Math.max(result1, result2);
    }
    // 198.打家劫舍的逻辑
    public int robRange(int[] nums, int start, int end) {
        if(start == end) return nums[start];

        int[] dp = new int[nums.length];
        dp[start]= nums[start];
        dp[start + 1]= Math.max(nums[start], nums[start + 1]);

        for(int i = start + 2; i <= end; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }
}
