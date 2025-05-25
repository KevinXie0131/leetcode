package com.answer.array;

import java.util.*;

public class Q1456_Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length {
    /**
     * 定长子串中元音的最大数目
     * return the maximum number of vowel letters in any substring of s with length k.
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     * 给你字符串 s 和整数 k 。返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     * 英文中的 元音字母 为（a, e, i, o, u）。
     * 示例 1：
     *  输入：s = "abciiidef", k = 3
     *  输出：3
     *  解释：子字符串 "iii" 包含 3 个元音字母。
     */
    public static void main(String[] args) {
        String s = "abciiidef";
        int k = 3;
        System.out.println(maxVowels(s, k));
    }
    /**
     * Use queue as sliding window 滑动窗口
     */
    public static int maxVowels(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        int max = 0;

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        Deque<Character> queue = new ArrayDeque<>();
        for(int i = 0; i < k; i++){
            if(set.contains(ch[i])){
                queue.offer(ch[i]);
            }
        }
        max = queue.size();

        for(int i = k; i < n; i++){ // 只需要考虑移除（离开窗口）的字母 是不是元音，以及添加（进入窗口）的字母 是不是元音即可
            if(set.contains(ch[i])){
                queue.offer(ch[i]);
            }
            if(set.contains(ch[i - k])){
                queue.poll();
            }
            max =Math.max(max, queue.size());
        }
        return max;
    }
    /**
     * Use function to check valid character
     */
    public int maxVowels_1(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        int max = 0;

        Deque<Character> queue = new ArrayDeque<>();
        for(int i = 0; i < k; i++){
            if(isVowel(ch[i])){
                queue.offer(ch[i]);
            }
        }
        max = queue.size();

        for(int i = k; i < n; i++){
            if(isVowel(ch[i])){
                queue.offer(ch[i]);
            }
            if(isVowel(ch[i - k])){
                queue.poll();
            }
            max = Math.max(max, queue.size());
        }
        return max;
    }

    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
    /**
     * Use two pointer as sliding window
     */
    public int maxVowels_2(String s, int k) {
        int n = s.length();
        char[] ch = s.toCharArray();
        int max = 0;

        for(int i = 0; i < k; i++){
            if(isVowel_1(ch[i]) == 1){
                max++;
            }
        }
        int count = max;
        for(int i = k; i < n; i++){
            count += isVowel_1(ch[i]);
            count -= isVowel_1(ch[i - k]);
            max = Math.max(max, count);
        }
        return max;
    }

    public int isVowel_1(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }
    /**
     * 可以用一个长度为 k 的滑动窗口去遍历所有长度为 k 的字符串。
     * 滑动窗口的移动的核心是：右侧加入的新元素，左侧淘汰旧元素
     * 如果加入的是元音字母，统计 +1；如果淘汰的是元音字母，统计 -1。
     */
    public int maxVowels_3(String s, int k) {
        int n = s.length();
        int max = 0;

        for(int i = 0; i < k; i++){
            max += isVowel_1(s.charAt(i)); // 初始化首个窗口的元音字母个数
        }
        int count = max;
        for(int i = k; i < n; i++){
            count += isVowel_1(s.charAt(i)) - isVowel_1(s.charAt(i - k));  // 加入新字母，淘汰旧字母
            max = Math.max(max, count); // 更新最大值
        }
        return max;
    }
    /**
     * Another form
     */
    public int maxVowels_5(String S, int k) {
        char[] s = S.toCharArray();
        int ans = 0;
        int vowel = 0;
        for (int i = 0; i < s.length; i++) {
            // 1. 进入窗口
            if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') {
                vowel++;
            }
            if (i < k - 1) { // 窗口大小不足 k
                continue;
            }
            // 2. 更新答案
            ans = Math.max(ans, vowel);
            // 3. 离开窗口
            char out = s[i - k + 1];
            if (out == 'a' || out == 'e' || out == 'i' || out == 'o' || out == 'u') {
                vowel--;
            }
        }
        return ans;
    }
}
