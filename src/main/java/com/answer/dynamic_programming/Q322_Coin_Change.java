package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q322_Coin_Change {
    public static void main(String[] args) {
       int[] coins = {1,2,5};
       int amount = 11;
        System.out.println(coinChange(coins, amount));
    }
    /**
     * Approach 3 (Dynamic programming - Bottom up) [Accepted]
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin >= 0 &&  dp[i - coin] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if(dp[amount] == Integer.MAX_VALUE) return -1;

        System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
}
