package com.answer.dynamic_programming;

public class Q583_Delete_Operation_for_Two_Strings {
    /**
     * 两个字符串的删除操作
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     * 每步 可以删除任意一个字符串中的一个字符。
     *
     * 示例 1：
     * 输入: word1 = "sea", word2 = "eat"
     * 输出: 2
     * 解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
     */
    /**
     * Dynamic Programming
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数 (每步 可以删除任意一个字符串中的一个字符)
     * 关于最长公共子序列，请读者参考「1143. 最长公共子序列」
     *
     * 本题和动态规划：115.不同的子序列 相比，其实就是两个字符串都可以删除了，情况虽说复杂一些，但整体思路是不变的。
     * 这次是两个字符串可以相互删了，这种题目也知道用动态规划的思路来解
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1]; // dp: 以i-1为结尾的字符串1 和 以j-1为结尾的字符串2 想要达到相等，所需要删除元素的最少次数。

        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i; // word2为空字符串，word1要删除操作i次, 才能相同
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j; // word1为空字符串，word2要删除操作j次, 才能相同
        }
        /**
         * 当word1[i - 1] 与 word2[j - 1]相同的时候，dp[i][j] = dp[i - 1][j - 1];
         * 当word1[i - 1] 与 word2[j - 1]不相同的时候，有三种情况：
         *     情况一：删word1[i - 1]，最少操作次数为dp[i - 1][j] + 1
         *     情况二：删word2[j - 1]，最少操作次数为dp[i][j - 1] + 1
         *     情况三：同时删word1[i - 1]和word2[j - 1]，操作的最少次数为dp[i - 1][j - 1] + 2
         *     那最后当然是取最小值，所以当word1[i - 1] 与 word2[j - 1]不相同的时候，递推公式：dp[i][j] = min({dp[i - 1][j - 1] + 2, dp[i - 1][j] + 1, dp[i][j - 1] + 1});
         */
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 如果相同，则没有影响
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2,                    // 操作次数 + 2
                                  Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)); // 操作次数 + 1
                    // dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1); // 也可以
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
    /**
     * 另一种形式
     * 先求最长公共子序列 参考Q1143 Longest Common Subsequence
     * 再将多余的字符删除就可以了
     *
     * 本题和动态规划：1143.最长公共子序列 基本相同，只要求出两个字符串的最长公共子序列长度即可，
     * 那么除了最长公共子序列之外的字符都是必须删除的，最后用两个字符串的总长度减去两个最长公共子序列的长度就是删除的最少步数。
     */
    public int minDistance2(String word1, String word2) {
        int M = word1.length();
        int N = word2.length();
        int[][] dp = new int[M + 1][N + 1]; // dp数组中存储word1和word2最长相同子序列的长度

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
     * DP - longest common subsequence (用最长公共子序列反推) 先把String转化为char array
     */
    public int minDistance_3(String word1, String word2) {
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();
        int len1 = char1.length;
        int len2 = char2.length;

        int dp[][] = new int [len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(char1[i - 1] == char2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return len1 + len2 - (2 * dp[len1][len2]);// 和leetcode 1143只差在这一行。
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
