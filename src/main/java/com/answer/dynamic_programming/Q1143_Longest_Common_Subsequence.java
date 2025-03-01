package com.answer.dynamic_programming;

public class Q1143_Longest_Common_Subsequence {
    /**
     * Approach 3: Dynamic Programming
     * 2-D Dynamic Programming
     * 最长公共子序列 Subsequence：不要求元素连续
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int M = text1.length();
        int N = text2.length();
        int[][] dp = new int[M + 1][N + 1]; // dp: 以[0, i-1]的num1 和 以[0, j-1]的num2 的最长公共子序列长度
        //dp[i][0]和dp[0][j]都被初始化为0

        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]); // 不要求元素连续 / dp[i - 1][j - 1], dp[i][j - 1]和dp[i - 1][j]都可以推出dp[i][j]
                }
            }
        }
        return dp[M][N];
    }
    /**
     * Approach 4: Dynamic Programming with Space Optimization
     * 1-D Dynamic Programming
     */
    public int longestCommonSubsequence_1(String text1, String text2) {

        // If text1 doesn't reference the shortest string, swap them.
        if (text2.length() < text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        // The previous column starts with all 0's and like before is 1
        // more than the length of the first word.
        int[] previous = new int[text1.length() + 1];

        // Iterate through each column, starting from the last one.
        for (int col = text2.length() - 1; col >= 0; col--) {
            // Create a new array to represent the current column.
            int[] current = new int[text1.length() + 1];
            for (int row = text1.length() - 1; row >= 0; row--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    current[row] = 1 + previous[row + 1];
                } else {
                    current[row] = Math.max(previous[row], current[row + 1]);
                }
            }
            // The current column becomes the previous one.
            previous = current;
        }

        // The original problem's answer is in previous[0]. Return it.
        return previous[0];
    }
}
