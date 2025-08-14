package com.answer.sliding_window;

import java.util.*;

public class Q3_Longest_Substring_Without_Repeating_Characters {
    /**
     * Given a string s, find the length of the longest substring without duplicate characters.
     * A substring is a contiguous non-empty sequence of characters within a string.
     * 无重复字符的最长子串: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     * 子字符串 是字符串中连续的 非空 字符序列。
     */
    public static void main(String[] args) {
        String s = "abcabcbb";
     //   String s = "abba";
     //   String s = "dvdf";
        System.out.println(lengthOfLongestSubstring_2(s));
    }
    /**
     * Approach 3: Sliding Window Optimized 滑动窗口
     * 窗口内不含重复元素
     * 不满足要求。所以，要移动这个队列！如何移动？只要把队列的左边的元素移出就行了，直到满足题目要求！
     *
     * 套路步骤：
     * 1、定义需维护的结果变量
     * 2、定义首尾端，开始滑动窗口
     * 3、更新维护结果变量，有时会用到一个if语句结果变量和左指针。
     * 4、若窗口长度固定，就用if来维护：只要右指针还没到到头，左指针+1，并减掉左指针的值；
     * 4、若窗口长度可变，判断窗口合法性，用while去维护左指针右移，直到窗口合法，删掉左指针的值。
     * 5、返回答案
     */
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>(); // 哈希集合，记录每个字符是否出现过
        char[] arr = s.toCharArray();

        int left = 0;
        for(int i = 0; i < arr.length; i++){
            if(map.containsKey(arr[i])){
                // 更新窗口左边界, 加 1 表示从字符位置后一个才开始不重复
                left = Math.max(left, map.get(arr[i]) + 1); // 需要比较max，比如"abba"或者"dvdf"
            }
            map.put(arr[i], i);
          /*  if(map.containsKey(arr[i])){ // works too
                left = Math.max(left, map.get(arr[i]));
            }
            map.put(arr[i], i + 1);*/
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
    /**
     * int[26] for Letters 'a' - 'z' or 'A' - 'Z'
     * int[128] for ASCII
     * int[256] for Extended ASCII
     *
     * 使用一个数组记录每个字符上次出现的位置，在遍历的同时移动窗口左边界，最后返回窗口长度的最大值即可。
     */
    public static int lengthOfLongestSubstring_1(String s) {
        int max = 0;
        //   Map<Character, Integer> map = new HashMap<>();
        Integer[] map = new Integer[256]; // new Integer[128]; // works too
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
    static public int lengthOfLongestSubstring_2(String s) {
        Map<Character, Integer> chars = new HashMap();

        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char rc = s.charAt(right);
            chars.put(rc, chars.getOrDefault(rc,0) + 1);

            while (chars.get(rc) > 1) { // 不含有重复字符
                char lc = s.charAt(left);
                chars.put(lc, chars.get(lc) - 1);
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
    /**
     * another form
     */
    public int lengthOfLongestSubstring_2a(String s) {
        char[] array = s.toCharArray(); // 转换成 char[] 加快效率（忽略带来的空间消耗）

        int left = 0;
        int res = 0;
        int[] cnt = new int[128]; // 也可以用 HashMap<Character, Integer>，这里为了效率用的数组

        for (int right = 0; right < array.length; right++) {
            char rc = s.charAt(right);
            cnt[rc]++;

            while (cnt[rc] > 1) { // 不含有重复字符
                char lc = array[left];
                cnt[lc]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
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
    /**
     * Official answer
     * 滑动窗口
     * 1.我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，
     * 而右指针即为上文中的 rk
     * 2.在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，
     * 但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。
     * 我们记录下这个子串的长度；
     * 3.在枚举结束后，我们找到的最长的子串的长度即为答案。
     */
    public int lengthOfLongestSubstring5(String s) {
        Set<Character> occ = new HashSet<Character>();// 哈希集合，记录每个字符是否出现过
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));// 左指针向右移动一格，移除一个字符
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                occ.add(s.charAt(rk + 1)); // 不断地移动右指针
                ++rk;
            }

            ans = Math.max(ans, rk - i + 1);  // 第 i 到 rk 个字符是一个极长的无重复字符子串
        }
        return ans;
    }
}
