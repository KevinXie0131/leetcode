package com.answer.sliding_window;

import java.util.*;

public class Q567_Permutation_in_String {
    /**
     * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
     * In other words, return true if one of s1's permutations is the substring of s2.
     * 字符串的排列: 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的 排列。如果是，返回 true ；否则，返回 false 。
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * s1 and s2 consist of lowercase English letters.
     */
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbfaooab";
        /**
         * 输出：true
         * 解释：s2 包含 s1 的排列之一 ("ba").
         */
        System.out.println(checkInclusion_3(s1, s2));
    }

    /**
     * Approach 5: Sliding Window
     *
     * Instead of making use of a special HashMap datastructure just to store the frequency of occurence of characters,
     * we can use a simpler array data structure to store the frequencies. Given strings contains only lowercase alphabets ('a' to 'z').
     * So we need to take an array of size 26.
     */
    public static boolean checkInclusion(String s1, String s2) {
        int slen = s2.length();
        int plen = s1.length();
        if (slen < plen) {
            return false;
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for(int i = 0; i < plen; i++){
            sCount[s2.charAt(i) - 'a']++;
            pCount[s1.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sCount, pCount)){
            return true;
        }

        for(int i = 0; i < slen - plen; i++){
            sCount[s2.charAt(i) - 'a']--;
            sCount[s2.charAt(i + plen) - 'a']++;

            if(Arrays.equals(sCount, pCount)){
                return true;
            }
        }
        return false;
    }
    /**
     * Approach 2: Using sorting
     */
    public boolean checkInclusion_1(String s1, String s2) {
        s1 = sort(s1);
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if (s1.equals(sort(s2.substring(i, i + s1.length()))))
                return true;
        }
        return false;
    }
    public String sort(String s) {
        char[] t = s.toCharArray();
        Arrays.sort(t);
        return new String(t);
    }
    /**
     * Two pointers
     * Sliding window的思路: 我们在保证区间长度为n的情况下，去考察是否存在一个区间使得cnt的值全为0
     * 反过来，还可以在保证cnt的值不为正的情况下，去考察是否存在一个区间，其长度恰好为n
     */
    public static boolean checkInclusion_3(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }
}
