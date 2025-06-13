package com.answer.greedy;

import java.util.Arrays;
import java.util.Collections;

public class Q322_Coin_Change {
    /**
     * 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     */
    public static void main(String[] args) {
    /*    int[] coins = {1,2,5};
        int amount = 11;*/
        int[] coins = {186,419,83,408};
        int amount = 6249;
/*        int[] coins = {411,412,413,414,415,416,417,418,419,420,421,422};
        int amount = 9864;*/
        System.out.println(coinChange_1(coins, amount));
    }
    /**
     * Doesn't work for int[] coins = {186,419,83,408} & int amount = 6249.
     */
    public static int coinChange(int[] coins, int amount) {
        Integer[] coins1 = Arrays.stream( coins ).boxed().toArray( Integer[]::new );
        Arrays.sort(coins1, Collections.reverseOrder());
        int res = 0;
        for(int coin : coins1){
            while(amount > 0 && coin <= amount){
                amount -= coin;
                res++;
            }
        }

        return amount == 0 ? res: -1;
    }
    /**
     * Greedy algorithm + DFS
     * Time Limit Exceeded for int[] coins = {411,412,413,414,415,416,417,418,419,420,421,422} &  int amount = 9864
     */
    static int result = Integer.MAX_VALUE;

    public static int coinChange_1(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, 0 , amount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void dfs(int[] coins, int index, int count, int neededAmount) {
        if(neededAmount == 0){
            result = Math.min(result, count);
            return;
        }
        if(index < 0){
            return;
        }
        int num = neededAmount / coins[index];
        for(int k = num; k >= 0 && count + k < result; k--){
            dfs(coins, index - 1, count + k , neededAmount - k * coins[index]);
        }
    }
}
