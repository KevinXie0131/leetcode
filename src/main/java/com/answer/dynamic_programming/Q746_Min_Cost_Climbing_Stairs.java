package com.answer.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;

public class Q746_Min_Cost_Climbing_Stairs {
    public static void main(String[] args) {
        int[] cost = {10,15,20};
        System.out.println(minCostClimbingStairs(cost));
    }
    /**
     * Approach 1: Bottom-Up Dynamic Programming (Tabulation) 动态规划
     * 可以选择从下标为0或下标为1的台阶开始爬楼梯, 也就是相当于跳到 下标 0 或者 下标 1 是不花费体力的，从下标 0 下标1 开始跳就要花费体力了
     */
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1]; // dp[i]的定义：到达第i台阶所花费的最少体力为dp[i]。
        // dp[0] = dp[1] = 0 // 默认第一步都是不花费体力的
        for(int i = 2; i < cost.length + 1; i++){ // 从第一级或者第二级开始跳, 可以有两个途径得到dp[i]，一个是dp[i-1] 一个是dp[i-2]。
            int takeOne = dp[i - 1] + cost[i - 1]; // dp[i - 1] 跳到 dp[i] 需要花费 dp[i - 1] + cost[i - 1]。
            int takeTwo = dp[i - 2] + cost[i - 2]; // dp[i - 2] 跳到 dp[i] 需要花费 dp[i - 2] + cost[i - 2]。
            // 那么究竟是选从dp[i - 1]跳还是从dp[i - 2]跳呢？一定是选最小的
            dp[i] = Math.min(takeOne, takeTwo); // 状态转移：从较小子问题逐步求解较大子问题
        }
        System.out.println(Arrays.toString(dp)); // [0, 0, 10, 15]
        return dp[cost.length]; // 跳到楼顶，即最高级的上面
    }
    /**
     * 空间优化
     */
    public static int minCostClimbingStairs_2(int[] cost) {
        // 从下标为 0 或下标为 1 的台阶开始，因此支付费用为0
        int cur = 0; // 前一个台阶的最少费用
        int pre = 0; // 前两个台阶的最少费用
        // 前两个台阶不需要费用就能上到，因此从下标2开始；因为最后一个台阶需要跨越，所以需要遍历到cost.length
        for(int i = 2; i <= cost.length; i++){ // 从第一级或者第二级开始跳
            int next = Math.min(cur + cost[i - 1], pre + cost[i - 2]);  // 计算到达每一层台阶的最小费用
            pre = cur;  // 记录一下前两位
            cur = next;
        }
        return cur;
    }
    /**
     * Approach 2: Top-Down Dynamic Programming (Recursion + Memoization)
     */
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public int minCostClimbingStairs_1(int[] cost) {
        return minCost(cost.length, cost);
    }

    int minCost(int length, int[] cost){
        if(length <= 1) return 0;

        if(map.containsKey(length)){
            return map.get(length);
        }

        int takeOne = cost[length - 1] + minCost(length - 1, cost);
        int takeTwo = cost[length - 2] + minCost(length - 2, cost);

        map.put(length, Math.min(takeOne, takeTwo));
        return map.get(length);
    }
}
