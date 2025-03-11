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
     *  到第三层楼梯的状态可以由第二层楼梯 和 到第一层楼梯状态推导出来，那么就可以想到动态规划了
     */
    public static int climbStairs(int n) {
        if(n <= 2){
            return n;
        }

        int[] stairs = new int[n + 1];  // dp[i]： 爬到第i层楼梯，有dp[i]种方法

        stairs[1] = 1; // 不考虑dp[0]如何初始化，只初始化dp[1] = 1，dp[2] = 2，然后从i = 3开始递推，这样才符合dp[i]的定义
        stairs[2] = 2;

        for(int i = 3; i <= n; i++){ // 注意i是从3开始的
            stairs[i] = stairs[i - 1] + stairs[i - 2]; // 第n阶最大方案数量等于第n-1阶和第n-2阶最大方案数量之和
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
        int a = 1; // 用变量记录代替数组
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;  // f(i - 1) + f(i - 2)
            a = b;         // 记录f(i - 1)，即下一轮的f(i - 2)
            b = temp;      // 记录f(i)，即下一轮的f(i - 1)
        }
        return temp;
    }
    /**
     * 另一种形式 优化一下空间复杂度
     * 时间复杂度：O(n) 空间复杂度：O(1)
     */
    public int climbStairs_5(int n) {
        if (n <= 1) return n;
        int[] dp = new int[3];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            int sum = dp[1] + dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[2];
    }
    /**
     * Knapsack 完全背包
     * 一步一个台阶，两个台阶，三个台阶，直到 m个台阶，有多少种方法爬到n阶楼顶
     * 代码中m表示最多可以爬m个台阶。
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
