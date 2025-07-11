package com.answer.hashmap;

import java.util.*;

public class Q242_Valid_Anagram {
    /**
     * 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * 示例 1:
     *  输入: s = "anagram", t = "nagaram"
     *  输出: true
     * 示例 2:
     *  输入: s = "rat", t = "car"
     *  输出: false
     *
     * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
     */
    public static void main(String[] args) {
        String s ="nl";
        String t = "cx";
        System.out.println( isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] characters = new int[26];
        for(int i = 0; i < s.length(); i++){
            characters[s.charAt(i) - 'a']++;
            characters[t.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(characters[i] != 0){
                return false;
            }
        }
        return true;
    }
    /**
     * 242. 有效的字母异位词 字典解法
     * 时间复杂度O(m+n) 空间复杂度O(1)
     */
    public boolean isAnagram_0(String s, String t) {
        int[] record = new int[26]; // 维护一个长度为 26 的频次数组

        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++; // 并不需要记住字符a的ASCII，只要求出一个相对数值就可以了
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int count: record) {
            if (count != 0) { // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
                return false;
            }
        }
        return true; // record数组所有元素都为零0，说明字符串s和t是字母异位词
    }
    /**
     * t 是 s 的异位词等价于「两个字符串排序后相等」
     */
    public boolean isAnagram_1(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
    /**
     * t 是 s 的异位词等价于「两个字符串中字符出现的种类和次数均相等」
     */
    public boolean isAnagram_2(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i < s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(int i=0; i < t.length(); i++){
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) - 1);
            if(map.get(ch) < 0){ // 剪枝退出更快
                return false;
            }
        }
        return true;
    }

}
