package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q72_Edit_Distance {
    public static void main(String[] args) {
       String word1 = "horse";
       String word2 = "ros";
       int res = minDistance(word1, word2);
       System.out.println(res);
    }
    /**
     * Hard
     * Dynamic Programming
     *
     * Edit distance is a string metric, a way of quantifying how dissimilar two strings (e.g. words) are to one another,
     * measured by counting the minimum number of operations required to transform one string into the other.
     *
     * three types of transformation operations: addition, deletions, and replacements
     * 编辑距离，也称 Levenshtein 距离，指两个字符串之间互相转换的最少修改次数，通常用于在信息检索和自然语言处理中度量两个序列的相似度
     * 时间复杂度 O(m*n) 空间复杂度 O(m*n)
     */
    static public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。
        int[][] dp = new int[m + 1][n + 1]; // i是word1的字符位 j是word2的字符位 dp状态是word1前i个字符替换到word2前j个字符所需最小步骤
        // 初始化
        for (int i = 1; i <= m; i++) {
            dp[i][0] =  i; // 相当于word2为空，word1要做多少次操作 / 最少编辑步数等于word1的长度 / dp[i][0]就应该是i，对word1里的元素全部做删除操作，即：dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;  // 相当于word1为空，word2要做多少次操作 / 最少编辑步数等于word2的长度 / 同理dp[0][j] = j;
        }
        System.out.println(Arrays.deepToString(dp));
/*      [[0, 1, 2, 3],
         [1, 0, 0, 0],
         [2, 0, 0, 0],
         [3, 0, 0, 0],
         [4, 0, 0, 0],
         [5, 0, 0, 0]]*/
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 因为dp数组有效位从1开始
                // 所以当前遍历到的字符串的位置为i-1 | j-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) { // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                    dp[i][j] = dp[i - 1][j - 1]; // 若两字符相等，无须编辑当前字符，则直接跳过此两字符
                } else {
                    // 最少编辑步数 = 插入、删除、替换这三种操作的最少编辑步数 + 1
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1; // 最后要+1
                }
                /**
                 * if (word1[i - 1] == word2[j - 1])
                 *   不操作, 不用任何编辑
                 * if (word1[i - 1] != word2[j - 1])
                 *   增: dp[i][j] = dp[i][j - 1] + 1 // word2删除一个元素，那么就是以下标i - 1为结尾的word1 与 j-2为结尾的word2的最近编辑距离 再加上一个操作
                 *   删: dp[i][j] = dp[i - 1][j] + 1 // word1删除一个元素，那么就是以下标i - 2为结尾的word1 与 j-1为结尾的word2的最近编辑距离 再加上一个操作。
                 *   换: dp[i][j] = dp[i - 1][j - 1] + 1 // word1替换word1[i - 1]，使其与word2[j - 1]相同，此时不用增删加元素。那么只需要一次替换的操作，就可以让 word1[i - 1] 和 word2[j - 1] 相同。
                 */
            }
        }
        System.out.println(Arrays.deepToString(dp));
/*     [[0, 1, 2, 3],
        [1, 1, 2, 3],
        [2, 2, 1, 2],
        [3, 2, 2, 2],
        [4, 3, 3, 2],
        [5, 4, 4, 3]]*/
        return dp[m][n];
    }
    /**
     * 空间优化
     */
    public int minDistance1(String s, String t) {
        int n = s.length(), m = t.length();
        int[] dp = new int[m + 1];
        // 状态转移：首行
        for (int j = 1; j <= m; j++) {
            dp[j] = j;
        }
        // 状态转移：其余行
        for (int i = 1; i <= n; i++) {
            // 状态转移：首列
            int leftup = dp[0]; // 使用一个变量 leftup 来暂存左上方的解dp[i-1, j-1]
            dp[0] = i;
            // 状态转移：其余列
            for (int j = 1; j <= m; j++) {
                int temp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // 若两字符相等，则直接跳过此两字符
                    dp[j] = leftup;
                } else {
                    // 最少编辑步数 = 插入、删除、替换这三种操作的最少编辑步数 + 1
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), leftup) + 1;
                }
                leftup = temp; // 更新为下一轮的 dp[i-1, j-1]
            }
        }
        return dp[m];
    }
}
