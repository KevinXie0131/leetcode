package com.answer.dynamic_programming;

public class Q714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {
    /**
     * Dynamic Programming
     * 无限次地完成交易，但是你每笔交易都需要付手续费。
     * 如果已经购买了一个股票，在卖出它之前不能再继续购买股票
     * 每笔交易你只需要为支付一次手续费
     *
     * 相对于动态规划：122.买卖股票的最佳时机II，本题只需要在计算卖出操作的时候减去手续费就可以了，代码几乎是一样的
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[prices.length][2]; // dp[i][0] 表示第i天持有股票所得最多现金。 dp[i][1] 表示第i天不持有股票所得最多现金

        dp[0][0] -= prices[0];  // 持股票
        dp[0][1] = 0;

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]); // 持有
            // 本题和动态规划：122.买卖股票的最佳时机II 的区别就是这里需要多一个减去手续费的操作。
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee); // 不持有 注意这里需要有手续费了
        }
        return dp[len - 1][1];
    }
    /**
     * 卖出时支付手续费
     */
    public int maxProfit_1(int[] prices, int fee) {
        int len = prices.length;
        // 0 : 持股（买入）
        // 1 : 不持股（售出）
        // dp 定义第i天持股/不持股 所得最多现金
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i] - fee, dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
    /**
     * 买入时支付手续费
     */
    public int maxProfit_2(int[] prices, int fee) {
        int len = prices.length;
        // 0 : 持股（买入）
        // 1 : 不持股（售出）
        // dp 定义第i天持股/不持股 所得最多现金
        int[][] dp = new int[len][2];
        // 考虑买入的时候就支付手续费
        dp[0][0] = -prices[0] - fee;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
    /**
     *  一维数组优化
     */
    public int maxProfit_3(int[] prices, int fee) {
        int[] dp = new int[2];
        dp[0] = -prices[0]; // 持股票
        dp[1] = 0;          // 不持股票
        for (int i = 1; i <= prices.length; i++) {
            dp[0] = Math.max(dp[0], dp[1] - prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1] - fee);
        }
        return dp[1];
    }
    /**
     * 使用 2*2 array
     */
    public int maxProfit_4(int[] prices, int fee) {
        int dp[][] = new int[2][2];
        int len = prices.length;
        //[i][0] = holding the stock
        //[i][1] = not holding the stock
        dp[0][0] = -prices[0];

        for(int i = 1; i < len; i++){
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] + prices[i] - fee);
        }
        return dp[(len - 1) % 2][1];
    }
    /**
     * 贪心算法
     * 本题有了手续费，就要关心什么时候买卖了，因为计算所获得利润，需要考虑买卖利润可能不足以扣减手续费的情况。
     * 如果使用贪心策略，就是最低值买，最高值（如果算上手续费还盈利）就卖
     *
     * 情况一：收获利润的这一天并不是收获利润区间里的最后一天（不是真正的卖出，相当于持有股票），所以后面要继续收获利润。
     * 情况二：前一天是收获利润区间里的最后一天（相当于真正的卖出了），今天要重新记录最小价格了。
     * 情况三：不作操作，保持原有状态（买入，卖出，不买不卖）
     */
    public int maxProfit_5(int[] prices, int fee) {
        int result = 0;
        int minPrice = prices[0]; // 记录最低价格
        for (int i = 1; i < prices.length; i++) {
            // 情况二：相当于买入
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            // 情况三：保持原有状态（因为此时买则不便宜，卖则亏本）
            if (prices[i] >= minPrice && prices[i] <= minPrice + fee) {
                continue;
            }
            // 计算利润，可能有多次计算利润，最后一次计算利润才是真正意义的卖出
            if (prices[i] > minPrice + fee) {
                result += prices[i] - minPrice - fee;
                // 可以看出对情况一的操作，因为如果还在收获利润的区间里，表示并不是真正的卖出，而计算利润每次都要减去手续费，
                // 所以要让minPrice = prices[i] - fee;，这样在明天收获利润的时候，才不会多减一次手续费！
                minPrice = prices[i] - fee; // 情况一，这一步很关键，避免重复扣手续费
            }
        }
        return result;
    }
    /**
     * 贪心思路
     */
    public int maxProfit_6(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int sum = 0;
        for (int price : prices) {
            if (price + fee < buy) {
                buy = price + fee;
            } else if (price > buy){
                sum += price - buy;
                buy = price;
            }
        }
        return sum;
    }
}
