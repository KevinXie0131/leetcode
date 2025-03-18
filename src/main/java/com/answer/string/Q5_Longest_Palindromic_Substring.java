package com.answer.string;

public class Q5_Longest_Palindromic_Substring {
    public static void main(String[] args) {
        String s = "bb";
        String r = longestPalindrome_2(s);
        System.out.println(r);
    }
    /**
     * 暴力解法 - Time Limit Exceeded
     */
    public String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j <= len; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && test.length() > max) {
                    ans = s.substring(i, j);
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    public boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 中心扩展算法
     * 例如 abcda 和 abba
     */
   static  public String longestPalindrome_2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() == 1) return s;
        String result = "";
        for (int i = 0; i <= s.length() - 2; i++) {
            int left = i - 1, right = i + 1; // 奇数
            while(left <= right && left >= 0 && right <= s.length() -1 && s.charAt(left) == s.charAt(right)){
                if(right - left + 1> result.length()) {
                    result = s.substring(left, right + 1);
                }

                left--; right++;
            }

            left = i; right = i + 1;        // 偶数
            while(left <= right && left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)){
                if(right - left + 1 > result.length()) {
                    result = s.substring(left, right + 1);
                }
                left--; right++;
            }

        }
        if(result.length() == 0) return s.substring(0, 1);
        return result;
    }
    /**
     * 方法二：中心扩展算法
     */
    public String longestPalindrome_1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i); // 回文子串为奇数，如abcba
            int len2 = expandAroundCenter(s, i, i + 1); // 回文子串为偶数，如abba
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
    /**
     * 动态规划
     * 参考LeetCode 647. Palindromic Substrings。该题的思路改一下、加一点，就能通过Q5 Longest Palindromic Substring
     */
    public String longestPalindrome_4(String s) {
        // 题目要求要return 最长的回文连续子串，故需要记录当前最长的连续回文子串长度、最终起点、最终终点。
        int finalStart = 0;
        int finalEnd = 0;
        int finalLen = 0;

        char[] chars = s.toCharArray();
        int len = chars.length;

        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (chars[i] == chars[j] && (j - i <= 1 || dp[i + 1][j - 1])) // 简洁版
                    dp[i][j] = true;
                // 和LeetCode 647，差别就在这个if statement。
                // 如果当前[i, j]范围内的substring是回文子串(dp[i][j]) 且(&&) 长度大于当前要记录的最终长度(j - i + 1 > finalLen)
                // 我们就更新 当前最长的连续回文子串长度、最终起点、最终终点
                if (dp[i][j] && j - i + 1 > finalLen) {
                    finalLen = j - i + 1;
                    finalStart = i;
                    finalEnd = j;
                }
            }
        }
        // String.substring这个method的用法是[起点, 终点)，包含起点，不包含终点（左闭右开区间），故终点 + 1。
        return s.substring(finalStart, finalEnd + 1);
    }
}
