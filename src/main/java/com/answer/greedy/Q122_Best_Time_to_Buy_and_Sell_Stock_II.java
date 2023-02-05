package com.answer.greedy;

public class Q122_Best_Time_to_Buy_and_Sell_Stock_II {

    /**
     * Greedy
     */
    public int maxProfit(int[] prices) {
        int profit = 0;

            for(int i = 1; i < prices.length; i++){
            if(prices[i] - prices[i - 1] > 0){
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
