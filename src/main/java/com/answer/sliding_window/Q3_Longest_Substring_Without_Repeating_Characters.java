package com.answer.sliding_window;

import java.util.*;

public class Q3_Longest_Substring_Without_Repeating_Characters {
    public static void main(String[] args) {
     //   String s = "abcabcbb";
     //   String s = "abba";
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring_1(s));
    }

    /**
     * Approach 3: Sliding Window Optimized
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
    /**
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     */
    public static int lengthOfLongestSubstring_1(String s) {
        int max = 0;
        //   Map<Character, Integer> map = new HashMap<>();
        Integer[] map = new Integer[256];
        Arrays.fill(map, -1);
        char[] arr = s.toCharArray();

        int left = 0;
        for(int i = 0; i < arr.length; i++){
            if(map[(int)arr[i]] != -1){
                left = Math.max(left, map[(int)arr[i]] + 1);
            }

            map[(int)arr[i]] = i;
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
    /**
     * Approach 2: Sliding Window
     */
    public int lengthOfLongestSubstring_2(String s) {
        Map<Character, Integer> chars = new HashMap();

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars.put(r, chars.getOrDefault(r,0) + 1);

            while (chars.get(r) > 1) {
                char l = s.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }

            res = Math.max(res, right - left + 1);

            right++;
        }
        return res;
    }
    /**
     * Approach 1: Brute Force
     */
    public int lengthOfLongestSubstring_3(String s) {
        int n = s.length();

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (checkRepetition(s, i, j)) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res;
    }
    private boolean checkRepetition(String s, int start, int end) {
        Set<Character> chars = new HashSet<>();

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if(chars.contains(c)){
                return false;
            }
            chars.add(c);
        }

        return true;
    }
}
