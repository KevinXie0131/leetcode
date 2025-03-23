package com.answer.string;

public class Q125_Valid_Palindrome {
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
}
