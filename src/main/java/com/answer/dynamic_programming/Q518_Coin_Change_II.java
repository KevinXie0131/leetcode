package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q518_Coin_Change_II {

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1,2,5};
        System.out.println(change(amount, coins));
        System.out.println(change_1(amount, coins));
    }

    /**
     * Approach 1: Dynamic Programming
     *
     * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
     * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int i = 0; i < coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                dp[j] += dp[j - coins[i]];
            }
            System.out.println(Arrays.toString(dp));
        }
        /**
         *  for (int coin : coins) {
         *       for (int x = coin; x < amount + 1; ++x) {
         *         dp[x] += dp[x - coin];
         *       }
         *     }
         */
        return dp[amount];
    }
    /**
     * 究其原因，该代码计算的结果是 排列数，而不是 组合数
     * the result is not correct
     */
    public static int change_1(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int j = 1; j <= amount; j++){ //枚举金额
            for (int i = 0; i < coins.length; i++){
                int coin = coins[i]; //枚举硬币
                if (j < coin) continue; // coin不能大于amount
                dp[j] += dp[j-coin];
            }
        }
        return dp[amount];
    }
    /**
     * 2D Dynamic Programming
     */
    public int change_3(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        dp[0][0] = 1;  // 合法的初始化，其他dp[0][j]均不合法
        for(int i = 1; i < n + 1; i ++) {
            for(int j = 0; j < amount + 1; j ++) {
                dp[i][j] = dp[i - 1][j];
                if(j - coins[i - 1] >= 0) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }
}
