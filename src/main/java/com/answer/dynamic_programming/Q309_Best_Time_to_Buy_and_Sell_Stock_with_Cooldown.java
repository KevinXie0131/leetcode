package com.answer.dynamic_programming;

public class Q309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {
    /**
     * 买卖股票的最佳时机含冷冻期
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */
    /**
     * 相对于动态规划：122.买卖股票的最佳时机II ，本题加上了一个冷冻期
     *
     * Approach 1: Dynamic Programming
     * 有cool down时间: 多次买卖一支股票 (必须在再次购买前出售掉之前的股票 / 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     *
     * 具体可以区分出如下四个状态：
     *  状态一：持有股票状态（今天买入股票，或者是之前就买入了股票然后没有操作，一直持有）
     *  不持有股票状态，这里就有两种卖出股票状态
     *      状态二：保持卖出股票的状态（两天前就卖出了股票，度过一天冷冻期。或者是前一天就是卖出股票状态，一直没操作）
     *      状态三：今天卖出股票
     *  状态四：今天为冷冻期状态，但冷冻期状态不可持续，只有一天！
     *
     * 注意这里的每一个状态，例如状态一，是持有股票股票状态并不是说今天一定就买入股票，而是说保持买入股票的状态即：可能是前几天买入的，之后一直没操作，所以保持买入股票的状态。
     *
     * 「今天卖出股票」我是没有单独列出一个状态的归类为「不持有股票的状态」，而本题为什么要单独列出「今天卖出股票」一个状态呢？
     * 因为本题我们有冷冻期，而冷冻期的前一天，只能是「今天卖出股票」状态，如果是「不持有股票状态」那么就很模糊，因为不一定是 卖出股票的操作。
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][4]; // dp[i][j]，第i天状态为j，所剩的最多现金为dp[i][j]。
        dp[0][0] -= prices[0]; // 持有股票

        for (int i = 1; i < n; i++) {
            // 达到买入股票状态（状态一）即：dp[i][0]，有两个具体操作：
            // 操作一：前一天就是持有股票状态（状态一），dp[i][0] = dp[i - 1][0]
            // 操作二：今天买入了，有两种情况
            //      前一天是冷冻期（状态四），dp[i - 1][3] - prices[i]
            //      前一天是保持卖出股票的状态（状态二），dp[i - 1][1] - prices[i]
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3] - prices[i], dp[i - 1][1] - prices[i]) ); // 持有股票
            // 达到保持卖出股票状态（状态二）即：dp[i][1]
            // 操作一：前一天就是状态二
            // 操作二：前一天是冷冻期（状态四）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);                                     // 保持卖出状态(不持有状态之一)
            // 达到今天就卖出股票状态（状态三），即：dp[i][2]. 只有一个操作：昨天一定是持有股票状态（状态一），今天卖出
            dp[i][2] = dp[i - 1][0] + prices[i];                                                 // 卖出股票(不持有状态之一) 之后有冷冻期
            // 达到冷冻期状态（状态四），即：dp[i][3]，只有一个操作：昨天卖出了股票（状态三）
            dp[i][3] = dp[i - 1][2];                                                             // 冷冻期
        }
        return Math. max(dp[n - 1][3], Math.max(dp[n - 1][1], dp[n - 1][2])); // 最后一天如果是冷冻期也可能是最大值
    }
    /**
     * 空间复杂度可以优化
     */
    public int maxProfit_1(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        // bad case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);

        for (int i = 2; i < prices.length; i++) {
            // dp公式
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
    // using 2*4 array for space optimization 定义一个dp[2][4]大小的数组就可以了，就保存前一天的当前的状态
    // 这里稍微说一下，我在LeetCode提交的时候，2*4 2-D array的performance基本上和下面的1-D array performance差不多
    // 都是time: 1ms, space: 40.X MB （其实 length*4 的 2-D array也仅仅是space:41.X MB，看起来不多）
    // 股票累的DP题目大致上都是这样，就当作是一个延伸就好了。真的有人问如何优化，最起码有东西可以讲。
    /**
     1. [i][0] holding the stock
     2. [i][1] after cooldown but stil not buing the stock
     3. [i][2] selling the stock
     4. [i][3] cooldown
     */
    public int maxProfit_3(int[] prices) {
        int len = prices.length;
        int dp[][] = new int [2][4];
        dp[0][0] = -prices[0];

        for(int i = 1; i < len; i++){
            dp[i % 2][0] = Math.max(Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] - prices[i]), dp[(i - 1) % 2][3] - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][3]);
            dp[i % 2][2] = dp[(i - 1) % 2][0] + prices[i];
            dp[i % 2][3] = dp[(i - 1) % 2][2];
        }
        return Math.max(Math.max(dp[(len - 1) % 2][1], dp[(len - 1) % 2][2]), dp[(len - 1) % 2][3]);
    }
    // 一维数组优化
    public int maxProfit_5(int[] prices) {
        int[] dp=new int[4];

        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i <= prices.length - 1; i++){
            // 使用临时变量来保存dp[0], dp[2]
            // 因为马上dp[0]和dp[2]的数据都会变
            int temp = dp[0];
            int temp1 = dp[2];
            dp[0] = Math.max(dp[0], Math.max(dp[3], dp[1]) - prices[i]);
            dp[1] = Math.max(dp[1], dp[3]);
            dp[2] = temp + prices[i];
            dp[3] = temp1;
        }
        return Math.max(dp[3],Math.max(dp[1],dp[2]));
    }
    //另一种解题思路
    public int maxProfit_6(int[] prices) {
        int[][] dp = new int[prices.length + 1][2];
        dp[1][0] = -prices[0];

        for (int i = 2; i <= prices.length; i++) {
            /*
            dp[i][0] 第i天持有股票收益;
            dp[i][1] 第i天不持有股票收益;
            情况一：第i天是冷静期，不能以dp[i-1][1]购买股票,所以以dp[i - 2][1]买股票，没问题
            情况二：第i天不是冷静期，理论上应该以dp[i-1][1]购买股票，但是第i天不是冷静期说明，第i-1天没有卖出股票，
                则dp[i-1][1]=dp[i-2][1],所以可以用dp[i-2][1]买股票，没问题
             */
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i - 1]);
        }

        return dp[prices.length][1];
    }
}
