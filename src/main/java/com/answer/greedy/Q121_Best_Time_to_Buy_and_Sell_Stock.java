package com.answer.greedy;

import java.util.Arrays;

public class Q121_Best_Time_to_Buy_and_Sell_Stock {
    public static void main(String[] args) {
   //     int[] prices = {2,4,1};
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit_4(prices));
    }
    /**
     * 一段时间，只能买卖一次，问最大收益
     * 暴力 Time Limit Exceeded
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    int maxProfit_0(int[]  prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++){
                result = Math.max(result, prices[j] - prices[i]);
            }
        }
        return result;
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
     * Greedy 贪心
     * 因为股票就买卖一次，那么贪心的想法很自然就是取最左最小值，取最右最大值，那么得到的差值就是最大利润
     */
    public int maxProfit_1(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;

        for(int price : prices){
            cost = Math.min(cost, price);  // 取最左最小价格
            profit = Math.max(profit, price - cost); // 减去前面的最小值  直接取最大区间利润
        }

        return profit;
    }
    /**
     * Dynamic Programming 动态规划
     * 买或卖仅一次: 只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票
     *
     * dp[i][0] 表⽰第i天持有股票所得最多现⾦。
     * dp[i][1] 表⽰第i天不持有股票所得最多现⾦
     * 注意这里说的是“持有”，“持有”不代表就是当天“买入”！也有可能是昨天就买入了，今天保持持有的状态
     */
    public int maxProfit_3(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;

        int[][] dp = new int[prices.length][2]; // dp[i]是到第i天的Max Profit

        dp[0][0] -= prices[0]; // 买股票 (表示第0天持有股票，此时的持有股票就一定是买入股票了，因为不可能有前一天推出来，所以dp[0][0] -= prices[0])
        dp[0][1] = 0;          // 卖股票前要买入股票 (表示第0天不持有股票，不持有股票那么现金就是0，所以dp[0][1] = 0)

        for (int i = 1; i < len; i++) {
            // 第i天持有股票即dp[i][0]， 那么可以由两个状态推出来
            // 第i-1天就持有股票，那么就保持现状，所得现金就是昨天持有股票的所得现金 即：dp[i - 1][0]
            // 第i天买入股票，所得现金就是买入今天的股票后所得现金即：-prices[i]
            dp[i][0] = Math.max(dp[i - 1][0], 0 - prices[i]); // 初始资金是0
            // 第i天不持有股票即dp[i][1]， 也可以由两个状态推出来
            // 第i-1天就不持有股票，那么就保持现状，所得现金就是昨天不持有股票的所得现金 即：dp[i - 1][1]
            // 第i天卖出股票，所得现金就是按照今天股票价格卖出后所得现金即：prices[i] + dp[i - 1][0]
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[len - 1][1]; // 因为本题中不持有股票状态所得金钱一定比持有股票状态得到的多
    }
    /**
     *  要增加一维表示有没有股票(For example: 0表示没有股票，可以买。1表示有1股，可以卖)
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
    /**
     * Another form
     */
    public int maxProfit_5(int[] prices) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for(int i = 1; i < prices.length; i++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
        }
        return dp[prices.length-1][2];
    }
    /**
     * 只需要记录 当前天的dp状态和前一天的dp状态就可以了，可以使用滚动数组来节省空间
     */
    public int maxProfit_6(int[] prices) {
        int len = prices.length;
        int dp[][] = new int[2][2];

        dp[0][0] = - prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++){
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], prices[i] + dp[(i - 1) % 2][0]);
        }
        return dp[(len - 1) % 2][1];
    }
    /**
     * 动态规划：版本二(使用一維數組)
     */
    public int maxProfit_7(int[] prices) {
        int[] dp = new int[2];
        // 记录一次交易，一次交易有买入卖出两种状态
        // 0代表持有，1代表卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        // 可以参考斐波那契问题的优化方式
        // 我们从 i=1 开始遍历数组，一共有 prices.length 天，
        // 所以是 i<=prices.length
        for (int i = 1; i <= prices.length; i++) {
            // 前一天持有；或当天买入
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            // 如果 dp[0] 被更新，那么 dp[1] 肯定会被更新为正数的 dp[1]
            // 而不是 dp[0]+prices[i-1]==0 的0，
            // 所以这里使用会改变的dp[0]也是可以的
            // 当然 dp[1] 初始值为 0 ，被更新成 0 也没影响
            // 前一天卖出；或当天卖出, 当天要卖出，得前一天持有才行
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
        }
        return dp[1];
    }
}
