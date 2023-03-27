package com.answer.dynamic_programming;

public class Q1049_Last_Stone_Weight_II {
    /**
     * Dynamic Programming
     */
    public int lastStoneWeightII_1(int[] stones) {
        int sum = 0;
        for(int i = 0; i < stones.length; i++){
            sum += stones[i];
        }
        int target = sum / 2;

        int[][] dp = new int[stones.length + 1][target + 1];

        for(int i = 1; i <= stones.length; i++){
            for(int j = 1; j <= target; j++){
                if (j < stones[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }

        return sum - dp[stones.length][target] - dp[stones.length][target];
    }
    /**
     * Dynamic Programming - Using 1D Array
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int i = 0; i < stones.length; i++){
            sum += stones[i];
        }
        int target = sum / 2;

        int[] dp = new int[15001];

        for(int i = 0; i < stones.length; i++){
            for(int j= target; j >= stones[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        return sum - dp[target] - dp[target];
    }
}
