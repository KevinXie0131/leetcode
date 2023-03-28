package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack1 {

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
}
