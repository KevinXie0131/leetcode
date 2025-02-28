package com.answer.dynamic_programming;

public class Q714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {
    /**
     * Dynamic Programming
     * 无限次地完成交易，但是你每笔交易都需要付手续费。
     * 如果已经购买了一个股票，在卖出它之前不能再继续购买股票
     * 每笔交易你只需要为支付一次手续费
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[prices.length][2];

        dp[0][0] -= prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]); // 持有

            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee); // 不持有 手续费
        }
        return dp[len - 1][1];
    }
}
