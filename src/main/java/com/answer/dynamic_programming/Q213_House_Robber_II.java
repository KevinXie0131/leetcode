package com.answer.dynamic_programming;

public class Q213_House_Robber_II {
    /**
     * 打家劫舍 II
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     */
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
