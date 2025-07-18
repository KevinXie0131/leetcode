package com.answer.hashmap;

import java.util.*;

public class Q1002_Find_Common_Characters {
    /**
     * 查找共用字符
     * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
     * Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.
     * 示例 1：
     * 输入：words = ["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     * 输入：words = ["cool","lock","cook"]
     * 输出：["c","o"]
     */
    public static void main(String[] args) {
        String[] words = {"bella","label","roller"};
        commonChars0a(words);
    }
    /**
     * 计数
     */
    public List<String> commonChars0(String[] words) {
        int[] minFreq = new int[26];
        Arrays.fill(minFreq, Integer.MAX_VALUE);

        for(String word : words){
            int[] freq = new int[26];
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                freq[ch - 'a']++;
            }
            for(int i = 0; i < 26; i++){
                minFreq[i] = Math.min(minFreq[i], freq[i]);
            }
        }

        List<String> res = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            // 如果某字符只在某个单词中出现或没出现那么频率会被将为0不被计入结果
            // 其他在每个单词中都出现的字符计数多次
            while(minFreq[i] > 0){
                char ch = (char)('a' + i);
                res.add(String.valueOf(ch));
                minFreq[i]--;
            }
        }
        return res;
    }
    /**
     * HashMap
     */
    static public List<String> commonChars0a(String[] words) {
        Map<Character, Integer> minFreq = new HashMap<>();
        for(int i = 0; i < 26; i++){
            minFreq.put((char)(i + 'a'), Integer.MAX_VALUE);
        }

        for(String word : words){
            Map<Character, Integer> freq = new HashMap<>();
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
            for(int i = 0; i < 26; i++){
                minFreq.put((char)(i + 'a'), Math.min(minFreq.get((char)(i + 'a')), freq.getOrDefault((char)(i + 'a'), 0)));
            }
        }

        List<String> res = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            int count = minFreq.getOrDefault((char)(i + 'a'), 0);
            while(count-- > 0){
                char ch = (char)('a' + i);
                res.add(String.valueOf(ch));
            }
        }
        return res;
    }
    /**
     * 哈希法 (关键字: '小写字符', '出现频率'): 用数组来做哈希法
     * 整体思路就是统计出搜索字符串里26个字符的出现的频率，然后取每个字符频率最小值，最后转成输出格式就可以了。
     */
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<String>();
        int[] hash = new int[26]; // 用来统计所有字符串里字符出现的最小频率
        int[][] allHash = new int[words.length][26];

        for(int i = 0; i < words.length; i++){
            String str = words[i];
            for(int j = 0; j < str.length(); j++){
                char ch = str.charAt(j);
                allHash[i][ch - 'a']++;   // 统计除第一个字符串外字符的出现频率
                if(i == 0){  // 用第一个字符串给hash初始化
                    hash[ch - 'a'] = allHash[i][ch - 'a'];
                }
            }
        }
        for(int i = 1; i < words.length; i++){
            for(int j = 0; j < 26; j++){  // 这是关键所在
                hash[j] = Math.min( hash[j],  allHash[i][j]); // 更新hash，保证hash里统计26个字符在所有字符串里出现的最小次数
            }

        }
        // 将hash统计的字符次数，转成输出形式
        for(int i = 0; i < 26; i++){
            while(hash[i] > 0){ // 注意这里是while，多个重复的字符
                char ch = (char)(i + 'a');
                result.add(String.valueOf(ch));
                hash[i]--;
            }
        }
        return result;
    }
    /**
     * 另一种形式
     */
    public List<String> commonChars1(String[] words) {
        List<String> result = new ArrayList<>();
        if (words.length == 0) return result;
        int[] hash= new int[26]; // 用来统计所有字符串里字符出现的最小频率

        for (int i = 0; i < words[0].length(); i++) { // 用第一个字符串给hash初始化
            hash[words[0].charAt(i)- 'a']++;
        }
        // 统计除第一个字符串外字符的出现频率
        for (int i = 1; i < words.length; i++) {
            int[] hashOtherStr= new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                hashOtherStr[words[i].charAt(j)- 'a']++;
            }
            // 更新hash，保证hash里统计26个字符在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
            }
        }
        // 将hash统计的字符次数，转成输出形式
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) { // 注意这里是while，多个重复的字符
                char c= (char) (i + 'a');
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }
        return result;
    }
}
