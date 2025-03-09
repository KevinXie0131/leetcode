package com.answer.greedy;

public class Q122_Best_Time_to_Buy_and_Sell_Stock_II {
    /**
     * Greedy贪心算法
     * Approach 3: Simple One Pass
     * 本题首先要清楚两点：
     *  只有一只股票！
     *  当前只有买股票或者卖股票的操作
     *  想获得利润至少要两天为一个交易单元
     *
     * 如果想到其实最终利润是可以分解的，那么本题就很容易了. 不要整块的去看，而是把整体利润拆为每天的利润。
     * 就是把利润分解为每天为单位的维度，而不是从 0 天到第 3 天整体去考虑！
     * 那么根据 prices 可以得到每天的利润序列：(prices[i] - prices[i - 1]).....(prices[1] - prices[0])。
     * 我们需要收集每天的正利润就可以，收集正利润的区间，就是股票买卖的区间，而我们只需要关注最终利润，不需要记录区间。
     */
    public int maxProfit(int[] prices) { // 局部最优：收集每天的正利润，全局最优：求得最大利润。
        int profit = 0;

            for(int i = 1; i < prices.length; i++){
            if(prices[i] - prices[i - 1] > 0){ // 将前后两天的利润相加
                profit += prices[i] - prices[i - 1]; // Math.max(prices[i + 1] - prices[i], 0);
            }
        }
        return profit;
    }
    /**
     * Dynamic Programming 动态规划
     * 每天买卖不限: 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * dp[i][0] 表⽰第i天持有股票所得现⾦。
     * dp[i][1] 表⽰第i天不持有股票所得最多现⾦
     */
    public int maxProfit_3(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[prices.length][2];

        dp[0][0] -= prices[0];  // 持股票
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            // 第i天持股票所剩最多现金 = max(第i-1天持股票所剩现金, 第i-1天持现金-买第i天的股票)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]); // 资金是dp[i - 1][1]
            // 第i天持有最多现金 = max(第i-1天持有的最多现金，第i-1天持有股票的最多现金+第i天卖出股票)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]); // 注意这⾥是和121. 买卖股票的最佳时机唯⼀不同的地⽅。
        }
        return dp[len - 1][1];
    }
}
