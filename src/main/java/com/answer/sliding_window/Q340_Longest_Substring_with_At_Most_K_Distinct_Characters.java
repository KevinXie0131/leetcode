package com.answer.sliding_window;

import java.util.*;

public class Q340_Longest_Substring_with_At_Most_K_Distinct_Characters {
    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(lengthOfLongestSubstringKDistinct_1(s, 2 ));
    }
    /**
     * Similar with 159. Longest Substring with At Most Two Distinct Characters
     *
     * Approach 1: Sliding Window + Hashmap
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int max = 0;
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for(int i = 0; i < ch.length; i++){
            map.put(ch[i], i);
            if(map.size() > k){
                Integer index = Collections.min(map.values());
                map.remove(ch[index]);
                left = Math.max(left, index + 1);
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
    /**
     * Two pointers
     */
    public static int lengthOfLongestSubstringKDistinct_1(String s, int k) {
        if (k == 0) {
            return 0;
        }
        Deque<Character> queue = new ArrayDeque<>();
        int[] arr = new int[256];
        int count = 0;
        int max = 0;

        for(int i = 0; i < s.length(); i++){
            if(arr[s.charAt(i)] == 0){
                count++;
                while(count > k){
                    char temp = queue.poll();
                    arr[temp]--;
                    if(arr[temp] == 0){
                        count--;
                    }
                }
            }

            queue.offer(s.charAt(i));
            arr[s.charAt(i)]++;
            max = Math.max(max, queue.size());
        }

        return max;
    }
}
