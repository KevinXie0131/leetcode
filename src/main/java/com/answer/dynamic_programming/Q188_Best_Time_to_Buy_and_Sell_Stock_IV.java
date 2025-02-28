package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q188_Best_Time_to_Buy_and_Sell_Stock_IV {
    /**
     * Dynamic Programming - Hard
     *
     * 每天买卖k次 (k = 0: Q121 / k不限: Q122 / k = 2: Q123)
     * 最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次（你必须在再次购买前出售掉之前的股票）。
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
    /**
     * A more general template for multiple transactions per day
     */
    public static void main(String[] args) {
        int k = 2;
        int[] prices = {3,2,6,5,0,3};
        int res = maxProfit_5(k, prices);
        System.out.println(res);
    }
    static public int maxProfit_5(int k, int[] prices) {
        int result = 0;

        int[][][] dp = new int[prices.length][k + 1][2]; // j is transaction number. k=0: not hold / k=1: hold
        dp[0][0][0] = 0;
        dp[0][0][1] = -prices[0];
        for(int num = 1; num < k + 1; num++){
            dp[0][num][0] = dp[0][num][1] = -10000; // 0 <= prices[i] <= 105
        }

        for(int i = 1; i < prices.length; i++){
            dp[i][0][0] = dp[i - 1][0][0];
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);

            for(int num = 1; num < k; num++){
                dp[i][num][0] = Math.max(dp[i - 1][num][0], dp[i - 1][num - 1][1] + prices[i]);
                dp[i][num][1] = Math.max(dp[i - 1][num][1], dp[i - 1][num][0] - prices[i]);
            }

            dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k - 1][1] + prices[i]);

        }
        System.out.println(Arrays.deepToString(dp));

        for(int num = 0; num < k + 1; num++){
            result = Math.max(result, dp[prices.length - 1][num][0]);
        }
        return result;
    }
}
