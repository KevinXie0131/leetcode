package com.answer.greedy;

public class Q122_Best_Time_to_Buy_and_Sell_Stock_II {
    /**
     * 本题和121. 买卖股票的最佳时机 的唯一区别是本题股票可以买卖多次了（注意只有一只股票，所以再次购买前要出售掉之前的股票）
     *
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
                profit += prices[i] - prices[i - 1]; // Math.max(prices[i + 1] - prices[i], 0); // 也可以
            }
        }
        return profit;
    }
    /**
     * Dynamic Programming 动态规划
     * 每天买卖不限: 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * dp[i][0] 表⽰第i天持有股票所得最多现⾦。
     * dp[i][1] 表⽰第i天不持有股票所得最多现⾦
     *
     * 注意这里和121. 买卖股票的最佳时机 唯一不同的地方，就是推导dp[i][0]的时候，第i天买入股票的情况。
     * 在121. 买卖股票的最佳时机 中，因为股票全程只能买卖一次，所以如果买入股票，那么第i天持有股票即dp[i][0]一定就是 -prices[i]。
     * 而本题，因为一只股票可以买卖多次，所以当第i天买入股票的时候，所持有的现金可能有之前买卖过的利润即：dp[i - 1][1]，所以dp[i - 1][1] - prices[i]
     */
    public int maxProfit_3(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[prices.length][2];

        dp[0][0] -= prices[0];  // 持股票
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            // 第i天持股票所剩最多现金 = max(第i-1天持股票所剩现金, 第i-1天持现金-买第i天的股票)
            // 第i天买入股票，所得现金就是昨天不持有股票的所得现金减去 今天的股票价格 即：dp[i - 1][1] - prices[i]
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]); // 资金是dp[i - 1][1] 注意这⾥是和121. 买卖股票的最佳时机唯⼀不同的地⽅。
            // 第i天持有最多现金 = max(第i-1天持有的最多现金，第i-1天持有股票的最多现金+第i天卖出股票)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];  // 卖出股票收益高于持有股票收益
    }
    /**
     * DP using 2*2 Array (下方還有使用一維滾動數組的更優化版本)
     */
    public int maxProfit_4(int[] prices) {
        int dp[][] = new int [2][2]; // 注意这里只开辟了一个2 * 2大小的二维数组
        //dp[i][0]: holding the stock 持有股票
        //dp[i][1]: not holding the stock 没有股票
        dp[0][0] = - prices[0];
        dp[0][1] = 0;

        for(int i = 1; i < prices.length; i++){
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] + prices[i]);
        }
        return dp[(prices.length - 1) % 2][1];
    }
    /**
     * 优化空间
     */
    public int maxProfit_5(int[] prices) {
        int[] dp = new int[2];
        // 0表示持有，1表示卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i <= prices.length; i++){
            // 前一天持有; 既然不限制交易次数，那么再次买股票时，要加上之前的收益
            dp[0] = Math.max(dp[0], dp[1] - prices[i-1]);
            // 前一天卖出; 或当天卖出，当天卖出，得先持有
            dp[1] = Math.max(dp[1], dp[0] + prices[i-1]);
        }
        return dp[1];
    }
}
