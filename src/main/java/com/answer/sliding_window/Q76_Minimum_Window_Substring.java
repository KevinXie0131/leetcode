package com.answer.sliding_window;

import java.util.*;

public class Q76_Minimum_Window_Substring {
    /**
     * Given two strings s and t of lengths m and n respectively, return the minimum window substring of
     * s such that every character in t (including duplicates) is included in the window.
     * If there is no such substring, return the empty string "".
     * 最小覆盖子串: 一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，
     * 则返回空字符串 "" 。
     * 注意：
     *    对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     *    如果 s 中存在这样的子串，我们保证它是唯一的答案。
     */
    public static void main(String[] args) { // Hard 困难
       String s = "ADOBECODEBANC", t = "ABC";
       System.out.println(minWindow4(s, t)); // Output: "BANC"
    }
    /**
     * 滑动窗口
     * 枚举 s 子串的右端点 right（子串最后一个字母的下标），如果子串涵盖 t，就不断移动左端点 left 直到不涵盖为止。
     * 在移动过程中更新最短子串的左右端点。
     * 具体来说：
     *  初始化 ansLeft=−1, ansRight=m，用来记录最短子串的左右端点，其中 m 是 s 的长度。
     *  用一个哈希表（或者数组）cntT 统计 t 中每个字母的出现次数。
     *  初始化 left=0，以及一个空哈希表（或者数组）cntS，用来统计 s 子串中每个字母的出现次数。
     *  遍历 s，设当前枚举的子串右端点为 right，把 s[right] 的出现次数加一。
     *  遍历 cntS 中的每个字母及其出现次数，如果出现次数都大于等于 cntT 中的字母出现次数：
     *      如果 right−left<ansRight−ansLeft，说明我们找到了更短的子串，更新 ansLeft=left, ansRight=right。
     *      把 s[left] 的出现次数减一。
     *      左端点右移，即 left 加一。
     *      重复上述三步，直到 cntS 有字母的出现次数小于 cntT 中该字母的出现次数为止。
     *  最后，如果 ansLeft<0，说明没有找到符合要求的子串，返回空字符串，否则返回下标 ansLeft 到下标 ansRight 之间的子串。
     * 由于本题大写字母和小写字母都有，为了方便，代码实现时可以直接创建大小为 128 的数组，保证所有 ASCII 字符都可以统计。
     */
    static public String minWindow(String S, String t) {
        char[] s = S.toCharArray();
        int m = s.length;
        int ansLeft = -1;
        int ansRight = m;
        int[] cntS = new int[128]; // s 子串字母的出现次数
        int[] cntT = new int[128]; // t 中字母的出现次数
        for (char c : t.toCharArray()) {
            cntT[c]++;
        }

        int left = 0;
        for (int right = 0; right < m; right++) { // 移动子串右端点
            cntS[s[right]]++; // 右端点字母移入子串
            while (isCovered(cntS, cntT)) { // 涵盖
                if (right - left < ansRight - ansLeft) { // 找到更短的子串
                    ansLeft = left; // 记录此时的左右端点
                    ansRight = right;
                }
                cntS[s[left]]--; // 左端点字母移出子串
                left++;
            }
        }
        return ansLeft < 0 ? "" : S.substring(ansLeft, ansRight + 1);
    }
    /**
     * 什么是「涵盖」
     * 看示例 1，s 的子串 BANC 中每个字母的出现次数，都大于等于 t=ABC 中每个字母的出现次数，这就叫涵盖。
     */
    static private boolean isCovered(int[] cntS, int[] cntT) {
        for (int i = 'A'; i <= 'Z'; i++) {
            if (cntS[i] < cntT[i]) {
                return false;
            }
        }
        for (int i = 'a'; i <= 'z'; i++) {
            if (cntS[i] < cntT[i]) {
                return false;
            }
        }
        return true;
    }
    /**
     * 滑动窗口
     */
    Map<Character, Integer> ori = new HashMap<Character, Integer>(); // 用一个哈希表表示 t 中所有的字符以及它们的个数
    Map<Character, Integer> cnt = new HashMap<Character, Integer>(); // 用一个哈希表动态维护窗口中所有的字符以及它们的个数

    public String minWindow1(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }
    // 如果这个动态表中包含 t 的哈希表中的所有字符，并且对应的个数都不小于 t 的哈希表中各个字符的个数，那么当前的窗口是「可行」的。
    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
    /**
     * 滑动窗口 + 哈希表
     * 思路清晰
     */
   static public String minWindow4(String s, String t) {
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : t.toCharArray()) {
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
        }
        int ansL = -1;
        int ansR = s.length();
        int l = 0;
        int count = cnt.size();

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            if (cnt.containsKey(c)) {
                cnt.put(c, cnt.get(c) - 1);
                if (cnt.get(c) == 0) {
                    count--;
                }
            }
            while (count == 0) {
                if (ansR - ansL > r - l) {
                    ansL = l;
                    ansR = r;
                }
                char ch = s.charAt(l);
                if (cnt.containsKey(ch)) {
                    if (cnt.get(ch) == 0) {
                        count++;
                    }
                    cnt.put(ch, cnt.get(ch) + 1);
                }
                l++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR + 1);
    }
    /**
     * 借助数组的标记，负数表示s子串还不能满足t某个字符的需求，正数表示需求过剩，可移动窗口
     */
    static public String minWindow2(String s, String t) {
        int[] hash = new int[128]; // 统计字符需求及当前窗口频次
        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i)]--; // 负值代表有 相关字符的空缺
        }

        char[] sArr = s.toCharArray();
        int count = 0;
        int res = Integer.MAX_VALUE;
        int L = -1;
        for (int i = 0, j = 0; j < sArr.length; j++) {
            if (hash[sArr[j]] < 0) {
                count++;  // 负值表示存在空缺未填满
            }
            hash[sArr[j]]++; // 更新窗口频次

            // 缩小窗口：移除冗余字符（频次超过需求或非t字符）
            while (i < j && hash[sArr[i]] > 0){
                hash[sArr[i]]--; // 减少频次
                i++; // 左指针右移
            }
            // 记录最小窗口
            if (count == t.length() && res > j - i + 1) {
                res = j - i + 1;
                L = i;
            }
        }
        return res == Integer.MAX_VALUE ? "" : s.substring(L, L + res);
    }
}
