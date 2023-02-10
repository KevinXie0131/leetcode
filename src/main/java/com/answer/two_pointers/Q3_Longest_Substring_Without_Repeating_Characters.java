package com.answer.two_pointers;

import java.util.*;

public class Q3_Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args) {
      //  String s = "abcabcbb";
      //  String s = "abba";
        String s = "tmmzuxt";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * Approach 2: Sliding Window
     */
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();

        int left = 0;
        for(int i = 0; i < arr.length; i++){
            if(map.containsKey(arr[i])){
                left = Math.max(left, map.get(arr[i]) + 1);
            }

            map.put(arr[i], i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

}
