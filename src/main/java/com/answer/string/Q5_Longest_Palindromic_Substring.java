package com.answer.string;

public class Q5_Longest_Palindromic_Substring {
    public static void main(String[] args) {
        String s = "cccc";
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
     */
   static  public String longestPalindrome_2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            int left = i - 1, right = i + 1;
            while(left <= right && left >= 0 && right <= s.length() -1 && s.charAt(left) == s.charAt(right)){
                if(right - left> result.length()) {
                    result = s.substring(left, right + 1);
                }

                left++; right--;
            }

            left = i; right = i + 1;
            while(left <= right && left >= 0 && right <= s.length() -1 && s.charAt(left) == s.charAt(right)){
                if(right - left + 1> result.length()) {
                    result = s.substring(left, right + 1);
                }
                left++; right--;
            }

        }

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

}
