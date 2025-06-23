package com.answer.greedy;

public class Q714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {
    /**
     * 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
     * 返回获得利润的最大值。
     *  Find the maximum profit you can achieve.
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     *       The transaction fee is only charged once for each stock purchase and sale.
     * 示例 1：
     *  输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
     *  输出：8
     *  解释：能够达到的最大利润:
     *      在此处买入 prices[0] = 1
     *      在此处卖出 prices[3] = 8
     *      在此处买入 prices[4] = 4
     *      在此处卖出 prices[5] = 9
     *      总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
     */
    /**
     * 有交易手续费
     */
    /**
     * Greedy
     *
     * buy 表示在最大化收益的前提下，如果我们手上拥有一支股票，那么它的最低买入价格是多少。
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int profit = 0;
        int buy = prices[0] + fee;

        for(int i = 1; i < prices.length; i++){
            if(prices[i] + fee < buy){
                buy = prices[i] + fee;
            }
            else if(prices[i] > buy){
                profit += prices[i] - buy;
                // 但实际上，我们此时卖出股票可能并不是全局最优的（例如下一天股票价格继续上升），因此我们可以提供一个反悔操作
                // 这样一来，如果下一天股票价格继续上升，我们会获得 prices[i+1]−prices[i] 的收益
                buy = prices[i];
            }
        }

        return profit;
    }
}
