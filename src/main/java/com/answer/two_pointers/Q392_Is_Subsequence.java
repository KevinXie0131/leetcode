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
     */
    public boolean isSubsequence_2(String s, String t) {
        int M = s.length();
        int N = t.length();

        int[][] dp = new int[M + 1][N + 1];

        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[M][N] == M;
    }
}
