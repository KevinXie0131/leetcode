package com.answer.sliding_window;

import java.util.*;

public class Q438_Find_All_Anagrams_in_a_String {
    public static void main(String[] args) {
        String s1 = "cbaebabacd";
        String s2 = "abc";
/*        String s1 = "acdcaeccde";
        String s2 ="c";*/
      /*  String s1 = "aa";
        String s2 ="bb";*/

        System.out.println(findAnagrams_2(s1, s2));
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
