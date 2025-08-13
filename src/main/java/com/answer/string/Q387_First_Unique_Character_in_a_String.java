package com.answer.string;

import java.util.*;

public class Q387_First_Unique_Character_in_a_String {
    /**
     * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
     * 示例 1：
     *  输入: s = "leetcode"
     *  输出: 0
     * 示例 2:
     *  输入: s = "loveleetcode"
     *  输出: 2
     */
    /**
     * Approach 1: Linear time solution
     * 利用固定大小的数组代替哈希映射, 来记录每个字母的出现次数，从而优化空间使用
     * 通过两次遍历字符串：第一次遍历构建字母计数表，第二次遍历确定首个唯一字符的位置
     */
    public int firstUniqChar(String s) {
        char[] ch = s.toCharArray();
        int[] counter = new int[26];
        for(char c : ch){   //先统计每个字符出现的次数
            counter[c - 'a']++;
        }
        for(int i = 0; i < ch.length; i++){    //然后在遍历字符串s中的字符，如果出现次数是1就直接返回
            if(counter[ch[i] - 'a'] == 1){
                return i;
            }
        }
        return - 1;
    }
    /**
     * 使用哈希表存储频数
     */
    public int firstUniqChar1(String s) {
        char[] array = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : array){  //先统计每个字符的数量
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(int i = 0; i < array.length; i++){ //然后在遍历字符串s中的字符，如果出现次数是1就直接返回
            if(map.get(array[i]) == 1){
                return i;
            }
        }
        return -1;
    }
    /**
     * 遍历字符串 s ，使用哈希表统计 “各字符数量是否 >1 ”。
     * 再遍历字符串 s ，在哈希表中找到首个 “数量为 1 的字符”，并返回。
     */
    public int firstUniqChar3(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc){
            dic.put(c, !dic.containsKey(c));
        }

        for(int i = 0; i < sc.length; i++){
            if(dic.get(sc[i])) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 使用Java的api
     * 循环字符串，当它的某个字符的第一次出现的位置和最后一次出现的位置下标相同则表示该字符只出现了一次，返回第一个首位相同的字符即可
     */
    public int firstUniqChar4(String s) {
        for(int i = 0; i < s.length(); i++){
            if(s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }
}
