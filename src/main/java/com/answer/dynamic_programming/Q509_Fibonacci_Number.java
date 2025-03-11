package com.answer.dynamic_programming;

import java.util.*;

public class Q509_Fibonacci_Number {
    /**
     * Approach 1: Recursion 递归解法
     * This has the potential to be bad in cases that there isn't enough physical memory to handle the increasingly growing stack,
     * leading to a StackOverflowError
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)，算上了编程语言中实现递归的系统栈所占空间
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
    /**
     * Approach 2: Bottom-Up Approach using Tabulation 动态规划 (非压缩状态的版本)
     * dp 数组的迭代解法
     * dp[i]是第i个数值
     */
    public int fib_1(int n) {
        if(n <= 1) {
            return n; // If n = 0, return 0. If n = 1, return 1.
        }
        // dp[i]的定义为：第i个数的斐波那契数值是dp[i]
        int[] dp = new int[n + 1]; // 0 - n
        dp[0] = 0; // 初始状态
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2]; // 状态转移方程
        }
        return dp[n]; // 终止状态
    }
    /**
     * Approach 3: Top-Down Approach using Memoization 记忆化搜索
     */
    private static Map<Integer, Integer> cache = new HashMap<>();
    static{
        cache.put(0, 0);
        cache.put(1, 1);
    }
    public int fib_2(int n) {
        if(n <= 1) return n;

        if(cache.containsKey(n)){
            cache.get(n);
        }
        int res = fib_2(n - 1) + fib_2(n - 2);
        cache.put(n, res);
        return res;
    }
    /**
     * Approach 4: Iterative Bottom-Up Approach
     * 动态规划
     */
    public int fib_3(int n) {
        if(n <= 1) return n;

        int res = 0;;
        int first = 0;
        int second = 1;
        for(int i = 2; i <= n; i++){
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }
    /**
     * 滚动数组
     * 循环d[i]只依赖于前两个数据d[i - 1]和d[i - 2]; 为了节约空间用滚动数组的做法
     */
    public int fib_4(int n) {
        if(n <= 1) return n;

        int[] dp = new int[3];
        dp[1]=1;
        //  dp[2]=1;

        for(int i = 2; i <= n; i++){
            dp[2] = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        System.out.println(Arrays.toString(dp));
        return  dp[2];
    }
}
