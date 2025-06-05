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
      ///  System.out.println(characterReplacement(s1, k1));
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
        System.out.println(characterReplacement1(s, k));
    }
    /**
     * 双指针（滑动窗口）
     * 首先需要区分两个概念：子串（子数组） 和 子序列。这两个名词经常在题目中出现，非常有必要加以区分。
     * 子串sub-string（子数组 sub-array）是连续的，而子序列 subsequence 可以不连续。
     *
     * 模板的思想是：以右指针作为驱动，拖着左指针向前走。右指针每次只移动一步，而左指针在内部 while 循环中每次可能移动多步。
     * 右指针是主动前移，探索未知的新区域；左指针是被迫移动，负责寻找满足题意的区间
     *
     * 思路：找到一个子串，它需满足除最长连续字串外的其它字符不超过k个；返回所有这样子串中的最长一个
     */
    static public int characterReplacement1(String s, int k) {
        int len = s.length();
        int left = 0, right = 0, ans = 0;
        int historyCharMax = 0; // 记录字串中最长的连续相同字符的长度（可能中间有间隔，但间隔次数不超过k）
        int[] map = new int[26]; // 记录每个字符的数量
        // [left, right) 内最多替换 k 个字符可以得到只有一种字符的子串
        while (right < len) {
            int index = s.charAt(right) - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]); // 看是之前的连续字符长，还是新加入的字符构成的连续字符长
            // 当前子串总长为right - left + 1，如果抛开最长连续字符后的其它字符(异类)的个数超过了k个，
            // 那么左边界右移，保证滑动窗口中非最长连续字符外的其它字符(异类)不超过k个，
            // 同时被移走的字符左边界字符的数量计数减1
            while (right - left + 1 > historyCharMax + k) { // 说明此时 k 不够用
                map[s.charAt(left) - 'A']--;                // 把其它不是最多出现的字符替换以后，都不能填满这个滑动的窗口，这个时候须要考虑左边界向右移动
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++; // 只要当前子串中的异类没超过k，那么就继续增长该字串
        }
        return ans;
    }
    /**
     * Similar with Q487 Max Consecutive Ones II
     * Approach 3: Sliding Window (Fast)
     * 令 l 为符合条件的子串的左端点，r 为符合条件的子串的右端点。
     * 使用 cnt 统计 [l,r] 范围的子串中每个字符串出现的次数。
     * 对于合法的子串而言，必然有 sum(所有字符的出现次数) - max(出现次数最多的字符的出现次数）= other(其他字符的出现次数) <= k。
     */
    public static int characterReplacement(String s, int k) {
        int max = 0;
        int maxCount = 0; //当前窗口内字母的最大次数
        int left = 0, right = 0;
        char[] ch = s.toCharArray();
        int len = ch.length;
        int[] counter = new int[26]; //记录窗口内各字母出现的次数

        while(right < len){
            // if 'A' is 0, then what is the relative order or offset of the current character entering the window 0 is 'A', 1 is 'B' and so on
            char c = ch[right]; //当前字母
            counter[c - 'A']++;  //当前字母出现的次数
            maxCount = Math.max(maxCount, counter[c - 'A']);  // the maximum frequency we have seen in any window yet //窗口内所出现字母的最大次数

            // move the start pointer towards right if the current window is invalid
            while(right - left + 1 - maxCount > k){ //非最大次数的字母出现次数 > k
                counter[ch[left] - 'A']--;  // decrease its frequency //窗口左移,移除元素，次数-1
                left++; // move the start pointer forward
            }
            // the window is valid at this point, note down the length size of the window never decreases
            max = Math.max(max, right - left + 1);
            right++; //窗口右移
        }

        return max;
    }
}
