package com.answer.greedy;

public class Q122_Best_Time_to_Buy_and_Sell_Stock_II {
    /**
     * Greedy
     * Approach 3: Simple One Pass
     */
    public int maxProfit(int[] prices) {
        int profit = 0;

            for(int i = 1; i < prices.length; i++){
            if(prices[i] - prices[i - 1] > 0){ // 将前后两天的利润相加
                profit += prices[i] - prices[i - 1]; // Math.max(prices[i + 1] - prices[i], 0);
            }
        }
        return profit;
    }
    /**
     * Dynamic Programming
     * 每天买卖不限: 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * dp[i][0] 表⽰第i天持有股票所得现⾦。
     * dp[i][1] 表⽰第i天不持有股票所得最多现⾦
     */
    public int maxProfit_3(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[prices.length][2];

        dp[0][0] -= prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]); // 资金是dp[i - 1][1]
            // 注意这⾥是和121. 买卖股票的最佳时机唯⼀不同的地⽅。
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];
    }
}
