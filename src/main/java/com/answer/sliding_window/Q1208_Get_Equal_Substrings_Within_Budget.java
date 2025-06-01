package com.answer.sliding_window;


public class Q1208_Get_Equal_Substrings_Within_Budget {
    /**
     * You are given two strings s and t of the same length and an integer maxCost.
     * You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]|
     * (i.e., the absolute difference between the ASCII values of the characters).
     * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t
     * with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its corresponding
     * substring from t, return 0.
     * 尽可能使字符串相等: 两个长度相同的字符串，s 和 t。
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
     *
     * t.length == s.length
     * s and t consist of only lowercase English letters.
     *
     * 示例 1：
     *  输入：s = "abcd", t = "bcdf", maxCost = 3
     *  输出：3
     *  解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
     * 示例 2：
     *  输入：s = "abcd", t = "cdef", maxCost = 3
     *  输出：1
     *  解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
     * 示例 3：
     *  输入：s = "abcd", t = "acde", maxCost = 0
     *  输出：1
     *  解释：a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。
     */
    public static void main(String[] args) {
        String s = "abcd";
        String t = "bcdf";
        int maxCost = 3;
        System.out.println(equalSubstring(s, t, maxCost));
    }
    /**
     * Similar with Q487 Max Consecutive Ones II
     *
     * Sliding window
     *
     * 「滑动窗口」是固定大小的，「双指针」是不固定大小的；
     * 「滑动窗口」一定是同向移动的，「双指针」可以相向移动。
     */
    public static int equalSubstring(String s, String t, int maxCost) {
        int max = 0;
        int left = 0, right = 0;

        while(right < s.length()){
            int diff = Math.abs(s.charAt(right) - t.charAt(right));
            maxCost -= diff;

            if(maxCost < 0){
                int diff1 = Math.abs(s.charAt(left) - t.charAt(left));
                maxCost += diff1;
                left++;
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        return max;
    }
    /**
     * Official answer
     */
    public int equalSubstring_1(String s, String t, int maxCost) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;
    }
}