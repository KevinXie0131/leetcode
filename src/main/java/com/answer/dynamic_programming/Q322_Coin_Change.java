package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q322_Coin_Change {
    public static void main(String[] args) {
       int[] coins = {1,2,5};
       int amount = 11;
        System.out.println(coinChange(coins, amount));
    }
    /**
     * Approach 3 (Dynamic programming - Bottom up) [Accepted]
     * dp[i] is the least steps to reach the i floor
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                if(i - coin >= 0 &&  dp[i - coin] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if(dp[amount] == Integer.MAX_VALUE) return -1;

        System.out.println(Arrays.toString(dp));
        return dp[amount];
        //  return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    /**
     * Backtracking - Time Limit Exceeded
     */
    int res = Integer.MAX_VALUE;
    public int coinChange_1(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        findWay(coins,amount,0);
        // 如果没有任何一种硬币组合能组成总金额，返回 -1。
        if(res == Integer.MAX_VALUE){
            return -1;
        }
        return res;
    }

    public void findWay(int[] coins,int amount,int count){
        if(amount < 0){
            return;
        }
        if(amount == 0){
            res = Math.min(res, count);
        }
        for(int i = 0;i < coins.length;i++){
            findWay(coins,amount - coins[i],count + 1);
        }
    }
    /**
     * Memorization 记忆化搜索
     */
    int[] memo;
    public int coinChange_2(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        memo = new int[amount];
        return findWay(coins,amount);
    }
    // memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为-1
    // findWay函数的目的是为了找到 amount数量的零钱可以兑换的最少硬币数量，返回其值int
    public int findWay(int[] coins,int amount){
        if(amount < 0){
            return -1;
        }
        if(amount == 0){
            return 0;
        }
        // 记忆化的处理，memo[n]用赋予了值，就不用继续下面的循环
        // 直接的返回memo[n] 的最优值
        if(memo[amount-1] != 0){
            return memo[amount-1];
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < coins.length;i++){
            int res = findWay(coins,amount-coins[i]);
            if(res >= 0 && res < min){
                min = res + 1; // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
            }
        }
        memo[amount-1] = (min == Integer.MAX_VALUE ? -1 : min);
        return memo[amount-1];
    }
    /**
     * 二维DP，三层循环
     * 完全背包问题
     */
    int INF = Integer.MAX_VALUE / 2;  // 后续有加法操作，所以要除以2防止溢出
    public int coinChange_4(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for(int[] a: dp) {
            Arrays.fill(a, INF);  // 初始化
        }
        dp[0][0] = 0; // 合法的初始化，其他dp[0][j]均不合法
        for(int i = 1; i < n + 1; i ++) {
            for(int j = 0; j < amount + 1; j ++) {
                int k = 0;
                while (j - k * coins[i - 1] >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * coins[i - 1]] + k);
                    k ++;
                }
            }
        }
        return dp[n][amount] == INF ? -1 : dp[n][amount];
    }
    /**
     * 二维DP，两层循环
     */
    // int INF = Integer.MAX_VALUE / 2;  // 后续有加法操作，所以要除以2防止溢出
    public int coinChange_5(int[] coins, int amount) {
        if(amount == 0) {
            return 0;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for(int[] a: dp) {
            Arrays.fill(a, INF);
        }
        dp[0][0] = 0;  // 合法的初始化，其他dp[0][j]均不合法
        for(int i = 1; i < n + 1; i ++) {
            for(int j = 0; j < amount + 1; j ++) {
                dp[i][j] = dp[i - 1][j];
                if(j - coins[i - 1] >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amount] == INF ? -1 : dp[n][amount];
    }
}
