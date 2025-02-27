package com.answer.greedy;

import java.util.Arrays;

public class Q121_Best_Time_to_Buy_and_Sell_Stock {
    /**
     * 买或卖仅一次
     */
    public static void main(String[] args) {
   //     int[] prices = {2,4,1};
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit_4(prices));
    }
    /**
     * Approach 2: One Pass
     */
    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int profit = 0;

        for(int price : prices){
            if(price < min){
                min = price;
            }
            else if(price > min && price - min > profit){
                profit = price - min;
            }
        }

        return profit;
    }
    /**
     * Greedy
     */
    public int maxProfit_1(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;

        for(int price : prices){
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost); // 减去前面的最小值
        }

        return profit;
    }
    /**
     * Dynamic Programming
     *
     * dp[i][0] 表⽰第i天持有股票所得现⾦。
     * dp[i][1] 表⽰第i天不持有股票所得最多现⾦
     */
    public int maxProfit_3(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;

        int[][] dp = new int[prices.length][2]; // dp[i]是到第i天的Max Profit  要增加一维表示有没有股票(0表示没有股票，可以买。1表示有1股，可以卖)

        dp[0][0] -= prices[0]; // 买股票
        dp[0][1] = 0;          // 卖股票前要买入股票

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }

        return dp[len - 1][1];
    }
    /**
     *  dp[i][0]: have not bought
     *  dp[i][1]: buy today
     *  dp[i][2]: have bought and sell today
     *
     */
   static public int maxProfit_4(int[] prices) {
        int result = 0;

        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for(int i = 1; i < prices.length; i++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] =  Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
      //      result = Math.max(result, dp[i][0]); // can ignore
      //      result = Math.max(result, dp[i][1]);
            result = Math.max(result, dp[i][2]);
        }
       System.out.println(Arrays.deepToString(dp));
        return result;
    }
}
