package com.answer.dynamic_programming;

public class Q115_Distinct_Subsequences {
    /**
     * Hard
     * Dynamic Programming
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
                /**
                 *  // 包含两种决策：
                 *  // 不使用 cs[i] 进行匹配，则有 f[i][j] = f[i - 1][j]
                 *  f[i][j] = f[i - 1][j];
                 *  // 使用 cs[i] 进行匹配，则要求 cs[i] == ct[j]，然后有  f[i][j] += f[i - 1][j - 1]
                 *  if (cs[i] == ct[j]) {
                 *     f[i][j] += f[i - 1][j - 1];
                 *  }
                 */
            }
        }

        return dp[s.length()][t.length()];
    }
}
