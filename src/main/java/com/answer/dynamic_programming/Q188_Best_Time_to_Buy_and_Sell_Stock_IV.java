package com.answer.dynamic_programming;

public class Q188_Best_Time_to_Buy_and_Sell_Stock_IV {
    /**
     * 每天买卖k次 (k = 0: Q121 / k不限: Q122 / k = 2: Q123)
     */
    /**
     * Dynamic Programming - Hard
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2 * k + 1];

        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0];
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[prices.length - 1][2 * k];
    }
}
