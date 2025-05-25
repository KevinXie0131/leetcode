package com.answer.string;

import java.util.*;

public class Q49_Group_Anagrams {
    /**
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.
     * 字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     */
    public static void main(String[] args) {
        String[] strs =  {"eat","tea","tan","ate","nat","bat"};
        groupAnagrams(strs);
    }
    /**
     * Approach 1: Categorize by Sorted String
     * 排序法 (时间复杂度：NKlogK 空间复杂度：NK)
     * 将每个字符串排序后的结果作为 key，对原字符串数组进行分组，最后提取分组结果即可。
     */
   static public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr); // KlogK
            String key = new String(arr);
            //    String key = String.valueOf(arr);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
/*            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            map.get(key).add(s);
            */
            list.add(s);
            map.put(key, list);
/*            if(!map.containsKey(key)){ // 这个也可以
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(s);*/
        }
        return new ArrayList<List<String>>(map.values());
    }
    /**
     * another form
     */
    public List<List<String>> groupAnagrams_1a(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            char[] arr = s.toCharArray();  // 把 s 排序，作为哈希表的 key
            Arrays.sort(arr); // KlogK
            String key = new String(arr);
            // 排序后相同的字符串分到同一组
            map.computeIfAbsent(new String(arr), k -> new ArrayList<String>()).add(s);
        }
        return new ArrayList<List<String>>(map.values()); // 哈希表的 value 保存分组后的结果
    }
    /**
     * Approach 2: Categorize by Count
     * 哈希法 (时间复杂度：NK 空间复杂度：NK)
     */
    public List<List<String>> groupAnagrams_1(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
               /* for (int i = 0; i < 26; i++) { // 有数字加#的组合作为key
                    sb.append(counts[i]);
                    sb.append("#");
                }*/
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
