package com.answer.two_pointers;

import java.util.Arrays;

public class Q392_Is_Subsequence {
    /**
     * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     * A subsequence of a string is a new string that is formed from the original string by deleting some
     * (can be none) of the characters without disturbing the relative positions of the remaining characters.
     * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
     * 判断子序列: 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * s and t consist only of lowercase English letters. 两个字符串都只由小写字符组成。
     * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check
     * one by one to see if t has its subsequence. In this scenario, how would you change your code?
     * 进阶：如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
     * 在这种情况下，你会怎样改变代码？
     */
    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t)); // 输出：false
    }
    /**
     * Approach 2: Two-Pointers 双指针
     */
    public static boolean isSubsequence(String s, String t) {
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int i = 0 ;
        int j = 0 ;
        while(i< source.length && j < target.length){
            if(source[i] == target[j]) {
                i++;

                if(i == source.length){
                    return true;
                }
            }
            j++;
        }

        return i == source.length;
    }
    /**
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）
     * 这道题应该算是编辑距离的入门题目，因为从题意中我们也可以发现，只需要计算删除的情况，不用考虑增加和替换的情况。
     *
     * Dynamic Programming
     * 判断s是不是t的子序列，不要求连续。t中删除任意字符后，是不是变成s
     * 和 Q1143.最长公共子序列 的递推公式基本那就是一样的，区别就是 本题 如果删元素一定是字符串t，而 Q1143.最长公共子序列 是两个字符串都可以删元素。
     */
    public boolean isSubsequence_2(String s, String t) {
        int M = s.length();
        int N = t.length();

        int[][] dp = new int[M + 1][N + 1]; // dp: 以i-1为结尾的字符串s 和 以j-1为结尾的字符串t 的相同子序列的长度
        // dp[i][0]和dp[0][j]都被初始化为0
        // dp[i][0] 表示以下标i-1为结尾的字符串，与空字符串的相同子序列长度，所以为0. dp[0][j]同理。

        // if (s[i - 1] == t[j - 1])
        //   t中找到了一个字符在s中也出现了
        // if (s[i - 1] != t[j - 1])
        //   相当于t要删除元素，继续匹配
        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1; // 找到了一个相同的字符，相同子序列长度自然要在dp[i-1][j-1]的基础上加1
                }else{
                    dp[i][j] = dp[i][j - 1]; // 此时相当于t要删除元素，t如果把当前元素t[j - 1]删除，那么dp[i][j] 的数值就是 看s[i - 1]与 t[j - 2]的比较结果了，即：dp[i][j] = dp[i][j - 1];
                }
            }
        }
        // 如果dp[s.size()][t.size()] 与 字符串s的长度相同说明：s与t的最长相同子序列就是s，那么s 就是 t 的子序列
        return dp[M][N] == M; // 判断是否为s的长度
    }
    // 修改遍历顺序后，可以利用滚动数组，对dp数组进行压缩
    public boolean isSubsequence_3(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 1; i < dp.length; i++) { // 遍历t字符串
            for (int j = 1; j < dp[i].length; j++) { // 遍历s字符串
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[t.length()][s.length()] == s.length();
    }
    // 状态压缩
    public boolean isSubsequence_4(String s, String t) {
        int[] dp = new int[s.length() + 1];
        for (int i = 0; i < t.length(); i ++) {
            // 需要使用上一轮的dp[j - 1]，所以使用倒序遍历
            for (int j = dp.length - 1; j > 0; j --) {
                // i遍历的是t字符串，j遍历的是dp数组，dp数组的长度比s的大1，因此需要减1。
                if (t.charAt(i) == s.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + 1;
                }
            }
        }
        return dp[s.length()] == s.length();
    }
    // 将dp定义为boolean类型，dp[i]直接表示s.substring(0, i)是否为t的子序列
    public boolean isSubsequence_5(String s, String t) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // 表示 “” 是t的子序列

        for (int i = 0; i < t.length(); i ++) {
            for (int j = dp.length - 1; j > 0; j --) {
                if (t.charAt(i) == s.charAt(j - 1)) {
                    dp[j] = dp[j - 1];
                }
            }
        }
        return dp[dp.length - 1];
    }
}
