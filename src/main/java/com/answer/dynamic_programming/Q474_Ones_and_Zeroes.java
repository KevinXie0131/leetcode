package com.answer.dynamic_programming;

public class Q474_Ones_and_Zeroes {

    /**
     * Approach #3 Using Recursion [Time Limit Exceeded]
     */
    public int findMaxForm_1(String[] strs, int m, int n) {
        return calculate(strs, 0, m, n);
    }
    public int calculate(String[] strs, int i, int zeroes, int ones) {
        if (i == strs.length)
            return 0;
        int[] count = countzeroesones(strs[i]);
        int taken = -1;
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
            taken = calculate(strs, i + 1, zeroes - count[0], ones - count[1]) + 1;
        int not_taken = calculate(strs, i + 1, zeroes, ones);
        return Math.max(taken, not_taken);
    }
    public int[] countzeroesones(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }
    /**
     * Approach #4 Using Memoization [Accepted]
     */
    public int findMaxForm_2(String[] strs, int m, int n) {
        int[][][] memo = new int[strs.length][m + 1][n + 1];
        return calculate_2(strs, 0, m, n, memo);
    }
    public int calculate_2(String[] strs, int i, int zeroes, int ones, int[][][] memo) {
        if (i == strs.length)
            return 0;
        if (memo[i][zeroes][ones] != 0)
            return memo[i][zeroes][ones];
        int[] count = countzeroesones(strs[i]);
        int taken = -1;
        if (zeroes - count[0] >= 0 && ones - count[1] >= 0)
            taken = calculate_2(strs, i + 1, zeroes - count[0], ones - count[1], memo) + 1;
        int not_taken = calculate_2(strs, i + 1, zeroes, ones, memo);
        memo[i][zeroes][ones] = Math.max(taken, not_taken);
        return memo[i][zeroes][ones];
    }
    /**
     * Approach #5 2-D Dynamic Programming [Accepted]
     * dp[m][n] denotes the maximum number of strings that can be included in the subset given only i 0's and j 1's are available.
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for(String s : strs){
            int one = 0, zero = 0;
            for(char c : s.toCharArray()){
                if(c == '0'){
                    zero++;
                }else if(c == '1'){
                    one++;
                }
            }

            for(int i = m; i >= zero; i--){
                for(int j = n; j >= one; j--){
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
