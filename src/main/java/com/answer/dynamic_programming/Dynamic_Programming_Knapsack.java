package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack {

    public static void main(String[] args) {
/*        int[] val = {1, 2, 4, 2, 3};
        int[] wt = {2, 3, 3 ,1, 2};*/

        int[] val = {12, 10, 20, 15};
        int[] wt = {2, 1, 3, 2};

 //       System.out.println(knapsack( 5, 5, wt, val));
        System.out.println(knapsack( 5, 4, wt, val));

        System.out.println(knapsack_2( 5, wt, val));

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
        }

        System.out.println(Arrays.toString(dp));

        return dp[W];
    }
}
