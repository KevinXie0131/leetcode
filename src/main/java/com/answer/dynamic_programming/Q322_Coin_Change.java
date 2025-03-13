package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q322_Coin_Change {
    public static void main(String[] args) {
       int[] coins = {1,2,5};
       int amount = 11;
        System.out.println(coinChange(coins, amount));
    }
    /**
     * 完全背包的一种特例：零钱兑换
     * 在 0-1 背包问题中，每种物品只有一个，因此将物品i放入背包后，只能从前i-1个物品中选择。
     * 在完全背包问题中，每种物品的数量是无限的，因此将物品放入背包后，仍可以从前i个物品中选择。
     *
     * 1.两道题可以相互转换，“物品”对应“硬币”、“物品重量”对应“硬币面值”、“背包容量”对应“目标金额”。
     * 2.优化目标相反，完全背包问题是要最大化物品价值，零钱兑换问题是要最小化硬币数量。
     * 3.完全背包问题是求“不超过”背包容量下的解，零钱兑换是求“恰好”凑到目标金额的解。
     *
     * 本题要求最小值，因此需将运算符max()更改为min()
     * 优化主体是硬币数量而非商品价值，因此在选中硬币时执行+1即可。
     */
    int coinChange5(int[] coins, int amt) {
        int n = coins.length;
        int MAX = amt + 1;

        int[][] dp = new int[n + 1][amt + 1];// 初始化 / dp表: 前n种硬币能够凑出金额amt的最少硬币数量
        for (int a = 1; a <= amt; a++) { // 状态转移：首行首列
            dp[0][a] = MAX; // 当无硬币时，无法凑出任意>0的目标金额，即是无效解 我们采用数字amt + 1来表示无效解
        }
        for (int i = 1; i <= n; i++) { // 状态转移：其余行和列
            for (int a = 1; a <= amt; a++) {
                if (coins[i - 1] > a) {
                    dp[i][a] = dp[i - 1][a];  // 若超过目标金额，则不选硬币 i
                } else {
                    dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - coins[i - 1]] + 1);  // 不选和选硬币 i 这两种方案的较小值
                }
            }
        }
        return dp[n][amt] != MAX ? dp[n][amt] : -1;
    }
    /**
     * 零钱兑换：空间优化后的动态规划
     * 题目中说每种硬币的数量是无限的，可以看出是典型的完全背包问题。
     */
    int coinChange6(int[] coins, int amt) {
        int n = coins.length;
        int MAX = amt + 1; //初始化dp数组为最大值

        int[] dp = new int[amt + 1]; // dp[j]：凑足总额为j所需钱币的最少个数为dp[j]
        Arrays.fill(dp, MAX); // dp[j]必须初始化为一个最大的数，否则就会在min(dp[j - coins[i]] + 1, dp[j])比较的过程中被初始值覆盖。
        dp[0] = 0; // 凑足总金额为0所需钱币的个数一定是0
        // 状态转移
        // 本题的两个for循环的关系是：外层for循环遍历物品，内层for遍历背包或者外层for遍历背包，内层for循环遍历物品都是可以的
        // 本题是要求最少硬币数量，硬币是组合数还是排列数都无所谓！所以两个for循环先后顺序怎样都可以, 都不影响钱币的最小个数
        // 遍历顺序为：coins（物品）放在外循环，target（背包）在内循环。且内循环正序。(内外循环交换也可以通过提交)
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) { // 完全背包。所以遍历的内循环是正序
                if (coins[i - 1] > a) {
                    dp[a] = dp[a]; // 若超过目标金额，则不选硬币 i
                } else {
                    dp[a] = Math.min(dp[a], dp[a - coins[i - 1]] + 1);    // 不选和选硬币 i 这两种方案的较小值
                }
            }
        }
        return dp[amt] != MAX ? dp[amt] : -1;
    }
    /**
     * Approach 3 (Dynamic programming - Bottom up) [Accepted]
     * dp[i] is the least steps to reach the i floor
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);   //初始化dp数组为最大值
        dp[0] = 0; //当金额为0时需要的硬币数目为0

        for(int i = 1; i <= amount; i++){
            for(int coin : coins){
                // 如果dp[j - coins[i]]是初始值则跳过
                if(i - coin >= 0 &&  dp[i - coin] != Integer.MAX_VALUE){ //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);  //选择硬币数目最小的情况
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
