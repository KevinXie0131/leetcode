package com.answer.sliding_window;

import java.util.*;

public class Q159_Longest_Substring_with_At_Most_Two_Distinct_Characters {
    /**
     * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
     * Example 1:
     *  Input: "eceba"
     *  Output: 3
     *  Explanation: t is "ece" which its length is 3.
     * Example 2:
     *  Input: "ccaabbb"
     *  Output: 5
     *  Explanation: t is "aabbb" which its length is 5.
     *  至多包含两个不同字符的最长子串
     *  给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
     */
    public static void main(String[] args) {
        String s = "eceba";
        System.out.println(lengthOfLongestSubstringTwoDistinct6(s));
        String s1 = "ccaabbb";
        System.out.println(lengthOfLongestSubstringTwoDistinct6(s1));
    }
    /**
     * Approach 1: Sliding Window 哈希表 + 滑动窗口
     * 维护一个哈希表 cnt 记录当前滑动窗口中各个字符出现的次数，如果哈希表中的键值对个数超过2，则说明当前滑动窗口中包含了超过2个不同的字符，
     * 此时需要移动左指针 j，直到哈希表中的键值对个数不超过2为止，然后更新窗口的最大长度。
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int max = 0;
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;

        for(int i = 0; i < ch.length; i++){
            map.put(ch[i], i); // 将当前字符及其位置存入哈希表

            if(map.size() > 2){
                Map.Entry<Character, Integer> min = Collections.min(map.entrySet(), new Comparator<Map.Entry<Character, Integer>>() {
                    public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                        return entry1.getValue().compareTo(entry2.getValue());  // 找出哈希表中值最小的键值对
                    }
                });
                left = Math.max(left, min.getValue() + 1);// 更新左指针位置
                map.remove(min.getKey());// 移除最左边出现的字符
            }
            max = Math.max(max, i - left + 1); // 更新最大长度
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
    /**
     * 用滑动窗口和哈希表记录当前窗口内字符及其最新下标。
     * 当窗口内超过2个不同字符时，移动左指针并移除最左侧字符，保持窗口最多只有2种不同字符。
     */
    static public int lengthOfLongestSubstringTwoDistinct3(String s) {
        if (s == null || s.isEmpty()) return 0;

        int left = 0, maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, right); // 记录字符c最新出现的位置

            if (map.size() > 2) {  // 如果map中不同字符超过两个，则需要收缩窗口
                int deletedIdx = Integer.MAX_VALUE; // 找到最左侧字符的位置
                for (int idx : map.values()) {
                    deletedIdx = Math.min(deletedIdx, idx);
                }

                map.remove(s.charAt(deletedIdx));// 移除最左侧的字符
                left = deletedIdx + 1;   // 左指针移动到被移除字符的下一个位置
            }
            maxLen = Math.max(maxLen, right - left + 1);  // 更新最大长度
        }
        return maxLen;
    }
    /**
     * another form
     */
    static public int lengthOfLongestSubstringTwoDistinct4(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        int n = s.length();
        int ans = 0;

        for (int right = 0, left = 0; right < n; ++right) {
            char ch = s.charAt(right);
            counter.put(ch, counter.getOrDefault(ch, 0) + 1);

            while (counter.size() > 2) {
                char t = s.charAt(left);
                counter.put(t, counter.get(t) - 1);

                if (counter.get(t) == 0) {
                    counter.remove(t);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
    /**
     * 方法3：滑动窗口 + 计数数组（适用于ASCII字符）
     * 用长度为256的计数数组统计窗口内每个字符出现次数。
     * 窗口内不同字符超过2时，收缩左端，直到只剩2种。
      */
    static public int lengthOfLongestSubstringTwoDistinct6(String s) {
        if (s == null || s.isEmpty()) return 0;

        int[] count = new int[256]; // 假设字符集为ASCII
        int distinct = 0, left = 0, maxLen = 0; // distinct变量记录窗口内不同字符的个数。

        for (int right = 0; right < s.length(); right++) {
            if (count[s.charAt(right)] == 0) {
                distinct++; // 新增一种字符
            }
            count[s.charAt(right)]++;

            while (distinct > 2) {  // 超过2种不同字符，左指针右移收缩窗口
                count[s.charAt(left)]--;
                if (count[s.charAt(left)] == 0) {
                    distinct--;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
