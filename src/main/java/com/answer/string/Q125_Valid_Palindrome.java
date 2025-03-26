package com.answer.string;

import java.util.Arrays;

public class Q125_Valid_Palindrome {
    public static void main(String[] args) {
        String s = "race a car";
     //   String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome_5(s));
    }
    /**
     * Palindrome 回文串
     */
    public boolean isPalindrome(String s) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i< s.length(); i++){
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
                sb.append(Character.toLowerCase(c));
            }
        }

        StringBuffer sbReverse = new StringBuffer(sb).reverse(); // 对原字符串取反
        return sb.toString().equals(sbReverse.toString());
    }
    /**
     * 头尾双指针
     */
    public boolean isPalindrome_1(String s) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i< s.length(); i++){
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
                sb.append(Character.toLowerCase(c));
            }
        }
        String str = sb.toString();
        int left = 0;
        int right = str.length() - 1;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
    /**
     * 头尾双指针
     * 使用头尾双指针不断向中间移动直到相遇，同时移动过程中对比两指针指向的字符是否相同。
     */
    public boolean isPalindrome_2(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }
    /**
     * 动态规划
     * 首先我们需要明确dp数组表示什么含义？这里需要二维的dp[][]数组，dp[i][j]表示子串[i..j]是否为回文子串
     * 当我们判断[i..j]是否为回文子串时，只需要判断s[i] == s[j]，同时判断[i+1..j-1]是否为回文子串即可
     * 需要注意有两种特殊情况：[i, i] or [i, i + 1]，即：子串长度为 1 或者 2。所以加了一个条件限定j - i < 2
     * 状态转移方程如下：
     *   dp[i][j] = (s[i] == s[j]) && (j - i < 2 || dp[i + 1][j - 1])
     *
     * 提高后，不能完全通过 Memory Limit Exceeded
     */
    static public boolean isPalindrome_5(String s) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i< s.length(); i++){ // 清理字符
            char c = s.charAt(i);
            if(Character.isLetterOrDigit(c)){
                sb.append(Character.toLowerCase(c));
            }
        }
        // 动态规划
        char[] str = sb.toString().toCharArray();
        boolean[][] dp = new boolean[str.length][str.length];
        int n = str.length;
        int len = 0;
        // 下面两层循环就是求所有子串的固定套路
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (str[j] == str[i] && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    // 其他处理逻辑
                    if (len < j - i + 1) {
                        len = j - i + 1;
                    }
                }
            }
        }
        System.out.println(str.length);
        System.out.println(len);
        System.out.println(Arrays.deepToString(dp));
        return len == str.length;
    }
}
