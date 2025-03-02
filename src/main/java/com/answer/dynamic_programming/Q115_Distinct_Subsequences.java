package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q115_Distinct_Subsequences {
    public static void main(String[] args) {
        String s = "babgbag", t  = "bag";
        numDistinct(s, t);
/*     [[1, 0, 0, 0],
        [1, 1, 0, 0],
        [1, 1, 1, 0],
        [1, 2, 1, 0],
        [1, 2, 1, 1],
        [1, 3, 1, 1],
        [1, 3, 4, 1],
        [1, 3, 4, 5]]*/
    }
    /**
     * Hard
     * Dynamic Programming
     * 字符串s中有多少像t一样的子序列 / 不要求元素连续
     */
    static public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1]; // dp: 以i-1为结尾的字符串s 和 以j-1为结尾的字符串t 的不同子序列的个数
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1; // t为空字符串 有一种方法将s变为空字符串
        }
       // dp[0][j] = 0; // s为空字符串 有0种方法将s变为t
       // dp[0][0] = 1; // s和t为空字符串 有1种方法将s变为t

        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // For example,  bagg & bag, the last g can be used for two times
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
        System.out.println(Arrays.deepToString(dp));
        return dp[s.length()][t.length()];
    }
}
