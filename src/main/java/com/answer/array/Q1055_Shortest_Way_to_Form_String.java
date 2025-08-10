package com.answer.array;

public class Q1055_Shortest_Way_to_Form_String {
    /**
     * 给你两个字符串 source 和 target，返回你需要按顺序（可以重复）从 source 中拷贝的最少子序列的数量，使得你可以组成 target。
     * 如果无法通过这种方式组成 target，请返回 -1。
     * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
     * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
     * Example 1:
     *  Input: source = "abc", target = "abcbc"
     *  Output: 2
     *  Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
     *  解释：第一次从 source 拷贝 "abc"，第二次从 source 拷贝 "bc"。
     * Example 2:
     *  Input: source = "abc", target = "acdbc"
     *  Output: -1
     *  Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
     *  解释：由于 target 中的 'd' 在 source 中不存在，所以返回 -1。
     * Example 3:
     *  Input: source = "xyz", target = "xzyxz"
     *  Output: 3
     *  Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
     *  解释：第一次拷贝 "xz"，第二次拷贝 "y"，第三次拷贝 "xz"。
     *
     * Constraints:
     *  Both the source and target strings consist of only lowercase English letters from "a"-"z".
     *  The lengths of source and target string are between 1 and 1000.
     */
    public static void main(String[] args) {
        String source = "abc", target = "abcbc";
        System.out.println(shortestWay_1(source, target));
        String source1 = "abc", target1 = "acdbc";
        System.out.println(shortestWay_1(source1, target1));
        String source2 = "xyz", target2 = "xzyxz";
        System.out.println(shortestWay_1(source2, target2));
    }
    /**
     * Approach 3: Two Pointers + Greedy
     *
     * 子序列（subsequence）：并不要求连续，序列 [4, 6, 5] 是 [1, 2, 4, 3, 7, 6, 5] 的一个子序列。(子序列（subsequence）：并不要求在原序列中连续)
     * 子串（substring、subarray）：一定是连续的 (子串（substring、subarray）：在原序列中一定是连续的)
     *
     * 注意事项：按照 LeetCode 的习惯，子序列（subsequence）不必连续，子数组（subarray）或子字符串 （substring）必须连续
     */
    public static int shortestWay(String source, String target) {
        char[] str1 = source.toCharArray();
        char[] str2 = target.toCharArray();
        int j = 0 ;
        int count = 0 ;
        while( j < str2.length){
            int prev = j ;
            for(int i = 0 ; i < str1.length ; i++){
                if( j < str2.length && str1[i] == str2[j]) {
                    j++;
                }
            }
            if( prev == j ) { //如果j没有移动
                return -1;
            }
            count++;
        }

        return count;
    }
    /**
     * Two Pointers
     */
    public static int shortestWay_1(String source, String target) {
        int m = source.length(), n = target.length();
        int ans = 0, j = 0;
        while (j < n) {
            int i = 0;
            boolean ok = false;
            while (i < m && j < n) {
                if (source.charAt(i) == target.charAt(j)) {
                    ok = true;
                    ++j;
                }
                ++i;
            }
            if (!ok) {
                return -1;
            }
            ++ans;
        }
        return ans;
    }
}
