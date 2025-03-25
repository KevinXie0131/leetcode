package com.answer.string;

import java.util.*;

public class Q49_Group_Anagrams {
    /**
     * Approach 1: Categorize by Sorted String
     * 排序法 (时间复杂度：NKlogK 空间复杂度：NK)
     * 将每个字符串排序后的结果作为 key，对原字符串数组进行分组，最后提取分组结果即可。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
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
