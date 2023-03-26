package com.answer.dynamic_programming;

public class Q746_Min_Cost_Climbing_Stairs {
    public static void main(String[] args) {
        int[] cost = {10,15,20};
        System.out.println(minCostClimbingStairs(cost));
    }

    /**
     * Approach 1: Bottom-Up Dynamic Programming (Tabulation)
     */
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];

        for(int i = 2; i < cost.length + 1; i++){
            int takeOne = dp[i - 1] + cost[i - 1];
            int takeTwo = dp[i - 2] + cost[i - 2];

            dp[i] = Math.min(takeOne, takeTwo);
        }

        return dp[cost.length];
    }
}
