package com.answer.sliding_window;

import java.util.*;

public class Q395_Longest_Substring_with_At_Least_K_Repeating_Characters {
    /**
     * 至少有 K 个重复字符的最长子串
     * 一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     * 如果不存在这样的子字符串，则返回 0。
     * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character
     * in this substring is greater than or equal to k.
     *
     * 示例 1：
     * 输入：s = "aaabb", k = 3
     * 输出：3
     * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2：
     * 输入：s = "ababbc", k = 2
     * 输出：5
     * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     */
    public static void main(String[] args) {
        String s = "aaabb";
        longestSubstring2(s,3);
    }
    /**
     * 滑动窗口
     * 为什么难以确定何时收缩窗口
     * 在这个问题中，我们面临一个困境：当窗口中某些字符的出现次数不足 k 次时，我们无法确定是否应该收缩窗口。因为如果继续扩大窗口，
     * 可能会让这些字符的出现次数达到 k 次，从而满足条件。但如果一直扩大窗口，又会导致窗口变得过大，失去效率。
     *
     * 为了有效利用滑动窗口算法，我们需要引入一个额外的约束条件来帮助我们决定何时收缩窗口。具体来说，我们可以引入一个参数 count，
     * 表示窗口中允许存在的不同字符的种类数。这样，问题就转化为：求每个字符都出现至少 k 次，且仅包含 count 种不同字符的最长子串。
     *
     * 明确窗口的边界：通过限制窗口中不同字符的种类数，我们可以明确何时需要收缩窗口。当窗口中不同字符的种类数超过 count 时，就必须收缩窗口，直到窗口中不同字符的种类数不超过 count。
     * 简化问题：将问题分解为多个子问题，每个子问题对应一个特定的 count 值。通过遍历所有可能的 count 值（从 1 到字符串中不同字符的总数），我们可以找到满足条件的最长子串。
     *
     * 1.遍历所有可能的 count 值：从 1 到字符串中不同字符的总数。
     * 2.滑动窗口：
     *   使用两个指针表示窗口的左右边界。
     *   使用一个哈希表记录窗口中每个字符的出现次数。
     *   使用一个变量unique记录窗口中不同字符的种类数。
     *   使用一个变量valid记录窗口内出现次数到达k次的字符种类数。
     *   当窗口中不同字符的种类数超过 count 时，收缩窗口（移动左指针）。
     *   当窗口中所有字符的出现次数都达到 k 次时，更新最长子串的长度。
     */
    public int longestSubstring(String s, int k) {
        int len = 0;
        for (int i = 1; i <= 26; i++){
            len = Math.max(len, findcount(s, k, i));//枚举窗口内字符种类  //每个循环中，滑动窗口内只能有i个不同字符
        }
        return len;
    }

    public int findcount(String s, int k, int count) {
        int res = 0;
        int left = 0, right = 0;
        int window[] = new int[26];
        int unique = 0;
        int valid = 0;
        //开始滑窗
        while(right < s.length()){
            int c = s.charAt(right++) - 'a'; //更新窗口内字符种类及出现次数为k次的字符种类数
            if(window[c]++ == 0) unique++;
            if(window[c] == k) valid++;

            while(unique > count){//窗口内字符种类数大于count时缩小窗口
                int d = s.charAt(left++) - 'a';
                if(window[d]-- == k) valid--;
                if(window[d] == 0) unique--;
            }

            if(valid == count) {
                res = Math.max(res, right - left); //更新窗口长度
            }
        }
        return res;
    }

    /**
     * 分治（递归）实现思路主要是：
     *  如果当前字符串长度小于k，返回0。
     *  统计当前字符串中每个字符的出现次数。
     *  找到任何出现次数少于k的字符作为分割点，将字符串分割为多个子串。
     *  递归地在每个子串中继续查找满足条件的最长子串。
     *  如果所有字符出现次数均不小于k，当前字符串即为最长满足条件子串。
     */
    public int longestSubstring1(String s, int k) {
        return helper(s.toCharArray(), k, 0, s.length());
    }
    // 左闭右开
    private int helper(char[] str, int k, int start, int end) {
        if (end - start < k) {
            return 0; // 长度不足，不可能符合条件
        }
        // 统计字符出现次数
        int[] count = new int[26];
        for (int i = start; i < end; i++) {
            count[str[i] - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if (count[i] < k && count[i] > 0) {
                for (int j = start; j < end; j++) {
                    if(str[j] == i + 'a'){
                        int left = helper(str, k, start, j);// 递归求取两边最大长度
                        int right = helper(str, k, j + 1, end);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start; // 全部字符出现次数都大于等于k，当前子串即为结果
    }
    /**
     * 同上 左闭右闭
     */
    static public int longestSubstring2(String s, int k) {
        return helper1(s.toCharArray(), k, 0, s.length() - 1);
    }

    static private int helper1(char[] str, int k, int start, int end) {
        if (end - start + 1 < k) {
            return 0; // 长度不足，不可能符合条件
        }
        // 统计字符出现次数
        int[] count = new int[26];
        for (int i = start; i <= end; i++) {
            count[str[i] - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if (count[i] < k && count[i] > 0) {
                for (int j = start; j <= end; j++) {
                    if(str[j] == i + 'a'){
                        int left = helper1(str, k, start, j - 1);// 递归求取两边最大长度
                        int right = helper1(str, k, j + 1, end);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start + 1;
    }
    /**
     * 递归
     */
    public int longestSubstring4(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        HashMap<Character, Integer> counter = new HashMap();
        for (int i = 0; i < s.length(); i++) { // 统计整个字符串的字符个数
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (char c : counter.keySet()) {
            if (counter.get(c) < k) { // 寻找字符个数小于k的字符
                int res = 0;
                for (String t : s.split(String.valueOf(c))) { // 用这个字符把整个字符串分隔为几个部分，问题的解不可能超出这几个子部分
                    res = Math.max(res, longestSubstring(t, k));   // 递归处理每个部分
                }
                return res;
            }
        }
        return s.length();   // 没有找到小于k的字符，说明整个字符串都符合要求，返回其长度
    }
}
