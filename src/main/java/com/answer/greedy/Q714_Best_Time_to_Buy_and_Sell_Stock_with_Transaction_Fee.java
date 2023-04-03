package com.answer.greedy;

public class Q714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {
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
