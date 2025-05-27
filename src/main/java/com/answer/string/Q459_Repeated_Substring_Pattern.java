package com.answer.string;

public class Q459_Repeated_Substring_Pattern {
    /**
     * Given a string s, check if it can be constructed by taking a substring of it and appending multiple
     * copies of the substring together.
     * 重复的子字符串: 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
     * 示例 1:
     *  输入: s = "abab"
     *  输出: true
     *  解释: 可由子串 "ab" 重复两次构成。
     * 示例 2:
     *  输入: s = "aba"
     *  输出: false
     */
    /**
     * 当然，我们在判断 s + s 拼接的字符串里是否出现一个s的的时候，要刨除 s + s 的首字符和尾字符，这样避免在s+s中搜索出原来的s，
     * 我们要搜索的是中间拼接出来的s。
     * 如果有一个字符串s，在 s + s 拼接后， 不算首尾字符，如果能凑成s字符串，说明s 一定是重复子串组成。
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public boolean repeatedSubstringPattern_1(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s); // 掐头去尾
        //  return (s + s).indexOf(s, 1) != s.length(); // works too
    }
    /**
     * 如果您的字符串 S 包含一个重复的子字符串，那么这意味着您可以多次 “移位和换行”`您的字符串，并使其与原始字符串匹配。
     * 基于这个思想，可以每次移动k个字符，直到匹配移动 length - 1 次。
     */
    public  boolean repeatedSubstringPattern2(String s) { // Time Limit Exceeded
        for(int i = 1; i < s.length(); i++) {
            String str = rotate(s.toCharArray(),i);
            if(s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public  String rotate(char[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        return String.valueOf(nums);
    }

    public  void reverse(char[] nums, int begin, int end) {
        int i = begin, j = end;
        while(i < j) {
            char temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
    /**
     * KMP
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.equals("")) return false;

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }


}
