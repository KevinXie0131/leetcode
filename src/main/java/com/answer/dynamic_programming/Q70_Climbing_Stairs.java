package com.answer.dynamic_programming;

import java.util.HashMap;

public class Q70_Climbing_Stairs {
    /**
     * Approach 1: Brute Force
     */
    /**
     * Approach 2: Recursion with Memoization
     */
    HashMap<Integer, Integer> map = new  HashMap<>();

    int climb(int i){
        if (i <= 2) return i;

        if (!map.containsKey(i)) {
            map.put(i, climb(i - 1) + climb(i - 2));
        }

        return map.get(i);
    }

    public int climbStairs_1(int n) {
        return climb(n);
    }
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
