package com.answer.sliding_window;

import java.util.*;

public class Q159_Longest_Substring_with_At_Most_Two_Distinct_Characters {
    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
    }
    /**
     * Approach 1: Sliding Window
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int max = 0;
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for(int i = 0; i < ch.length; i++){
            map.put(ch[i], i);
            if(map.size() > 2){
                Map.Entry<Character, Integer> min = Collections.min(map.entrySet(), new Comparator<Map.Entry<Character, Integer>>() {
                    public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                        return entry1.getValue().compareTo(entry2.getValue());
                    }
                });
                left = Math.max(left, min.getValue() + 1);
                map.remove(min.getKey());
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
    /**
     * Another form of Sliding Window
     */
    public static int lengthOfLongestSubstringTwoDistinct_0(String s) {
        int max = 0;
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for(int i = 0; i < ch.length; i++){
            map.put(ch[i], i);
            if(map.size() > 2){
                Integer index = Collections.min(map.values());
                map.remove(ch[index]);
                left = Math.max(left, index + 1);
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
