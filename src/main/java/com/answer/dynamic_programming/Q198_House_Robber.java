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
     * 前状态和前面状态会有一种依赖关系，那么这种依赖关系都是动规的递推公式
     *
     */
    public int rob_1(int[] nums) {
        if(nums.length == 1) return nums[0];

        int[] dp = new int[nums.length]; // dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。
        // Base cases
        dp[0]= nums[0];
        dp[1]= Math.max(nums[0], nums[1]);
        // 决定dp[i]的因素就是第i房间偷还是不偷
        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);// Recurrence relation 在i - 1和i - 2只能选一个
        }
        return dp[nums.length - 1];
    }
    /**
     * 使用滚动数组思想，优化空间
     * 分析本题可以发现，所求结果仅依赖于前两种状态，此时可以使用滚动数组思想将空间复杂度降低为3个空间
     */
    public int rob_2(int[] nums) {

        int len = nums.length;

        if (len == 0) return 0;
        else if (len == 1) return nums[0];
        else if (len == 2) return Math.max(nums[0],nums[1]);

        int[] result = new int[3]; //存放选择的结果
        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);

        for(int i = 2; i < len; i++){
            result[2] = Math.max(result[0] + nums[i], result[1]);
            result[0] = result[1];
            result[1] = result[2];
        }
        return result[2];
    }
    /**
     * 进一步对滚动数组的空间优化 dp数组只存与计算相关的两次数据
     */
    public int rob_3(int[] nums) {
        if (nums.length == 1)  return nums[0];
        // 初始化dp数组
        // 优化空间 dp数组只用2格空间 只记录与当前计算相关的前两个结果
        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int res = 0;
        // 遍历
        for (int i = 2; i < nums.length; i++) {
            res = Math.max((dp[0] + nums[i]) , dp[1] );
            dp[0] = dp[1];
            dp[1] = res;
        }
        // 输出结果
        return dp[1];
    }
}
