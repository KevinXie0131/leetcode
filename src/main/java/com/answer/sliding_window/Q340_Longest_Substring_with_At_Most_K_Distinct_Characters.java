package com.answer.sliding_window;

import java.util.*;

public class Q340_Longest_Substring_with_At_Most_K_Distinct_Characters {
    /**
     * Given a string, find the length of the longest substring T that contains at most k distinct characters.
     * Example 1:
     *  Input: s = "eceba", k = 2
     *  Output: 3
     *  Explanation: T is "ece" which its length is 3.
     * Example 2:
     *  Input: s = "aa", k = 1
     *  Output: 2
     *  Explanation: T is "aa" which its length is 2.
     * 至多包含 K 个不同字符的最长子串: 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
     */
    public static void main(String[] args) {
        String s1 = "eceba";
        int k1 = 2;
        System.out.println(lengthOfLongestSubstringKDistinct_0(s1, k1 ));
        String s = "aa";
        int k = 1;
        System.out.println(lengthOfLongestSubstringKDistinct_0(s, k ));
    }
    /**
     * Similar with 159. Longest Substring with At Most Two Distinct Characters
     * Approach 1: Sliding Window + Hashmap
     * 用滑动窗口和HashMap记录窗口内字符及其最新下标。当窗口内不同字符超过k时，移除最左侧的字符并右移左指针。每次更新最大长度。
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
     * Approach 2: Sliding Window + Ordered Dictionary
     * 方法2：滑动窗口 + LinkedHashMap（保持插入顺序，移除最早出现的字符）
     */
    public static int lengthOfLongestSubstringKDistinct_0(String s, int k) {
        if (k == 0) {
            return 0;
        }
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        int left = 0, right = 0, max = 0;

        for(int i = 0; i < s.length(); i++){
            Character ch = s.charAt(i);
            // if character is already in the hashmap -delete it, so that after insert it becomes the rightmost element in the hashmap
            if(map.containsKey(ch)){
                map.remove(ch);
            }
            map.put(ch, right);
            right++;

            if(map.size() > k){
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = map.entrySet().iterator().next();
                map.remove(leftmost.getKey());
                // move left pointer of the slidewindow
                left = leftmost.getValue() + 1;
            }
            max = Math.max(max, right - left);
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
