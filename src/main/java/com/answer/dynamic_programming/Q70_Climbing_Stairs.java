package com.answer.dynamic_programming;

public class Q70_Climbing_Stairs {
    /**
     * Approach 1: Brute Force
     */
    /**
     * Approach 2: Recursion with Memoization
     */
    /**
     * Approach 3: Dynamic Programming
     */
    public int climbStairs(int n) {
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
}
