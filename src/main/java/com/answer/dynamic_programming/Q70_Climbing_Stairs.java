package com.answer.dynamic_programming;

import java.util.HashMap;

public class Q70_Climbing_Stairs {
    /**
     * Approach 1: Brute Force 暴力递归 Time Limit Exceeded
     */
    public int climbStairs_0(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return climbStairs_0(n-1) + climbStairs_0(n-2);
    }
    /**
     * Approach 2: Recursion with Memoization // 带备忘录的递归解法（自顶向下）
     */
    HashMap<Integer, Integer> map = new  HashMap<>(); // 备忘录

    public int climbStairs_1(int n) {
        return climb(n);
    }

    int climb(int i){
        if (i <= 2) return i;

        if (!map.containsKey(i)) {   //先判断有没计算过，即看看备忘录有没有
            map.put(i, climb(i - 1) + climb(i - 2));
        }

        return map.get(i);  //备忘录有，即计算过，直接返回
    }
    /**
     * Approach 3: Dynamic Programming 自底向上的动态规划
     *
     * 动态规划有几个典型特征，最优子结构、状态转移方程、边界、重叠子问题。在青蛙跳阶问题中：
     *  f(n-1)和f(n-2) 称为 f(n) 的最优子结构
     *  f(n)= f（n-1）+f（n-2）就称为状态转移方程
     *  f(1) = 1, f(2) = 2 就是边界啦
     *  比如f(10)= f(9)+f(8),f(9) = f(8) + f(7) ,f(8)就是重叠子问题。
     *
     *  空间复杂度是O(n) 空间复杂度是O(n)
     */
    public static int climbStairs(int n) {
        if(n <= 2){
            return n;
        }

        int[] stairs = new int[n + 1];

        stairs[1] = 1;
        stairs[2] = 2;

        for(int i = 3; i <= n; i++){
            stairs[i] = stairs[i - 1] + stairs[i - 2];
        }

        return stairs[n];
    }
    /**
     * 滚动数组优化
     * 只需要两个变量a和b来存储，就可以满足需求了，因此空间复杂度是O(1)
     */
    public int climbStairs3(int n) {
        if (n<= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
    /**
     * Knapsack 完全背包
     */
    public static void main(String[] args) {
        System.out.println( climbStairs(10));
        System.out.println( climbStairs_2(10));
    }
    public static int climbStairs_2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int m = 2;

        for (int i = 1; i <= n; i++) { // 遍历背包
            for (int j = 1; j <= m; j++) { // 遍历物品
                if (i - j >= 0) {
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[n];
    }
}
