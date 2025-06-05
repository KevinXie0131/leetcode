package com.answer.two_pointers;

import java.util.*;

public class Q345_Reverse_Vowels_of_a_String {
    /**
     * Given a string s, reverse only all the vowels in the string and return it.
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
     * 反转字符串中的元音字母
     * 一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
     * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现不止一次。
     * s consist of printable ASCII characters. / s 由 可打印的 ASCII 字符组成
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：s = "IceCreAm"
         *  输出："AceCreIm"
         *  解释：s 中的元音是 ['I', 'e', 'e', 'A']。反转这些元音，s 变为 "AceCreIm".
         */
        String s = "IceCreAm";
        System.out.println(reverseVowels(s));
    }
    /**
     * Approach 1: Two Pointers
     */
    public static String reverseVowels(String s) {
        char[] ch = s.toCharArray();
        int left = 0, right = ch.length - 1;

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        while(left < right){
            while(!set.contains(Character.toLowerCase(ch[left])) && left < right){
                left++;
            }
            while(!set.contains(Character.toLowerCase(ch[right])) && left < right){
                right--;
            }
            char temp = ch[left];
            ch[left] = ch[right];
            ch[right] = temp;
            left++;
            right--;
        }

        return new String(ch);

    }
    /**
     * Official answer
     */
    public String reverseVowels_1(String s) {
        int start = 0;
        int end = s.length() - 1;
        // Convert String to char array as String is immutable in Java
        char[] sChar = s.toCharArray();

        // While we still have characters to traverse
        while (start < end) {
            // Find the leftmost vowel
            while (start < s.length () && !isVowel(sChar[start])) {
                start++;
            }
            // Find the rightmost vowel
            while (end >= 0 && !isVowel(sChar[end])) {
                end--;
            }
            // Swap them if start is left of end
            if (start < end) {
                swap(sChar, start++, end--);
            }
        }

        // Converting char array back to String
        return new String(sChar);
    }
    // Return true if the character is a vowel (case-insensitive)
    boolean isVowel(char c) {
        return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u'
                || c == 'A' || c == 'I' || c == 'E' || c == 'O' || c == 'U';
    }

    // Function to swap characters at index x and y
    void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
}
