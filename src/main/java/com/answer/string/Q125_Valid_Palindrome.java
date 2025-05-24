package com.answer.string;

import java.util.Arrays;

public class Q125_Valid_Palindrome {
    /**
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
     * all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include
     * letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     * 字母和数字都属于字母数字字符。
     * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     */
    public static void main(String[] args) {
    //    String s = "race a car";
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome_6(s));
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
     * 头尾双指针, 在原字符串上直接判断, 只使用 O(1) 空间的算法
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
     * 如果 s[i] 既不是字母也不是数字，右移左指针，也就是把 i 加一。
     * 如果 s[j] 既不是字母也不是数字，左移右指针，也就是把 j 减一。
     * 否则，如果 s[i] 和 s[j] 转成小写后相等，那么把 i 加一，把 j 减一，继续判断。
     * 否则，s 不是回文串，返回 false。
     */
    static public boolean isPalindrome_6(String s) {
        int i = 0 ;
        int j = s.length() - 1;

        while(i < j) {
            if(!Character.isLetterOrDigit(s.charAt(i))){
                i++;
            } else  if(!Character.isLetterOrDigit(s.charAt(j))){
                j--;
            } else if (Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))){
                i++;
                j--;
            } else {
                return false;
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
                if (str[j] == str[i] && (j - i < 2 || dp[i + 1][j - 1])) { // (r - l <= 2 // works too
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
