package com.answer.dynamic_programming;

import java.util.*;

public class Q509_Fibonacci_Number {
    /**
     * Approach 1: Recursion
     * This has the potential to be bad in cases that there isn't enough physical memory to handle the increasingly growing stack,
     * leading to a StackOverflowError
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
     * Approach 2: Bottom-Up Approach using Tabulation 动态规划
     * dp 数组的迭代解法
     */
    public int fib_1(int n) {
        if(n <= 1) {
            return n; // If n = 0, return 0. If n = 1, return 1.
        }

        int[] cache = new int[n + 1]; // 0 - n
        cache[0] = 0; // 初始状态
        cache[1] = 1;

        for(int i = 2; i <= n; i++){
            cache[i] = cache[i-1] + cache[i-2]; // 状态转移方程
        }
        return cache[n]; // 终止状态
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
        int prepre = 0;
        int pre = 1;
        for(int i = 2; i <= n; i++){
            res = pre + prepre;
            prepre = pre;
            pre = res;
        }
        return res;
    }
}
