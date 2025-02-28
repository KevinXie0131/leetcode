package com.answer.dynamic_programming;

public class Q309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    /**
     * Approach 1: Dynamic Programming
     * 有cool down时间: 多次买卖一支股票 (必须在再次购买前出售掉之前的股票 / 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][4];
        dp[0][0] -= prices[0]; // 持有股票

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i]) ); // 持有股票
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);                                     // 保持卖出状态(不持有状态之一)
            dp[i][2] = dp[i - 1][0] + prices[i];                                                 // 卖出股票(不持有状态之一) 之后有冷冻期
            dp[i][3] = dp[i - 1][2];                                                             // 冷冻期
        }
        return Math. max(dp[n - 1][3], Math.max(dp[n - 1][1], dp[n - 1][2]));
    }
}
