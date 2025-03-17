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
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
     * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * Hard
     * Dynamic Programming
     * 字符串s中有多少像t一样的子序列 / 不要求元素连续
     *
     * 这道题目如果不是子序列，而是要求连续序列的，那就可以考虑用KMP。
     * 这道题目相对于72. 编辑距离，简单了不少，因为本题相当于只有删除操作，不用考虑替换增加之类的。
     * 但相对于刚讲过的动态规划：392.判断子序列 就有难度了，这道题目双指针法可就做不了
     */
    static public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1]; // dp: 以i-1为结尾的字符串s 子序列中出现以j-1为结尾的字符串t 的不同子序列的个数
        for (int i = 0; i < s.length() + 1; i++) {
            dp[i][0] = 1; // t为空字符串 有一种方法将s变为空字符串
        }
       // dp[0][j] = 0; // s为空字符串 有0种方法将s变为t
       // dp[0][0] = 1; // s和t为空字符串 有1种方法将s变为t
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < t.length() + 1; j++) {
                // 当s[i - 1] 与 t[j - 1]相等时，dp[i][j]可以有两部分组成。
                //    一部分是用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]。即不需要考虑当前s子串和t子串的最后一位字母，所以只需要 dp[i-1][j-1]。
                //    一部分是不用s[i - 1]来匹配，个数为dp[i - 1][j]。
                //    例如: s：bagg 和 t：bag ，s[3] 和 t[2]是相同的，但是字符串s也可以不用s[3]来匹配，即用s[0]s[1]s[2]组成的bag。当然也可以用s[3]来匹配，即：s[0]s[1]s[3]组成的bag。
                // 当s[i - 1] 与 t[j - 1]不相等时，dp[i][j]只有一部分组成，不用s[i - 1]来匹配（就是模拟在s中删除这个元素），即：dp[i - 1][j]
                // 我们求的是 s 中有多少个 t，而不是 求t中有多少个s，所以只考虑 s中删除元素的情况，即 不用s[i - 1]来匹配 的情况
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
