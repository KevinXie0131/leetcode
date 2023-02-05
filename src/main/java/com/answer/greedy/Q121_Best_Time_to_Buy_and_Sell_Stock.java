package com.answer.greedy;

public class Q121_Best_Time_to_Buy_and_Sell_Stock {
    public static void main(String[] args) {
        int[] prices = {2,4,1};
        System.out.println(maxProfit(prices));
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

}
