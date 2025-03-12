package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack1 {
    /* 完全背包：动态规划 */
    int unboundedKnapsackDP(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        // dp[i][j] 表示从下标为[0-i]的物品，每个物品可以取无限次，放进容量为j的背包，价值总和最大是多少。
        // 在 01背包理论基础（二维数组）中，背包先空留出物品1的容量，此时容量为1，只考虑放物品0的最大价值是dp[0][1]，因为01背包每个物品只有一个，既然空出物品1，那背包中也不会再有物品1
        // 而在完全背包中，物品是可以放无限个，所以即使空出物品1空间重量，那背包中也可能还有物品1，所以此时我们依然考虑放物品0和物品1的最大价值即：dp[1][1]， 而不是dp[0][1]
        // 所以放物品1的情况 = dp[1][1] + 物品1的价值

        // 两种情况，分别是放物品1 和 不放物品1，我们要取最大值（毕竟求的是最大价值）
        // dp[1][4] = max(dp[0][4], dp[1][1] + 物品1 的价值)
        // 递推公式： dp[i][j] = max(dp[i - 1][j], dp[i][j - weight[i]] + value[i]);
        int[][] dp = new int[n + 1][capacity + 1]; // 初始化 dp 表
        // 状态转移
        for (int i = 1; i <= n; i++) { // 遍历物品
            for (int c = 1; c <= capacity; c++) {  // 遍历背包容量
                if (weight[i - 1] > c) {
                    dp[i][c] = dp[i - 1][c]; // 若超过背包容量，则不选物品 i
                } else {
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i][c - weight[i - 1]] + value[i - 1]); // 不选和选物品 i 这两种方案的较大值
                }
            }
        }
        return dp[n][capacity];
    }
    /* 完全背包：空间优化后的动态规划 */
    int unboundedKnapsackDPComp(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        int[] dp = new int[capacity + 1];// 初始化 dp 表
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= capacity; c++) {
                if (weight[i - 1] > c) {
                    dp[c] = dp[c]; // 若超过背包容量，则不选物品 i
                } else {
                    dp[c] = Math.max(dp[c], dp[c - weight[i - 1]] + value[i - 1]);  // 不选和选物品 i 这两种方案的较大值
                }
            }
        }
        return dp[capacity];
    }
    /**
     * Unbounded Knapsack Problem 完全背包: 完全背包又是也是01背包稍作变化而来，即：完全背包的物品数量是无限的
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
