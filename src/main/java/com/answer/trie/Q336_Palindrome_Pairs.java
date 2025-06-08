package com.answer.trie;

import java.util.*;

public class Q336_Palindrome_Pairs { // Hard
    /**
     * given a 0-indexed array of unique strings words.
     * A palindrome pair is a pair of integers (i, j) such that:
     *  0 <= i, j < words.length,
     *  i != j, and
     *  words[i] + words[j] (the concatenation of the two strings) is a palindrome.
     * Return an array of all the palindrome pairs of words.
     * You must write an algorithm with O(sum of words[i].length) runtime complexity.
     * 回文对
     * 给定一个由唯一字符串构成的 0 索引 数组 words 。
     * 回文对 是一对整数 (i, j) ，满足以下条件：
     *  0 <= i, j < words.length，
     *  i != j ，并且
     *  words[i] + words[j]（两个字符串的连接）是一个回文串。
     * 返回一个数组，它包含 words 中所有满足 回文对 条件的字符串。
     * 你必须设计一个时间复杂度为 O(sum of words[i].length) 的算法。
     * words[i] consists of lowercase English letters.
     * 示例 1：
     *  输入：words = ["abcd","dcba","lls","s","sssll"]
     *  输出：[[0,1],[1,0],[3,2],[2,4]]
     *  解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        return null;
    }
}
