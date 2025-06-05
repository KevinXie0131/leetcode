package com.answer.sliding_window;

import java.util.*;

public class Q438_Find_All_Anagrams_in_a_String {
    /**
     * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
     * You may return the answer in any order.
     * 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.
     * 字母异位词是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。
     */
    public static void main(String[] args) {
     //   String s1 = "cbaebabacd";
     //   String s2 = "abc";
        /**
         * 输出: [0,6]
         * 解释: 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
         */
/*        String s1 = "acdcaeccde";
        String s2 ="c";*/
      /*  String s1 = "aa";
        String s2 ="bb";*/
        String s1 = "abab";
        String s2 = "ab";
        System.out.println(findAnagrams_5(s1, s2));
    }
    /**
     * 定长滑窗
     */
    static public List<Integer> findAnagrams_5(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        int m = s.length(), n = p.length();
        if(m < n) return res;

        int[] counter1 = new int[26];
        int[] counter2 = new int[26];
        for(int i = 0; i < n; i++){
            counter1[p.charAt(i) - 'a']++;
            counter2[s.charAt(i) - 'a']++;
        }

        int left = 0, right = n;
        while(right < m){
            if(Arrays.equals(counter1, counter2)){
                res.add(left);
            }

            counter2[s.charAt(right) - 'a']++;
            counter2[s.charAt(right - n) - 'a']--;
            left++;
            right++;
        }
        if(Arrays.equals(counter1, counter2)){
            res.add(left);
        }
        return res;
    }
    /**
     * 不定长滑窗
     * 代码实现时，可以把 cntS 和 cntP 合并成一个 cnt：
     *  对于 p 的字母 c，把 cnt[p] 加一。
     *  对于 s′的字母 c，把 cnt[c] 减一。
     *  如果 cnt[c]<0，说明窗口中的字母 c 的个数比 p 的多，右移左端点。
     */
    public static List<Integer> findAnagrams4(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] counter = new int[26]; // 统计 p 的每种字母的出现次数
        for (char c : p.toCharArray()) {
            counter[c - 'a']++;
        }
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int ch = s.charAt(right) - 'a';
            counter[ch]--; // 右端点字母进入窗口

            while (counter[ch] < 0) { // 字母 c 太多了
                counter[s.charAt(left) - 'a']++; // 左端点字母离开窗口
                left++;
            }
            if (right - left + 1 == p.length()) { // s' 和 p 的每种字母的出现次数都相同
                ans.add(left); // s' 左端点下标加入答案
            }
        }
        return ans;
    }
    /**
     * Brute force
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0, j = p.length() - 1; i <= j && j < s.length(); i++, j++){
            if(checkAnagram(s.substring(i, j + 1), p)){
                res.add(i);
            }
        }
        return res;
    }

    public static boolean checkAnagram(String str, String pstr){
        int[] counts = new int[26];
        int length = str.length();

        for (int i = 0; i < length; i++) {
            counts[str.charAt(i) - 'a']++;
        }
        for (int i = 0; i < pstr.length(); i++) {
            counts[pstr.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(counts[i] != 0){
                return false;
            }
        }
        return true;
    }
    /**
     * Approach 2: Sliding Window with Array
     * 使用数组来存储字符串 p 和滑动窗口中每种字母的数量。
     */
    public static List<Integer> findAnagrams_1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int slen = s.length();
        int plen = p.length();
        if (slen < plen) {
            return new ArrayList<Integer>();
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for(int i = 0; i < plen; i++){
            sCount[s.charAt(i) - 'a']++;
            pCount[p.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sCount, pCount)){
            res.add(0);
        }

        for(int i = 0; i < slen - plen; i++){
            sCount[s.charAt(i) - 'a']--;
            sCount[s.charAt(i + plen) - 'a']++;

            if(Arrays.equals(sCount, pCount)){
                res.add(i + 1);
            }
        }
        return res;
    }
    /**
     * Approach 1: Sliding Window with HashMap
     */
    public static List<Integer> findAnagrams_2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int slen = s.length();
        int plen = p.length();
        if (slen < plen) {
            return new ArrayList<Integer>();
        }
        Map<Character, Integer> pCount = new HashMap();
        Map<Character, Integer> sCount = new HashMap();
        for (char ch : p.toCharArray()) {
            pCount.put(ch, pCount.getOrDefault(ch, 0) + 1);
        }

        for(int i = 0; i < slen; i++){
            char ch = s.charAt(i);
            sCount.put(ch, sCount.getOrDefault(ch, 0) + 1);

            if(i >= plen){
                char ch1 = s.charAt(i - plen);
                if(sCount.get(ch1) == 1){
                    sCount.remove(ch1);
                }else{
                    sCount.put(ch1, sCount.get(ch1) - 1);
                }
            }
            if(pCount.equals(sCount)){
                res.add(i - plen + 1);
            }
        }
        return res;
    }
}
