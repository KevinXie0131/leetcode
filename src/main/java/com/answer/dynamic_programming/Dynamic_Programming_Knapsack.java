package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack {
    /**
     * 0/1 Knapsack Problem
     */
    public static void main(String[] args) {
/*        int[] val = {1, 2, 4, 2, 3};
        int[] wt = {2, 3, 3 ,1, 2};*/

        int[] val = {12, 10, 20, 15}; //对应的价值
        int[] wt = {2, 1, 3, 2};  //各个物品的重量

 //       System.out.println(knapsack( 5, 5, wt, val));
        System.out.println(knapsack( 5, 4, wt, val)); //背包最大能放下多少重的物品

        System.out.println(knapsack_2( 5, wt, val));
        System.out.println("------------");
        System.out.println(knapsack_3());
    }

    /**
     * https://segmentfault.com/a/1190000040962512
     */
    static int knapsack(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];

        for(int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w < wt[i - 1]) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        for(int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }

        return dp[N][W];
    }

    static int knapsack_2(int W, int[] wt, int[] val) {
        int[] dp = new int[W + 1];

        for(int i = 0; i < wt.length; i++) {
            for (int w = W; w >= wt[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
            }
            System.out.println(Arrays.toString(dp));
        }

      //  System.out.println(Arrays.toString(dp));

        return dp[W];
    }

    static int knapsack_3(){
        int[] weight = {1, 3, 4};   //各个物品的重量
        int[] value = {15, 20, 30}; //对应的价值
        int bagWeight = 4;                //背包最大能放下多少重的物品

        // 二维数组：状态定义:dp[i][j]表示从0-i个物品中选择不超过j重量的物品的最大价值
        int[][] dp = new int[weight.length + 1][bagWeight + 1];

        // 初始化:第一列都是0，第一行表示只选取0号物品最大价值
        for (int j = bagWeight; j >= weight[0]; j--)
            dp[0][j] = dp[0][j - weight[0]] + value[0];

        // weight数组的大小 就是物品个数
        for (int i = 1; i < weight.length; i++) // 遍历物品(第0个物品已经初始化)
        {
            for (int j = 0; j <= bagWeight; j++) // 遍历背包容量
            {
                if (j < weight[i])           //背包容量已经不足以拿第i个物品了
                    dp[i][j] = dp[i - 1][j]; //最大价值就是拿第i-1个物品的最大价值
                    //背包容量足够拿第i个物品,可拿可不拿：拿了最大价值是前i-1个物品扣除第i个物品的 重量的最大价值加上i个物品的价值
                    //不拿就是前i-1个物品的最大价值,两者进行比较取较大的
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
        }
        for(int[] a : dp){
            System.out.println(Arrays.toString(a));
        }
        System.out.println(dp[weight.length - 1][bagWeight]);
        return dp[weight.length - 1][bagWeight];
    }
}
