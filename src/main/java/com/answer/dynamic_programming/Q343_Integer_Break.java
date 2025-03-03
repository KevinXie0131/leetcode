package com.answer.dynamic_programming;

public class Q343_Integer_Break {
    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }
    /**
     * Dynamic Programming
     *
     * dp[i] 表示正整数 i 拆分成的整数的最大乘积
     * 前面的代码中的正整数 n 变成了这里的正整数 i，用指针 j 去划分 i，分成了 j 和 i - j
     * 遍历所有的 j，i−j 可以选择拆或者不拆，不拆就是i−j ，拆就是dp[i−j]，其实就是对i−j 调用的结果（子问题的解）
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        // dp[0] dp[i] 无法拆分
        dp[2] = 1;

        for(int i = 3; i < n + 1; i++){ // 对于数字 i，它可以分为两份：j 和 i-j，j 的范围是 1 到 i-j
        //  for(int j = 1; j < i; j++) // works too
        //  for(int j = 1; j <= i/2; j++){ // works too 尽可能拆分成相似的数值
            for(int j = 1; j < i - 1; j++){ // 对于 i-j 这部分可以拆或不拆，不拆就是 i-j，拆就是 dp[i-j]
                dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, (i - j) * j));
            }
        }
        return dp[n];
    }

    public int integerBreak_1(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;

        for(int i = 3; i < n + 1; i++){
            int max = 0;
            for(int j = 1; j < i - 1; j++){
                max = Math.max(max, Math.max(dp[i-j]*j, (i-j)*j));
            }
            dp[i] = max;
        }

        return dp[n];
    }
}
