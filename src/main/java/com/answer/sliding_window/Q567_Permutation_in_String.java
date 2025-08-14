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
     * 滑动窗口
     * 由于排列不会改变字符串中每个字符的个数，所以只有当两个字符串每个字符的个数均相等时，一个字符串才是另一个字符串的排列。
     */
    public boolean checkInclusion3(String s1, String s2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for(int i = 0; i < s1.length(); i++){
            count1[s1.charAt(i) - 'a']++;
        }

        for(int i = 0; i < s2.length(); i++){
            count2[s2.charAt(i) - 'a']++;

            if(i >= s1.length()){
                count2[s2.charAt(i - s1.length()) - 'a']--;
            }
            if(Arrays.equals(count1, count2)){
                return true;
            }
        }
        return false;
    }
    /**
     * Brute force
     * 时间复杂度是O（n*m） s1长度 * s2长度
     */
    public static boolean checkInclusion8(String s1, String s2) {
        int[] count1 = new int[26];
        for (int i = 0; i < s1.length(); i++) {   // 统计s1中每个字符出现的次数
            char c = s1.charAt(i);
            count1[c - 'a']++;
        }

        int l = 0, r = s1.length(); // 定义滑动窗口的范围是[l,r]，闭区间，长度与s1相等

        while (r <= s2.length()) {
            int[] count2 = new int[26];  // 统计窗口s2[l,r-1]内的元素出现的次数
            for (int i = l; i < r; i++) {
                char c = s2.charAt(i);
                count2[c - 'a']++;
            }
            if(Arrays.equals(count1,count2)) {    // 如果滑动窗口内各个元素出现的次数跟 s1 的元素出现次数完全一致，返回 True
                return true;
            }
            l++;
            r++;
        }
        return false;
    }
    /**
     * Approach 5: Sliding Window
     * Instead of making use of a special HashMap data structure just to store the frequency of occurrence of characters,
     * we can use a simpler array data structure to store the frequencies. Given strings contains only lowercase alphabets ('a' to 'z').
     * So we need to take an array of size 26.
     * 初始化将滑动窗口压满，取得第一个滑动窗口的目标值
     * 继续滑动窗口，每往前滑动一次，需要删除一个和添加一个元素
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
     * 哈希表 + 滑动窗口
     */
    public boolean checkInclusion7(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(char c : s1.toCharArray()){
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        int l = 0;
        for(int r = 0; r < s2.length(); r++) {
            char c = s2.charAt(r);
            map2.put(c, map2.getOrDefault(c, 0) + 1);

            while (map2.getOrDefault(c, 0) > map1.getOrDefault(c, 0)) {
                char ch = s2.charAt(l);
                map2.put(ch, map2.get(ch) - 1);
                if (map2.get(ch) == 0) {
                    map2.remove(ch);
                }
                l++;
            }
            if (map2.equals(map1)) {
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
     * Two pointers 双指针
     * Sliding window的思路: 我们在保证区间长度为n的情况下，去考察是否存在一个区间使得cnt的值全为0
     * 反过来，还可以在保证cnt的值不为正的情况下，去考察是否存在一个区间，其长度恰好为n
     */
    public static boolean checkInclusion_3(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < n; ++i) {
            counter[s1.charAt(i) - 'a']--; // 初始时，仅统计 s1 中的字符，则 cnt 的值均不为正，且元素值之和为 −n
        }
        int left = 0;
        for (int right = 0; right < m; right++) {
            int ch = s2.charAt(right) - 'a';
            counter[ch]++;

            while (counter[ch] > 0) {
                counter[s2.charAt(left) - 'a']--;
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }
}
