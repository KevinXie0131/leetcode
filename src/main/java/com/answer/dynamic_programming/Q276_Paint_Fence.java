package com.answer.dynamic_programming;

public class Q276_Paint_Fence {
    /**
     * 栅栏涂色
     * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
     * 你需要给所有栅栏柱上色，并且保证其中相邻的栅栏柱最多连续两个颜色相同。然后，返回所有有效涂色的方案数。
     * 示例： 输入: n = 3, k = 2
     * 输出: 6
     * 解释：用 2 种颜色涂 3 根栅栏，且不能有超过两根相邻同色，方案有 6 种。
     */
    /**
     * Approach 2: Bottom-Up Dynamic Programming (Tabulation)
     *
     * 1. Use a different color than the previous post:
     *    This means there are (k - 1) * totalWays(i - 1) ways to paint the i-th post a different color than the (i−1)-th post.
     * 2. Use the same color as the previous post:
     *    This means there are 1 * (k - 1) * totalWays(i - 2) ways to paint the (i−1)-th post a different color than the (i−2)-th post
     *
     * totalWays[i] represents the number of ways you can paint i fence posts
     * Initialize totalWays[1] = k and totalWays[2] = k * k.
     * Iterate from 3 to n, using the recurrence relation to populate totalWays:
     *    totalWays[i] = (k - 1) * (totalWays[i - 1] + totalWays[i - 2])
     */
    public int numWays(int n, int k) {
        // Base cases for the problem to avoid index out of bound issues
        if(n == 1) return k;
        if(n == 2) return k * k;

        int totalWays[] = new int[n + 1];
        totalWays[1] = k;
        totalWays[2] = k * k;

        for(int i = 3; i <= n; i++){
            totalWays[i] = (k - 1) * (totalWays[i - 1] + totalWays[i - 2]);
        }

        return totalWays[n];
    }
}
