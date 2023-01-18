package com.answer.hashmap;

import java.util.Arrays;
import java.util.*;

public class Q242_Valid_Anagram {
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
        for(int i=0; i < s.length(); i++){
            characters[s.charAt(i) - 'a']++;
            characters[t.charAt(i) - 'a']--;
        }
        for(int i=0; i<26; i++){
            if(characters[i] != 0){
                return false;
            }
        }
        return true;
    }
    /**
     *
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
     *
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
            if(map.get(ch) < 0){
                return false;
            }
        }
        return true;
    }
}
