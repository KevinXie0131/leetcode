package com.answer.dynamic_programming;

public class Q1143_Longest_Common_Subsequence {
    /**
     * 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *  例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     * 示例 1：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     */
    /**
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
     *
     * Approach 3: Dynamic Programming
     * 2-D Dynamic Programming
     * 最长公共子序列 Subsequence：不要求元素连续
     *
     * 本题和动态规划：718. 最长重复子数组 区别在于这里不要求是连续的了，但要有相对顺序，即："ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 时间复杂度: O(n * m)，其中 n 和 m 分别为 text1 和 text2 的长度
     * 空间复杂度: O(n * m)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int M = text1.length();
        int N = text2.length();
        int[][] dp = new int[M + 1][N + 1]; // dp: 以[0, i-1]的num1 和 以[0, j-1]的num2 的最长公共子序列长度
        //dp[i][0]和dp[0][j]都被初始化为0 / text1[0, i-1]和空串的最长公共子序列自然是0，所以dp[i][0] = 0;
        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                // 主要就是两大情况： text1[i - 1] 与 text2[j - 1]相同，text1[i - 1] 与 text2[j - 1]不相同
                //    如果text1[i - 1] 与 text2[j - 1]相同，那么找到了一个公共元素，所以dp[i][j] = dp[i - 1][j - 1] + 1;
                //    如果text1[i - 1] 与 text2[j - 1]不相同，那就看看text1[0, i - 2]与text2[0, j - 1]的最长公共子序列 和 text1[0, i - 1]与text2[0, j - 2]的最长公共子序列，取最大的。
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
     * 另一种形式
     * 可以在一开始的时候就先把text1, text2 转成char[]，之后就不需要有这么多为了处理字串的调整
     */
    public int longestCommonSubsequence_3(String text1, String text2) {
        int M = text1.length();
        int N = text2.length();
        int[][] dp = new int[M + 1][N + 1]; // 先对dp数组做初始化操作

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();
        for(int i = 1; i <= M; i++){
            char ch1 = arr1[i - 1];
            for(int j = 1; j <= N; j++){
                char ch2 = arr2[j - 1];
                if(ch1 == ch2){  // 开始列出状态转移方程
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[M][N];
    }
    /**
     * 一维dp数组
     */
    public int longestCommonSubsequence_2(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        // 多从二维dp数组过程分析
        // 关键在于  如果记录  dp[i - 1][j - 1]
        // 因为 dp[i - 1][j - 1]  <!=>  dp[j - 1]  <=>  dp[i][j - 1]
        int [] dp = new int[n2 + 1];

        for(int i = 1; i <= n1; i++){
            // 这里pre相当于 dp[i - 1][j - 1]
            int pre = dp[0];
            for(int j = 1; j <= n2; j++){
                //用于给pre赋值
                int cur = dp[j];
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[j] = pre + 1; //这里pre相当于dp[i - 1][j - 1]   千万不能用dp[j - 1] !!
                } else{
                    // dp[j]     相当于   dp[i - 1][j]
                    // dp[j - 1] 相当于   dp[i][j - 1]
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                pre = cur;  //更新dp[i - 1][j - 1], 为下次使用做准备
            }
        }
        return dp[n2];
    }
    /**
     * Approach 4: Dynamic Programming with Space Optimization
     * 1-D Dynamic Programming 一维dp数组
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
