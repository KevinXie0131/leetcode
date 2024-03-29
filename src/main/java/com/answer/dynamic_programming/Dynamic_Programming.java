package com.answer.dynamic_programming;

import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;

public class Dynamic_Programming {
    /**
     * 1. overlapping subproblems
     * 2. optimal substructure
     * 3. state transition equation
     *
     * There are two ways to implement a DP algorithm:
     * 1. Bottom-up, also known as tabulation.
     * 2. Top-down, also known as memoization.
     *
     * The first characteristic:
     *   What is the minimum cost of doing...
     *   What is the maximum profit from...
     *   How many ways are there to do...
     *   What is the longest possible...
     *   Is it possible to reach a certain point...
     *
     * The second characteristic:
     *   In DP problems, future "decisions" depend on earlier decisions
     */
    /**
     * 1. A function or array that answers the problem for a given state
     * 2. A recurrence relation to transition between states
     * 3. Base cases
     */
    /**
     * 五步曲:
     * 确定dp数组（dp table）以及下标的含义
     * 确定递推公式
     * dp数组如何初始化
     * 确定遍历顺序
     * 举例推导dp数组
     */
    public static void main(String[] args) {
        LocalTime start = LocalTime.now();

        System.out.println(climbStairs(10));
        LocalTime end = LocalTime.now();
        System.out.println(Duration.between(start, end).getSeconds());

        System.out.println(climbStairs_1(10));
        System.out.println(climbStairs_2(10));

    }
    /**
     * top-down recurrence
     */
    // A function that represents the answer to the problem for a given state
    private static int dp(int i) {
        if (i <= 2) return i; // Base cases
        return dp(i - 1) + dp(i - 2); // Recurrence relation
    }

    public static int climbStairs(int n) {
        return dp(n);
    }
    /**
     *memoization
     */
    private static HashMap<Integer, Integer> memo = new HashMap<>();

    private static int dp_1(int i) {
        if (i <= 2) return i;
        // Instead of just returning dp(i - 1) + dp(i - 2), calculate it once and then
        // store it inside a hashmap to refer to in the future
        if (!memo.containsKey(i)) {
            memo.put(i, dp_1(i - 1) + dp_1(i - 2));
        }

        return memo.get(i);
    }

    public static int climbStairs_1(int n) {
        return dp_1(n);
    }
    /**
     * DP
     */
    public static int climbStairs_2(int n) {
        if (n == 1) return 1;

        // An array that represents the answer to the problem for a given state
        int[] dp = new int[n + 1];
        dp[1] = 1; // Base cases
        dp[2] = 2; // Base cases
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // Recurrence relation
        }

        return dp[n];
    }
}
