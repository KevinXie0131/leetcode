package com.answer.dynamic_programming;

import java.util.Arrays;

public class Dynamic_Programming_Knapsack {
    /* 0-1 背包：动态规划 */
    int knapsackDP(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        // dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少
        // 例如 任取 物品0，物品1 放进容量为4的背包里，最大价值是 dp[1][4]。
        //      两种情况，分别是放物品1 和 不放物品1，我们要取最大值（毕竟求的是最大价值）
        //      dp[1][4] = max(dp[0][4], dp[0][1] + 物品1 的价值)
        // 递归公式： dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
        int[][] dp = new int[n + 1][capacity + 1]; // 初始化 dp 表
        // 状态转移: 二维dp数组01背包先遍历物品还是先遍历背包都是可以的，且第二层for循环是从小到大遍历
        for (int i = 1; i <= n; i++) { // 遍历物品
            for (int c = 1; c <= capacity; c++) { // 遍历背包容量
                if (weight[i - 1] > c) {
                    dp[i][c] = dp[i - 1][c]; // 若超过背包容量，则不选物品 i
                } else {
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - weight[i - 1]] + value[i - 1]); // 不选和选物品 i 这两种方案的较大值
                }
            }
        }
        return dp[n][capacity];
    }
    /* 0-1 背包：空间优化后的动态规划 */
    int knapsackDPComp(int[] weight, int[] value, int capacity) {
        int n = weight.length;
        // 在一维dp数组中，dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]。
        // 递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
        // 倒序遍历是为了保证物品i只被放入一次！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！
        // 从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。
        int[] dp = new int[capacity + 1]; // 初始化 dp 表
        // 状态转移: 一维dp数组01背包只能先遍历物品再遍历背包容量，且第二层for循环是从大到小遍历
        for (int i = 1; i <= n; i++) { // 遍历物品
            for (int c = capacity; c >= 1; c--) { // 遍历背包容量 (倒序遍历)
                if (weight[i - 1] <= c) {
                    dp[c] = Math.max(dp[c], dp[c - weight[i - 1]] + value[i - 1]);// 不选和选物品 i 这两种方案的较大值
                }
            }
        }
        return dp[capacity];
    }
    /**
     * 0/1 Knapsack Problem 0-1背包
     * 有n件物品和一个最多能背重量为w 的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。
     * 每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。
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
        System.out.println("------------");
        System.out.println(knapsack_4());
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
    /**
     *
     */
    static int knapsack_4(){
        int[]  weight = {1, 3, 4};
        int[]  value = {15, 20, 30};
        int bagWeight = 4;

        // 初始化
        int[]  dp = new int[bagWeight + 1];

        for (int i = 0; i < weight.length; i++)// 遍历物品
            for (int j = bagWeight; j >= weight[i]; j--)// 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]); //不取或者取第i个

        return dp[bagWeight];
    }

}
