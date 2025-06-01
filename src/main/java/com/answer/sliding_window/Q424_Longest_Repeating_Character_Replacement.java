package com.answer.sliding_window;

public class Q424_Longest_Repeating_Character_Replacement {
    /**
     * You are given a string s and an integer k. You can choose any character of the string and change it to any other
     * uppercase English character. You can perform this operation at most k times.
     * Return the length of the longest substring containing the same letter you can get after performing the above operations.
     * 替换后的最长重复字符
     * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
     * 在执行上述操作后，返回 包含相同字母的最长子字符串的长度。
     * s consists of only uppercase English letters.
     */
    public static void main(String[] args) {
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println(characterReplacement(s1, k1));
        /**
         * 输出：4
         * 解释：用两个'A'替换为两个'B',反之亦然。
         */
        String s = "AABABBA";
        int k = 1;
        /**
         * 输出：4
         * 解释：将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。子串 "BBBB" 有最长重复字母, 答案为 4。
         */
        System.out.println(characterReplacement(s, k));
    }

    /**
     * Similar with Q487 Max Consecutive Ones II
     *
     * Approach 3: Sliding Window (Fast)
     *
     * 首先需要区分两个概念：子串（子数组） 和 子序列。这两个名词经常在题目中出现，非常有必要加以区分。
     * 子串sub-string（子数组 sub-array）是连续的，而子序列 subsequence 可以不连续。
     *
     * 模板的思想是：以右指针作为驱动，拖着左指针向前走。右指针每次只移动一步，而左指针在内部 while 循环中每次可能移动多步。
     * 右指针是主动前移，探索未知的新区域；左指针是被迫移动，负责寻找满足题意的区间
     */
    public static int characterReplacement(String s, int k) {
        int max = 0, maxCount = 0;
        int left = 0, right = 0;
        char[] ch = s.toCharArray();
        int len = ch.length;
        int[] counter = new int[26];

        while(right < len){
            // if 'A' is 0, then what is the relative order
            // or offset of the current character entering the window
            // 0 is 'A', 1 is 'B' and so on
            char c = ch[right];
            counter[c - 'A']++;
            maxCount = Math.max(maxCount, counter[c - 'A']);  // the maximum frequency we have seen in any window yet

            // move the start pointer towards right if the current
            // window is invalid
            while(right - left >= maxCount + k){
                counter[ch[left] - 'A']--;  // decrease its frequency
                left++; // move the start pointer forward
            }
            // the window is valid at this point, note down the length
            // size of the window never decreases
            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }
}
