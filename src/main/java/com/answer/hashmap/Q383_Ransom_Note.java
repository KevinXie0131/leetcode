package com.answer.hashmap;

import java.util.*;

public class Q383_Ransom_Note {
    /**
     * 赎金信
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * 如果可以，返回 true ；否则返回 false 。
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     *
     * 示例 1：
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     * 示例 2：
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     * 示例 3：
     * 输入：ransomNote = "aa", magazine = "aab"
     * 输出：true
     */
    /**
     * 字符统计
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // shortcut
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // 定义一个哈希映射数组, 保存每个字母的出现次数
        int[] result = new int[26];
        char[] mag = magazine.toCharArray();
        char[] rans = ransomNote.toCharArray();
        // 遍历
        for(char ch: mag){
            result[ch -'a']++;
        }
        for(char ch: rans){
            result[ch -'a']--;
            if(result[ch -'a'] < 0){ // 如果数组中存在负数，说明ransomNote字符串中存在magazine中没有的字符
                return false;
            }
        }
        return true;
    }
    /**
     * HashMap
     */
    public boolean canConstruct1(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(Character ch : magazine.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(Character ch : ransomNote.toCharArray()){
            if(map.getOrDefault(ch, 0) == 0){
                return false;
            }
            map.put(ch, map.get(ch) - 1);
        }
        return true;
    }

}
