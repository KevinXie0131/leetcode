package com.answer.string;

import java.util.*;

public class Q205_Isomorphic_Strings {
    /**
     * 同构字符串
     * Approach 1: Character Mapping with Dictionary
     */
    public boolean isIsomorphic(String s, String t) {
        int[] dict1 = new int[256];
        int[] dict2 = new int[256];
        Arrays.fill(dict1, -1);
        Arrays.fill(dict2, -1);
        for(int i = 0; i < s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if(dict1[c1] == -1 && dict2[c2] == -1){
                dict1[c1] = c2;
                dict2[c2] = c1;
            } else if(!(dict1[c1] == c2 && dict2[c2] == c1)){
                return false;
            }
        }

        return true;
    }
    /**
     * Use HashMap
     */
    public boolean isIsomorphic_1(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
    /**
     * Use only one HashMap
     */
    public boolean isIsomorphic_3(String s, String t) {
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
    }

    private boolean isIsomorphicHelper(String s, String t) {
        int n = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if(map.containsKey(c1) && map.get(c1) != c2){
                return false;
            }
            map.put(c1, c2);
        }

        return true;
    }
}
