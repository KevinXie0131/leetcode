package com.answer.dynamic_programming;

public class Q72_Edit_Distance {
    /**
     * Hard
     * Dynamic Programming
     *
     * Edit distance is a string metric, a way of quantifying how dissimilar two strings (e.g. words) are to one another,
     * measured by counting the minimum number of operations required to transform one string into the other.
     *
     * three types of transformation operations: addition, deletions, and replacements
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        for (int i = 1; i <= m; i++) {
            dp[i][0] =  i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 因为dp数组有效位从1开始
                // 所以当前遍历到的字符串的位置为i-1 | j-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) { // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
                /**
                 * if (word1[i - 1] == word2[j - 1])
                 *     不操作
                 * if (word1[i - 1] != word2[j - 1])
                 *     增   dp[i][j] = dp[i][j - 1] + 1
                 *     删   dp[i][j] = dp[i - 1][j] + 1
                 *     换   dp[i][j] = dp[i - 1][j - 1] + 1
                 */
            }
        }
        return dp[m][n];
    }
}
