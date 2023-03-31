package com.answer.dynamic_programming;

public class Q647_Palindromic_Substrings {

    /**
     * Approach #2: Dynamic Programming
     */
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int result = 0;

        for (int i = s.length() - 1; i >= 0; i--) { // 注意遍历顺序
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) { // 情况⼀ 和 情况⼆
                        result++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) { // 情况三
                        result++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return result;
    }
    /**
     * Approach #3: Expand Around Possible Centers
     * 双指针法 两边扩散
     */
    public int countSubstrings_2(String s) {
        int result = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            result += extend(s, i, i, s.length()); // 以i为中⼼
            result += extend(s, i, i + 1, s.length()); // 以i和i+1为中⼼
        }
        return result;
    }
    int extend(String s, int i, int j, int n) {
        int res = 0;
        while (i >= 0 && j <= n - 1 && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            res++;
        }
        return res;
    }
}
