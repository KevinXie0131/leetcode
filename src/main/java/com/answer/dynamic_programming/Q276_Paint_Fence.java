package com.answer.dynamic_programming;

public class Q276_Paint_Fence {
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
