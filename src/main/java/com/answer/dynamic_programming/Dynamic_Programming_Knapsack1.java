package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack1 {
    /**
     * Unbounded Knapsack Problem
     */
    public static void main(String[] args) {

        int[] val = {15, 20, 30};
        int[] wt = {1, 3, 4};

        System.out.println(knapsack_2( 4, wt, val));
        System.out.println(knapsack_3( 4, wt, val));
    }

    static int knapsack_2(int W, int[] wt, int[] val) {
        int[] dp = new int[W + 1];

        for(int i = 0; i < wt.length; i++) {
            for (int w = wt[i]; w <= W; w++) {
                dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
            }
            System.out.println(Arrays.toString(dp));
        }

      //  System.out.println(Arrays.toString(dp));
        return dp[W];
    }

    static int knapsack_3(int W, int[] wt, int[] val) {
        int[] dp = new int[W + 1];

        for (int w = 0; w <= W; w++) {
            for(int i = 0; i < wt.length; i++) {
                if(w - wt[i] >= 0){
                    dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }

        //  System.out.println(Arrays.toString(dp));
        return dp[W];
    }
    /**
     * 完全背包的物品是可以添加多次的，所以要从⼩到⼤去遍历
     * 先遍历物品，再遍历背包
     */
    static int knapsack_4(int bagWeight, int[] weight, int[] value){
        int[] dp = new int[bagWeight + 1];
        // 先遍历物品，再遍历背包
        for(int i = 0; i < weight.length; i++) { // 遍历物品
            for(int j = weight[i]; j <= bagWeight ; j++) { // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagWeight];
    }
    /**
     * 完全背包
     * 先遍历被背包, 再遍历物品
     */
    static int knapsack_5(int bagWeight, int[] weight, int[] value){
        int[] dp = new int[bagWeight + 1];
        // 先遍历背包，再遍历物品
        for(int j = 0; j <= bagWeight; j++) { // 遍历背包容量
            for(int i = 0; i < weight.length; i++) { // 遍历物品
                if (j - weight[i] >= 0)
                    dp[j] = Math. max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagWeight];
    }
}
