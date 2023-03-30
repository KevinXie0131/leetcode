package com.answer.dynamic_programming;

public class Q416_Partition_Equal_Subset_Sum {

    /**
     * classic Knapsack problem
     * Approach 3: Bottom Up Dynamic Programming
     */
    public boolean canPartition_1(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        int target = sum / 2;

        int[][] dp = new int[nums.length + 1][target + 1];

        for(int i = 1; i <= nums.length; i++){
            for(int j = 1; j <= target; j++){
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }

        return dp[nums.length][target] == target;
    }
    /**
     * Approach 4: Optimised Dynamic Programming - Using 1D Array
     *
     * 在动态规划：关于01背包问题，你该了解这些！（滚动数组）中就已经说明：
     * 如果使⽤⼀维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒叙遍历
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        int target = sum / 2;

        // 每个数组中的元素不会超过 100，数组的⼤⼩不会超过 200总和不会⼤于20000，背包最⼤只需要其中⼀半，所以10001⼤⼩就可以了
        int[] dp = new int[20001];
        // 01背包
        for(int i = 0; i < nums.length; i++){
            for(int j= target; j >= nums[i]; j--){
                // 每⼀个元素⼀定是不可重复放⼊，所以从⼤到⼩遍历
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        // 集合中的元素正好可以凑成总和target
        return dp[target] == target;
    }
}
