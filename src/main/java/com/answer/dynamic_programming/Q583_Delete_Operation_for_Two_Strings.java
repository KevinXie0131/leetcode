package com.answer.dynamic_programming;

public class Q583_Delete_Operation_for_Two_Strings {
    /**
     * Dynamic Programming
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数 (每步 可以删除任意一个字符串中的一个字符)
     * 关于最长公共子序列，请读者参考「1143. 最长公共子序列」
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1]; // dp: 以i-1为结尾的字符串1 和 以j-1为结尾的字符串2 为相同所需的最小步数

        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i; // word2为空字符串，word1操作i次
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j; // word1为空字符串，word2操作j次
        }

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 如果相同，则没有影响
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2,              // 操作次数 + 2
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)); // 操作次数 + 1
                }
            }
            /**
             * 当word1[i - 1] 与 word2[j - 1]相同的时候，dp[i][j] = dp[i - 1][j - 1];
             * 当word1[i - 1] 与 word2[j - 1]不相同的时候，有三种情况：
             *     情况一：删word1[i - 1]，最少操作次数为dp[i - 1][j] + 1
             *     情况二：删word2[j - 1]，最少操作次数为dp[i][j - 1] + 1
             *     情况三：同时删word1[i - 1]和word2[j - 1]，操作的最少次数为dp[i - 1][j - 1] + 2
             *     那最后当然是取最小值，所以当word1[i - 1] 与 word2[j - 1]不相同的时候，递推公式：dp[i][j] = min({dp[i - 1][j - 1] + 2, dp[i - 1][j] + 1, dp[i][j - 1] + 1});
             */
        }
        return dp[word1.length()][word2.length()];
    }
    /**
     * 另一种形式
     * 先求最长公共子序列 参考Q1143 Longest Common Subsequence
     * 再将多余的字符删除就可以了
     */
    public int minDistance2(String word1, String word2) {
        int M = word1.length();
        int N = word2.length();
        int[][] dp = new int[M + 1][N + 1];

        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){ // 先求最长公共子序列
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return M + N - 2 * dp[M][N]; // 两个字符串长度 减去 最长公共子序列长度*2
    }
    /**
     * Dynamic Programming
     */
    public int minDistance_2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
