package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q123_Best_Time_to_Buy_and_Sell_Stock_III {
    /**
     * 这道题目相对 121.买卖股票的最佳时机 和 122.买卖股票的最佳时机II 难了不少。
     * 关键在于至多买卖两次，这意味着可以买卖一次，可以买卖两次，也可以不买卖。
     *
     * Dynamic Programming - Hard
     * 买卖2次: 最多可以完成两笔交易 (在再次购买前出售掉之前的股票）
     * 一天一共就有五个状态，
     *  没有操作 （其实我们也可以不设置这个状态）
     *  第一次持有股票
     *  第一次不持有股票
     *  第二次持有股票
     *  第二次不持有股票
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][5]; // dp[i][j]表示第i天状态j所剩最大现金
        // 需要注意：dp[i][1]，表示的是第i天，买入股票的状态，并不是说一定要第i天买入股票，这是容易陷入的误区。
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0]; // 第一次买卖at the same day, 第二次资金是0
        dp[0][4] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];                                     // 不操作
            // dp[i][1]状态，有两个具体操作：
            // 操作一：第i天买入股票了，那么dp[i][1] = dp[i-1][0] - prices[i]
            // 操作二：第i天没有操作，而是沿用前一天买入的状态，即：dp[i][1] = dp[i - 1][1]
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 第一次持有 / 0 - prices[i] can work too
            // dp[i][2]也有两个操作：
            // 操作一：第i天卖出股票了，那么dp[i][2] = dp[i - 1][1] + prices[i]
            // 操作二：第i天没有操作，沿用前一天卖出股票的状态，即：dp[i][2] = dp[i - 1][2]
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]); // 第一次不持有
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]); // 第二次持有
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]); // 第二次不持有
        }
        // 两次卖出的状态现金最大一定是最后一次卖出
        return dp[prices.length - 1][4]; // 如果第一次卖出已经是最大值了，那么我们可以在当天立刻买入再立刻卖出。所以dp[prices.length - 1][4] 已经包含了 dp[prices.length - 1][2], 也就是说第二次卖出手里所剩的钱一定是最多的。
      //  return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4]); // It is the same
    }
    /**
     * Another form / Ignore dp[i][0] 不操作
     */
    public int maxProfit1(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = dp[0][2] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //  dp[i][0] = dp[i - 1][0];                                 // 不操作 (can be ignored)
            dp[i][0] = Math.max(dp[i - 1][0], 0 - prices[i]);            // 第一次持有
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]); // 第一次不持有
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]); // 第二次持有
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]); // 第二次不持有
        }
        return dp[prices.length - 1][3];
    }
    /**
     * 版本一: 定义 5 种状态:
     * 0: 没有操作, 1: 第一次买入, 2: 第一次卖出, 3: 第二次买入, 4: 第二次卖出
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][5];
        dp[0][1] = -prices[0];
        // 初始化第二次买入的状态是确保 最后结果是最多两次买卖的最大利润
        dp[0][3] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[len - 1][4];
    }
    /**
     * 版本二: 空间优化
     */
    public int maxProfit6(int[] prices) {
        int[] dp = new int[4];
        // 存储两次交易的状态就行了
        dp[0] = -prices[0]; // dp[0]代表第一次交易的买入
        dp[1] = 0;          // dp[1]代表第一次交易的卖出
        dp[2] = -prices[0]; // dp[2]代表第二次交易的买入
        dp[3] = 0;          // dp[3]代表第二次交易的卖出
        for(int i = 1; i <= prices.length; i++){
            // 要么保持不变，要么没有就买，有了就卖
            dp[0] = Math.max(dp[0], 0 - prices[i-1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i-1]);
            // 这已经是第二次交易了，所以得加上前一次交易卖出去的收获
            dp[2] = Math.max(dp[2], dp[1] - prices[i-1]);
            dp[3] = Math.max(dp[3], dp[2] + prices[i-1]);
        }
        return dp[3];
    }
    /**
     * refer to Q121 template
     */
    public int maxProfit_4(int[] prices) {
        int result = 0;

        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for(int i = 1; i < prices.length; i++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] =  Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] =  Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] =  Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] =  Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);

            result = Math.max(result, dp[i][0]);
            result = Math.max(result, dp[i][1]);
            result = Math.max(result, dp[i][2]);
            result = Math.max(result, dp[i][3]);
            result = Math.max(result, dp[i][4]);
        }

        return result;
    }
    /**
     * A more general template for multiple transactions per day
     */
    public static void main(String[] args) {
    //    int[] prices = {1, 2, 3, 4, 5};
        int[]  prices = {3,3,5,0,0,3,1,4};
        int res = maxProfit_5(prices);
        System.out.println(res);

    }
    static public int maxProfit_5(int[] prices) {
        int result = 0;

        int[][][] dp = new int[prices.length][3][2]; // j is transaction number. k=0: not hold / k=1: hold
        dp[0][0][0] = 0;
        dp[0][0][1] = -prices[0];
        //因为最小值再减去1就是最大值Integer.MIN_VALUE-1=Integer.MAX_VALUE
        //不可能
        dp[0][1][0] = dp[0][1][1] = dp[0][2][0] = dp[0][2][1] = -10000; // 0 <= prices[i] <= 105

        for(int i = 1; i < prices.length; i++){
            dp[i][0][0] = dp[i - 1][0][0];
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][0][0] - prices[i]);

            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][0][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][1][0] - prices[i]);

            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][1][1] + prices[i]);
        }
        System.out.println(Arrays.deepToString(dp));

        result = Math.max(Math.max(dp[prices.length - 1][0][0], dp[prices.length - 1][1][0]), dp[prices.length - 1][2][0]);

        return result;
    }
}
