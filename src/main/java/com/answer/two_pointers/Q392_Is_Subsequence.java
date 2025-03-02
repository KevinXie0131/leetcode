package com.answer.two_pointers;

public class Q392_Is_Subsequence {
    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
    /**
     * Approach 2: Two-Pointers
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
     * Dynamic Programming
     * 判断s是不是t的子序列，不要求连续
     * t中删除任意字符后，是不是变成s
     * 和Q1143.最长公共子序列相似
     */
    public boolean isSubsequence_2(String s, String t) {
        int M = s.length();
        int N = t.length();

        int[][] dp = new int[M + 1][N + 1]; // dp: 以i-1为结尾的字符串s 和 以j-1为结尾的字符串t 的相同子序列的长度
        //dp[i][0]和dp[0][j]都被初始化为0

        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = dp[i][j - 1]; // 只能在t中删除字符 / dp[i - 1][j - 1]和dp[i][j - 1]都可以推出dp[i][j]
                }
            }
        }

        return dp[M][N] == M; // 判断是否为s的长度
    }
}
