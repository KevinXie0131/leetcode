package com.answer.dynamic_programming;

public class Q1049_Last_Stone_Weight_II {
    /**
     * Dynamic Programming  0-1背包（每个元素只可以使用一次）
     * 参考Q416 Partition Equal Subset Sum
     *
     * 本题其实是尽量让石头分成重量相同的两堆（尽可能相同），相撞之后剩下的石头就是最小的。
     * 问题就是有一堆石头，每个石头都有自己的重量，是否可以 装满 最大重量为 sum / 2的背包。
     * 416. 分割等和子集 是求背包是否正好装满，而本题是求背包最多能装多少。
     * 物品就是石头，物品的重量为stones[i]，物品的价值也为stones[i]
     */
    public int lastStoneWeightII_1(int[] stones) { // 将两堆石头尽可能分成相同重量
        int sum = 0;
        for(int i = 0; i < stones.length; i++){
            sum += stones[i];
        }
        int target = sum / 2;

        int[][] dp = new int[stones.length + 1][target + 1]; // 初始化为0
        //dp[1][j]取决于stones[0] （可以省略）
        for (int j = stones[0]; j <= target; j++) {
            dp[1][j] = stones[0];
        }
        for(int i = 1; i <= stones.length; i++){
            for(int j = 1; j <= target; j++){ //注意是等于
                //不放:dp[i - 1][j] 放:dp[i - 1][j - stones[i]] + stones[i]
                if (j < stones[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i - 1]] + stones[i - 1]);
                }
            }
        }
        return (sum - dp[stones.length][target]) - dp[stones.length][target]; // 每个石头即是重量，也是价值
    }
    /**
     * 2D Dynamic Programming -Accepted
     *
     * 背包容量: target = sum / 2, 每个数组元素的 「价值」 与 「成本」 都是其数值大小 stones[i]，求如何装能装下最多重量石头
     * dp[i][j]：前 i 个石头里面，重量不超过 j 的最佳组合的重量
     * 初始化：先填表格第 1 行，第 1 个石头只能让容积为它自己的背包恰好装满。
     */
    public int lastStoneWeightII_2(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += stones[i];
        }

        int target = sum / 2;
        int[][] dp = new int[n][target + 1];
        for (int j = 0; j <= target; j++) {
            dp[0][j] = j >= stones[0] ? stones[0] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < stones[i]) { // 不选第 i 个元素
                    dp[i][j] = dp[i - 1][j];
                } else { // 选第 i 个元素
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j -stones[i]] + stones[i]);
                }
            }
        }
        //所有石头里面，重量不超过这堆石头重量一半的，最佳组合的重量
        return sum - 2 * dp[n - 1][target];
    }
    /**
     * Dynamic Programming - Using 1D Array
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int i = 0; i < stones.length; i++){
            sum += stones[i];
        }
        int target = sum / 2;

        int[] dp = new int[15001]; // 因为提示中给出1 <= stones.length <= 30，1 <= stones[i] <= 1000，所以最大重量就是30 * 1000 。而我们要求的target其实只是最大重量的一半，所以dp数组开到15000大小就可以了。

        // 如果使⽤⼀维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒叙遍历
        for(int i = 0; i < stones.length; i++){ // 遍历物品
            for(int j= target; j >= stones[i]; j--){ // 遍历背包
                // 01背包的递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
                // 本题则是：dp[j] = max(dp[j], dp[j - stones[i]] + stones[i])
                dp[j] = Math.max(dp[j], dp[j - stones[i]]  + stones[i]);  //两种情况，要么放，要么不放
            }
        }
        // 在计算target的时候，target = sum / 2 因为是向下取整，所以sum - dp[target] 一定是大于等于dp[target]的
        return sum - dp[target] - dp[target]; // 相撞之后剩下的最小石头重量就是 (sum - dp[target]) - dp[target]
    }
}
