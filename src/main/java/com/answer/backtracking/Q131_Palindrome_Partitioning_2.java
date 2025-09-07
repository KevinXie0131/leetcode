package com.answer.backtracking;

import java.util.*;

public class Q131_Palindrome_Partitioning_2 {
    /**
     * 方法一：输入的视角（逗号选或不选）
     * 假设每对相邻字符之间有个逗号，那么就看每个逗号是选还是不选。
     * 也可以理解成：是否要在 i 和 i+1 处分割，把 s[i] 作为当前子串的最后一个字符，把 s[i+1] 作为下一个子串的第一个字符。
     * 注意 s[n−1] 一定是最后一个字符，所以在 i=n−1 的时候一定要分割。
     */
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        Deque<String> path = new ArrayDeque<>();
        dfs(0, 0, s, path, ans);
        return ans;
    }
    // 考虑 i 后面的逗号怎么选
    // start 表示当前这段回文子串的开始位置
    private void dfs(int i, int start, String s, Deque<String> path, List<List<String>> ans) {
        if (i == s.length()) { // s 分割完毕
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }
        // 不分割，不选 i 和 i+1 之间的逗号
        if (i < s.length() - 1) { // i=n-1 时只能分割
            // 考虑 i+1 后面的逗号怎么选
            dfs(i + 1, start, s, path, ans);
        }
        // 分割，选 i 和 i+1 之间的逗号（把 s[i] 作为子串的最后一个字符）
        if (isPalindrome(s, start, i)) {
            path.add(s.substring(start, i + 1));
            // 考虑 i+1 后面的逗号怎么选
            // start=i+1 表示下一个子串从 i+1 开始
            dfs(i + 1, i + 1, s, path, ans);
            path.removeLast(); // path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
