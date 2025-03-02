package com.answer.dynamic_programming;

public class Q516_Longest_Palindromic_Subsequence{
    /**
     * Approach 2: Iterative Dynamic Programming
     */
    // dp[i][j]的含义是s[i..=j]的最长回文子串的长度, 最终答案就是dp[0][s.len() - 1], 0 <= i <= j < s.len()
    // 基本状态: 当i==j时, 即只有一个字符, 设置回文长度为1
    // 下面是普通状态转移方法(i < j):
    // 情况1: s[i] == s[j]: 最左边和最右边的字符相同, 我们可以直接将中间部分的最长回文子串长度(dp[i+1][j-1])加2作为当前部分的最长回文子串长度dp[i][j]
    //     => dp[i][j] = dp[i+1][j-1] + 2;
    // 情况2: s[i] != s[j]: 最左边和最右边的字符不同, 没别的好办法, 只能取dp[i][j-1]与dp[i+1][j]的较大值
    //     => dp[i][j] = dp[i][j-1].max(dp[i+1][j])
    // 需要注意的是为了保证这种逻辑下每个前置状态都已计算出, 我们需要倒序遍历字符串
    // 下面的代码同时也使用了滚动数组节省空间, 写法上也比较晦涩难懂, 如果您看不明白也不要担心, 看不明白就对啦，就是想让您看不明白
    /**
     * 动态规划
     * 找出其中最长的回文子序列(不要求连续)，并返回该序列的长度
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements
     * without changing the order of the remaining elements.
     */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()]; // dp: [i, j]回文子序列的长度
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1; // i, j相同，指向同一个元素，一个元素也是回文，长度为1
        }
        for (int i = s.length() - 1; i >= 0; i--) { // dp[i + 1][j - 1]推出[i][j]，所以i从下往上遍历
            for (int j = i + 1; j <= s.length() - 1; j++) { // j = i 已经初始化过了
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2; // [i + 1, j - 1] 是里面的回文子序列
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); //不相等 考虑[i + 1, j]和[i, j - 1]
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
