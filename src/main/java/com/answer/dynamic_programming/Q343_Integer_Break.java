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
        int[] dp = new int[n + 1]; // dp[i]：分拆数字i，可以得到的最大乘积为dp[i]。
        // dp[0] dp[i] 无法拆分 拆分数字2，得到的最大乘积是1
        dp[2] = 1;
        // 从1遍历j，然后有两种渠道得到dp[i].
        // 一个是j * (i - j) 直接相乘。
        // 一个是j * dp[i - j]，相当于是拆分(i - j)，对这个拆分不理解的话，可以回想dp数组的定义。
        // 也可以这么理解，j * (i - j) 是单纯的把整数拆分为两个数相乘，而j * dp[i - j]是拆分成两个以及两个以上的个数相乘。
        for(int i = 3; i < n + 1; i++){ // 对于数字 i，它可以分为两份：j 和 i-j，j 的范围是 1 到 i-j
        //  for(int j = 1; j < i; j++) // works too
        //  for(int j = 1; j <= i/2; j++){ // works too 尽可能拆分成相似的数值 / 因为拆分一个数n 使之乘积最大，那么一定是拆分成m个近似相同的子数相乘才是最大的。
            for(int j = 1; j < i - 1; j++){ // 对于 i-j 这部分可以拆或不拆，不拆就是 i-j，拆就是 dp[i-j]
                dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, (i - j) * j)); // 那么在取最大值的时候，为什么还要比较dp[i]呢？因为在递推公式推导的过程中，每次计算dp[i]，取最大的而已。
            }
        }
        return dp[n];
    }
    /**
     * 另一种形式
     */
    public int integerBreak_1(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;

        for(int i = 3; i < n + 1; i++){
            int max = 0;
            for(int j = 1; j < i - 1; j++){
                // 这里的 j 其实最大值为 i-j,再大只不过是重复而已，
                // 并且，在本题中，我们分析 dp[0], dp[1]都是无意义的，
                // j 最大到 i-j,就不会用到 dp[0]与dp[1]
                max = Math.max(max, Math.max(dp[i-j]*j, (i-j)*j));
                // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                // 而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数, 再相乘。
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
