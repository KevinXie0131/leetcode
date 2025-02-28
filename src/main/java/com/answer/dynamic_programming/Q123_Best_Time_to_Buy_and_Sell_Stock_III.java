package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q123_Best_Time_to_Buy_and_Sell_Stock_III {
    /**
     * Dynamic Programming - Hard
     * 买卖2次: 最多可以完成两笔交易 (在再次购买前出售掉之前的股票）
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][5];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0]; // 第一次买卖at the same day, 第二次资金是0
        dp[0][4] = 0;

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];                                     // 不操作
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 第一次持有 / 0 - prices[i] can work too
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]); // 第一次不持有
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]); // 第二次持有
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]); // 第二次不持有
        }
        return dp[prices.length - 1][4]; // dp[prices.length - 1][4] 已经包含了 dp[prices.length - 1][2]
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
