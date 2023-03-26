package com.answer.dynamic_programming;

import java.util.HashMap;

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
    /**
     * Approach 2: Top-Down Dynamic Programming (Recursion + Memoization)
     */
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public int minCostClimbingStairs_1(int[] cost) {
        return minCost(cost.length, cost);
    }

    int minCost(int length, int[] cost){
        if(length <= 1) return 0;

        if(map.containsKey(length)){
            return map.get(length);
        }

        int takeOne = cost[length - 1] + minCost(length - 1, cost);
        int takeTwo = cost[length - 2] + minCost(length - 2, cost);

        map.put(length, Math.min(takeOne, takeTwo));
        return map.get(length);
    }
}
