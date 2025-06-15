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
     * 暴力 + 贪心 Doesn't work for int[] coins = {186,419,83,408} & int amount = 6249.
     * 6249 - 14 * 419 = 383 => 383 - 2 * 186 = 11 => cannot find any coint
     * 贪心想要总硬币数最少，肯定是优先用大面值硬币，所以对 coins 按从大到小排序
     * 注意不是现实中发行的硬币，面值组合规划合理，会有奇葩情况, 不到最优解
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
     * Greedy algorithm + DFS + 剪枝
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
        for(int k = num; k >= 0 && count + k < result; k--){ // count + k < result 剪枝
            dfs(coins, index - 1, count + k , neededAmount - k * coins[index]);
        }
    }
    /**
     * 递归 Time Limit Exceeded
     */
    int res = Integer.MAX_VALUE;
    public int coinChange3(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        findWay(coins, amount, 0);
        // 如果没有任何一种硬币组合能组成总金额，返回 -1。
        if(res == Integer.MAX_VALUE) return -1;
        return res;
    }

    public void findWay(int[] coins,int amount,int count){
        if(amount < 0) return;
        if(amount == 0) res = Math.min(res, count);

        for(int i = 0; i < coins.length; i++){
            findWay(coins,amount - coins[i],count + 1);
        }
    }
    /**
     * 记忆化搜索
     * 使用数组 memo[ ] 来保存节点的值 memo[n] 表示钱币 n 可以被换取的最少的硬币数，不能换取就为 −1
     * 在进行递归的时候，memo[n]被复制了，就不用继续递归了，可以直接的调用
     */
    int[] memo;
    public int coinChange5(int[] coins, int amount) {
        if(coins.length == 0) return -1;
        memo = new int[amount];
        return findWay(coins,amount);
    }
    // memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为-1
    // findWay函数的目的是为了找到 amount数量的零钱可以兑换的最少硬币数量，返回其值int
    public int findWay(int[] coins,int amount){
        if(amount < 0) return -1;
        if(amount == 0) return 0;
        // 记忆化的处理，memo[n]用赋予了值，就不用继续下面的循环
        // 直接的返回memo[n] 的最优值
        if(memo[amount - 1] != 0){
            return memo[amount - 1];
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++){
            int res = findWay(coins,amount - coins[i]);
            if(res >= 0 && res < min){
                min = res + 1; // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
            }
        }
        memo[amount - 1] = (min == Integer.MAX_VALUE ? -1 : min); // 如果没有任何一种硬币组合能组成总金额，返回 -1
        return memo[amount - 1];
    }
    /**
     * 动态规划 完全背包问题
     * 上面的记忆化搜索是先从 memo[amonut−1] 开始，从上到下
     * 动态规划从 memo[0] 开始，从下到上
     */
    public int coinChange6(int[] coins, int amount) {
        // 自底向上的动态规划
        if(coins.length == 0){
            return -1;
        }
        // dp[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for(int i = 1; i <= amount;i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j++){
                if(coins[j] <= i && dp[i - coins[j]] < min){
                    min = dp[i - coins[j]] + 1;
                }
            }
            // dp[i] = (min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min);
            dp[i] = min;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    /**
     * 另一种实现
     * dp[i] 有两种实现的方式，去两者的最小值
     * 包含当前的 coins[i]，那么剩余钱就是 i−coins[i]，这种操作要兑换的硬币数是 dp[i−coins[j]]+1
     * 不包含，要兑换的硬币数是 dp[i]
     */
    public int coinChange7(int[] coins, int amount) {
        // 自底向上的动态规划
        if(coins.length == 0){
            return -1;
        }
        // dp[n]的值： 表示的凑成总金额为n所需的最少的硬币个数
        int[] dp = new int[amount+1];
        // 给dp赋初值，最多的硬币数就是全部使用面值1的硬币进行换
        // amount + 1 是不可能达到的换取数量，于是使用其进行填充
        Arrays.fill(dp,amount + 1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                if(i >= coins[j]){
                    // memo[i]有两种实现的方式，
                    // 一种是包含当前的coins[i],那么剩余钱就是 i-coins[i],这种操作要兑换的硬币数是 memo[i-coins[j]] + 1
                    // 另一种就是不包含，要兑换的硬币数是memo[i]
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == (amount + 1) ? -1 : dp[amount];
    }
    /**
     * another form
     * PS：为啥 dp 数组初始化为 amount + 1 呢，因为凑成 amount 金额的数最多只可能等于 amount（全用 1 元面值的），
     * 所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
     */
    public int coinChange8(int[] coins, int amount) {
        // 数组大小为 amount + 1，初始值也为 amount + 1
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount + 1);
        // base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
