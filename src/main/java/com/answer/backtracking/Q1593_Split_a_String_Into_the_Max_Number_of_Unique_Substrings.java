package com.answer.backtracking;

import java.util.HashSet;

public class Q1593_Split_a_String_Into_the_Max_Number_of_Unique_Substrings {
    public static void main(String[] args) {
        String s = "ababccc";
        System.out.println(maxUniqueSplit(s)); // 5
        String s1 = "aba";
      //  System.out.println(maxUniqueSplit(s1)); // 2
        String s2 = "aa";
      //  System.out.println(maxUniqueSplit(s2)); // 1
    }
    /**
     * Given a string s, return the maximum number of unique substrings that the given string can be split into.
     * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms
     * the original string. However, you must split the substrings such that all of them are unique.
     * A substring is a contiguous sequence of characters within a string.
     * 拆分字符串使唯一子字符串的数目最大
     * 一个字符串 s ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。
     * 字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 唯一的 。
     * 注意：子字符串 是字符串中的一个连续字符序列。
     */
    static int maxSplit = 0;
    static public int maxUniqueSplit(String s) {
        HashSet<String> set = new HashSet<>();

        backtracking(s, 0, 0, set);
        return maxSplit;
    }
    /**
     * 方法一：回溯
     * 拆分给定的字符串，要求拆分后的每个子字符串唯一，求子字符串的最大数目，可以通过回溯算法实现。
     *
     * 对于长度为 n 的字符串，有 n−1 个拆分点。从左到右遍历字符串，对于每个拆分点，如果在此拆分之后，
     * 新得到的一个非空子字符串（即拆分点左侧的最后一个被拆分出的非空子字符串）与之前拆分出的非空子字符串都不相同，
     * 则当前的拆分点可以进行拆分，然后继续对剩下的部分（即拆分点右侧的部分）进行拆分。
     *
     * 判断拆分出的非空子字符串是否有重复时，可以使用哈希表。
     * 当整个字符串拆分完毕时，计算拆分得到的非空子字符串的数目，并更新最大数目。
     * 时间复杂度是 O(2^n × n)
     */
    static public void backtracking(String s, int start, int split, HashSet<String> set){
        // 判断剩余字符长度和已有答案，进行剪枝
        if (s.length() - start + set.size() <= maxSplit) return;

        if(start == s.length()){
            maxSplit = Math.max(maxSplit, split);
            return;
        }

        for(int i = start; i < s.length(); i++){
            String subStr = s.substring(start, i + 1);
            if(!set.contains(subStr)){
                set.add(subStr);
                backtracking(s, i + 1, split + 1, set);
                set.remove(subStr);
            }
        }
    }
}
