package com.answer.sliding_window;

public class Q424_Longest_Repeating_Character_Replacement {
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
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
