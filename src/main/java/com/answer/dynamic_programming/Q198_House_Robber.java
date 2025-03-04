package com.answer.dynamic_programming;

import java.util.HashMap;

public class Q198_House_Robber {
    /**
     * Approach 1: Recursion with Memoization
     */
    HashMap<Integer, Integer> map = new  HashMap<>();
    int[] nums;

    int dp(int i) {
        // Base cases
        if(i == 0) return nums[0];
        if(i == 1) return Math.max(nums[0], nums[1]);

        if(!map.containsKey(i)){
            map.put(i, Math.max(dp(i - 1), dp(i -2) + nums[i])); // Recurrence relation
        }

        return map.get(i);
    }

    public int rob(int[] nums) {
        this.nums = nums;
        return dp(nums.length - 1);
    }
    /**
     * Approach 2: Dynamic Programming
     * 两间相邻的房屋不能偷，求能够偷到的最高金额。
     * 类似爬楼梯
     */
    public int rob_1(int[] nums) {
        if(nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        // Base cases
        dp[0]= nums[0];
        dp[1]= Math.max(nums[0],nums[1]);

        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);// Recurrence relation 在i - 1和i - 2只能选一个
        }
        return dp[nums.length - 1];
    }

}
