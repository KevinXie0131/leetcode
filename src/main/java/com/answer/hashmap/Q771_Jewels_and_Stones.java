package com.answer.hashmap;

import java.util.*;

public class Q771_Jewels_and_Stones {
    /**
     * 宝石与石头
     * 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
     * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
     * Letters are case sensitive, so "a" is considered a different type of stone from "A".
     *
     * 示例 1：
     * 输入：jewels = "aA", stones = "aAAbbbb"
     * 输出：3
     * 示例 2：
     * 输入：jewels = "z", stones = "ZZ"
     * 输出：0
     */
    public static void main(String[] args) {
        numJewelsInStones0( "aA", "aAAbbbb");
    }
    /**
     * 哈希数组
     */
    public int numJewelsInStones(String jewels, String stones) {
        boolean[] j = new boolean[128];
        for (char ch : jewels.toCharArray()) {
            j[ch] = true;
        }

        int cnt = 0;
        for (char ch : stones.toCharArray()) {
            if (j[ch]) {
                cnt++;
            }
        }
        return cnt;
    }
    /**
     * another form
     */
    static public int numJewelsInStones0(String jewels, String stones) {
        int count = 0;
        int[] jewel = new int[52];
        for(char ch : jewels.toCharArray()){
            if(ch >= 'a'){
                jewel[ch - 'a']++;
            } else {
                jewel[ch - 'A' + 26]++;
            }
        }
        for(char ch : stones.toCharArray()){
            if(ch >= 'a'){
                if(jewel[ch - 'a'] > 0) count++;
            } else{
                if(jewel[ch - 'A' + 26] > 0) count++;
            }
        }
        return count;
    }
    /**
     * another form
     */
    public int numJewelsInStones4(String jewels, String stones) {
        int m = jewels.length(), n = stones.length();
        int res = 0;
        // 映射:偏移'A': 65 'a': 97
        int[] map = new int[58]; // z = '122' - 65 = 57. so array is [0, 57]
        for (int i = 0; i < m; i++) {
            map[jewels.charAt(i) - 'A']++;
        }
        for (int i = 0; i < n; i++) {
            if (map[stones.charAt(i) - 'A'] > 0) {
                res++;
            }
        }
        return res;
    }
    /**
     * HashMap
     */
    public int numJewelsInStones1(String jewels, String stones){
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : jewels.toCharArray()){
            map.put(ch, 1);
        }
        for(char ch : stones.toCharArray()){
           if(map.containsKey(ch)){
               count++;
           }
        }
        return count;
    }
    /**
     * HashSet
     */
    public int numJewelsInStones2(String jewels, String stones){
        int count = 0;
        Set<Character> set = new HashSet<>();
        for(char ch : jewels.toCharArray()){
            set.add(ch);
        }
        for(char ch : stones.toCharArray()){
            if(set.contains(ch)){
                count++;
            }
        }
        return count;
    }
    /**
     * 位运算
     */
    public int numJewelsInStones5(String jewels, String stones) {
        long xor = 0;
        for (int i = 0; i < jewels.length(); i++) {
            xor |= 1L << (jewels.charAt(i) - 'A');
        }
        int res = 0;
        for (int i = 0; i < stones.length(); i++) {
            if ((xor & (1L << (stones.charAt(i) - 'A'))) != 0) {
                res++;
            }
        }
        return res;
    }
}
