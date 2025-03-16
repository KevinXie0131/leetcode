package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public static void main(String[] args) {
        int k = 3;
        int[] prices = {3,2,6,5,0,3};
        int res = maxProfit(k, prices);
        System.out.println(res);
    }
    /**
     * 这道题目可以说是动态规划：123.买卖股票的最佳时机III 的进阶版，这里要求至多有k次交易
     *  Dynamic Programming - Hard
     *
     * 每天买卖k次 (k = 0: Q121 / k不限: Q122 / k = 2: Q123)
     * 最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次（你必须在再次购买前出售掉之前的股票）。
     *
     * dp[i][j] ：第i天的状态为j，所剩下的最大现金是dp[i][j]
     * j的状态表示为：
     *  0 表示不操作
     *  1 第一次买入
     *  2 第一次卖出
     *  3 第二次买入
     *  4 第二次卖出
     *  .....
     * 规律: 除了0以外，偶数就是卖出，奇数就是买入
     * 本题和动态规划：123.买卖股票的最佳时机III最大的区别就是这里要类比j为奇数是买，偶数是卖的状态。
     * 时间复杂度: O(n * k)，其中 n 为 prices 的长度
     * 空间复杂度: O(n * k)
     */
    static public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2 * k + 1]; //

        for (int j = 1; j < 2 * k; j += 2) {
            dp[0][j] = -prices[0]; // j为偶数是卖、奇数是买的状态
        }
        System.out.println(Arrays.deepToString(dp));

        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                // dp[i][1]状态，有两个具体操作：
                // 操作一：第i天买入股票了，那么dp[i][1] = dp[i - 1][0] - prices[i]
                // 操作二：第i天没有操作，而是沿用前一天买入的状态，即：dp[i][1] = dp[i - 1][1]
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);     //持有
                // dp[i][2]也有两个操作：
                // 操作一：第i天卖出股票了，那么dp[i][2] = dp[i - 1][1] + prices[i]
                // 操作二：第i天没有操作，沿用前一天卖出股票的状态，即：dp[i][2] = dp[i - 1][2]
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]); //不持有
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[prices.length - 1][2 * k];
    }
    /**
     * 版本二: 二维 dp数组
     */
    public int maxProfit_4(int k, int[] prices) {
        if (prices.length == 0) return 0;
        // [天数][股票状态]
        // 股票状态: 奇数表示第 k 次交易持有/买入, 偶数表示第 k 次交易不持有/卖出, 0 表示没有操作
        int len = prices.length;
        int[][] dp = new int[len][k*2 + 1];
        // dp数组的初始化, 与版本一同理
        for (int i = 1; i < k*2; i += 2) {
            dp[0][i] = -prices[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < k*2 - 1; j += 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[len - 1][k*2];
    }
    /**
     * A more general template for multiple transactions per day
     * 三维数组dp[i][j][k]，第i天，第j次买卖，k表示买还是卖的状态，从定义上来讲是比较直观。
     */
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
    /**
     * 版本一: 三维 dp数组
     */
    public int maxProfit_2(int k, int[] prices) {
        if (prices.length == 0) return 0;
        // [天数][交易次数][是否持有股票]
        int len = prices.length;
        int[][][] dp = new int[len][k + 1][2];
        // dp数组初始化
        // 初始化所有的交易次数是为确保 最后结果是最多 k 次买卖的最大利润
        for (int i = 0; i <= k; i++) {
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                // dp方程, 0表示不持有/卖出, 1表示持有/买入
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][k][0];
    }
    /**
     * 版本三：一维 dp数组
     */
    public int maxProfit_6(int k, int[] prices) {
        if(prices.length == 0 || k == 0){
            return 0;
        }
        // 其实就是123题的扩展，123题只用记录2次交易的状态
        // 这里记录k次交易的状态就行了
        // 每次交易都有买入，卖出两个状态，所以要乘 2
        int[] dp = new int[2 * k];
        // 按123题解题格式那样，做一个初始化
        for(int i = 0; i < dp.length / 2; i++){
            dp[i * 2] = -prices[0];
        }
        for(int i = 1; i <= prices.length; i++){
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
            // 还是与123题一样，与123题对照来看, 就很容易啦
            for(int j = 2; j < dp.length; j += 2){
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i-1]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i - 1]);
            }
        }
        // 返回最后一次交易卖出状态的结果就行了
        return dp[dp.length - 1];
    }
    /**
     * 一维 dp数组
     */
    public int maxProfit_7(int k, int[] prices) {
        //edge cases
        if(prices.length == 0 || k == 0) return 0;

        int dp[] = new int [k * 2 + 1];
        for(int i = 1; i < 2 * k + 1; i += 2){  // 奇数天购入股票，故初始化只初始化奇数天。
            dp[i] = -prices[0];
        }
        for(int i = 1; i < prices.length; i++){ //i 从 1 开始，因为第 i = 0 天已经透过初始化完成了。
            for(int j = 1; j < 2 * k + 1; j++){ //j 从 1 开始，因为第 j = 0 天已经透过初始化完成了。
                if(j % 2 == 1) {
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);//奇数天购买
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]); //偶数天卖出
                }
            }
            //打印DP数组
            //for(int x : dp)
            //    System.out.print(x +", ");
            //System.out.println();
        }
        return dp[2 * k];   //return 第2 * k次卖出的获利。
    }
}
