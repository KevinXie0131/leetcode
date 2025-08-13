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
        System.out.println(reverseVowels_1(s));
        String s1 = "leetcode";
        System.out.println(reverseVowels_1(s1));
    }
    /**
     * Approach 1: Two Pointers 双指针
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

        while(left < right){ // 双指针相向而行找元音字符
            while(!set.contains(Character.toLowerCase(ch[left])) && left < right){  // 从左向右找元音字母
                left++;
            }
            while(!set.contains(Character.toLowerCase(ch[right])) && left < right){  // 从右向左找元音字母
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
     * 可以在一个循环中直接实现
     * 这种简化循环的技巧在实际开发中也能用到，只是稍慢于传统的嵌套 while 循环。
     */
    public String reverseVowels2(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] chars = s.toCharArray();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        while (l < r) {
            if (!vowels.contains(chars[l])) {
                l++;
            } else if (!vowels.contains(chars[r])) {
                r--;
            } else {
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }
        }
        return new String(chars);
    }
    /**
     * 一些细节：由于题目没有说字符串中只包含字母，因此在使用数组模拟哈希表时，
     * 我们需要用当前字符减去 ASCII 码的最小值（空字符），而不是 'A' (because test case has "a.")
     */
    static boolean[] hash = new boolean[128];
    static char[] vowels = new char[]{'a','e','i','o','u'};
    static {
        for (char c : vowels) {
            hash[c - ' '] = hash[Character.toUpperCase(c) - ' '] = true;
        }
    }

    public String reverseVowels3(String s) {
        char[] cs = s.toCharArray();
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            if (hash[cs[l] - ' '] && hash[cs[r] - ' ']) {
                swap1(cs, l++, r--);
            }
            else if (!hash[cs[l] - ' ']) {
                l++;
            }
            else if (!hash[cs[r] - ' ']) {
                r--;
            }
        }
        return String.valueOf(cs);
    }

    void swap1(char[] cs, int l, int r) {
        char c = cs[l];
        cs[l] = cs[r];
        cs[r] = c;
    }
    /**
     * Official answer
     * 使用两个指针 i 和 j 对字符串相向地进行遍历
     */
   static public String reverseVowels_1(String s) {
        int start = 0;
        int end = s.length() - 1;
        char[] sChar = s.toCharArray(); // Convert String to char array as String is immutable in Java

        // While we still have characters to traverse
        while (start < end) {
            while (start < s.length () && !isVowel(sChar[start])) {  // Find the leftmost vowel
                start++;
            }

            while (end >= 0 && !isVowel(sChar[end])) {     // Find the rightmost vowel
                end--;
            }
            if (start > end)  break;     // 双指针相遇，退出

            //此时，如果 start<end，那么我们交换 start 和 end 指向的元音字母，否则说明所有的元音字母均已遍历过，就可以退出遍历的过程。
            if (start < end) {    // Swap them if start is left of end
                swap(sChar, start++, end--);
            }
        }
        return new String(sChar); // Converting char array back to String
    }

    // Return true if the character is a vowel (case-insensitive)
    static boolean isVowel(char c) {
        return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u'
                || c == 'A' || c == 'I' || c == 'E' || c == 'O' || c == 'U';
    }

    // Function to swap characters at index x and y
    static void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
    /**
     * 判断给定字符ch是否为元音字母
     */
    private boolean isVowel2(char ch){
        String vowels = "aeiouAEIOU";// 元音字母序列
        return vowels.indexOf(ch) != -1;
    }
}
