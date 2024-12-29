package com.answer.string;

public class Q459_Repeated_Substring_Pattern {

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
    }
}
