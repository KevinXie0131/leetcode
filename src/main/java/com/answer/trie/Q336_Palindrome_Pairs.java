package com.answer.trie;

import java.util.*;

public class Q336_Palindrome_Pairs { // Hard 困难
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
    public static void main(String[] args) {
       // String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        String[] words = {"a",""};
        System.out.println(palindromePairs3(words));
    }
    /**
     * 简单的暴力[超时]
     * 简单的暴力枚举出所有的字符串对，然后判断它们组成的字符串是否是回文对. 当数据量较大时，超时
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (!isPalindrome(words[i] + words[j])) continue;
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.add(j);
                ans.add(temp);
            }
        }
        return ans;
    }
    //  判断一个字符串是否是回文字符串
    static private boolean isPalindrome(String s) {
        int i = 0, j = s.length()-1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
    /**
     * 两个子串想组成回文串，可能是：
     *  一个子串是“翻转”+“回文串”，另一个子串是“翻转”
     *  或，一个子串是“翻转”，另一个子串是“回文串”+“翻转”。
     *
     * 先看看左边部分是不是回文，如果是，则期望右边部分找到翻转词。
     * 再看看右边部分是不是回文，如果是，则期望左边部分找到翻转词。
     * 为了能快速找出翻转词，我们提前将单词都翻转一遍，存入哈希表，还有它对应的索引
     */
    static public List<List<Integer>> palindromePairs3(String[] words) {
        int n = words.length, len = 0;
        String temp = "", left = "", right = "";
        HashMap<String, Integer> rev_words = new HashMap<>(); // 存储所有单词的翻转串
        // 遍历words得出所有单词的翻转
        for (int i = 0; i < n; ++i) {
            temp = words[i];
            rev_words.put(new StringBuilder(temp).reverse().toString(), i);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // 遍历words所有单词，找出能形成回文串的结果。分别查找左右子串，然后找出能形成回文的串
        for (int i = 0; i < n; ++i) {
            temp = words[i];
            len = temp.length();
            // 若单词自回文，则找空串左匹配
            if(rev_words.containsKey(temp) && rev_words.containsKey("") && !"".equals(temp)) {
                result.add(new ArrayList<>(Arrays.asList(rev_words.get(""), i)));
            }
            // 左子串长度为0 ~ n - 1，右子串长度为n ~ 1
            for(int j = 0; j < len; ++j) {
                left = temp.substring(0, j + 1);
                right = temp.substring(j + 1, len);
                // 若左子串left为回文，则从rev_words中找是否存在right，存在则两单词构成回文，即 该单词 + temp 构成回文
                if(isPalindrome(left) && rev_words.containsKey(right) && rev_words.get(right) != i) {
                    result.add(new ArrayList<>(Arrays.asList(rev_words.get(right), i)));
                }
                // 若右子串right为回文，则从rev_words中找是否存在left，存在则两单词构成回文，即 temp + 该单词 构成回文
                if(isPalindrome(right) && rev_words.containsKey(left) && rev_words.get(left) != i) {
                    result.add(new ArrayList<>(Arrays.asList(rev_words.get(left), i)));
                }
            }
        }
        return result;
    }
    /**
     * 字典树（Trie树）
     * 对于一个字符串对 (x,y), 若想要字符串 x+y 是一个回文字符串，则必须满足以下条件之一
     *  当 x.length()≥y.length() 时, 字符串 x 的 y.length() 长度的前缀与 y 的 逆序 相等，且字符串 x 去除长度为 y.length() 的前缀后，余下的部分也是一个回文字符串。
     *  当 x.length()<y.length() 时，与情况一正相反。
     *
     *  对于字符串 x 时，我们依次遍历其每一个字母，假设当前遍历到的位置为 i,若 [i,x.length()] 是一个回文对，且 [0,i] 的逆序存在于 word 列表中（设其下标为 y），则 (x,y) 可以构成回文对，加入结果数组中。
     * 当我们遍历完字符串 x 的每一个字符，我们还 需要考虑分析中第2种情况，即 x.length()<y.length()
     */
    class TriNode {
        TriNode []childNode = new TriNode[26];
        Integer index;
    }
    TriNode root = new TriNode();

    private void insert(String word, int index) {
        TriNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int pos = word.charAt(i) - 'a';
            if (curr.childNode[pos] == null) {
                curr.childNode[pos]=new TriNode();
            }
            curr = curr.childNode[pos];
        }
        curr.index = index;
    }

    private Integer search(String word, int start, int end) {
        TriNode curr = root;
        for (int i = end; i >= start; i--) { // 逆序
            int pos = word.charAt(i) - 'a';
            if (curr.childNode[pos] == null) {
                return null;
            }
            curr = curr.childNode[pos];
        }
        return curr.index;
    }
    // 判断一个字符串是否是回文字符串
    private boolean isPalindrome(String s,int start,int end) {
        int i=start,j=end;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;j--;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();

            for (int j = 0; j <= len; j++) {
                if (isPalindrome(word, j, len - 1)) {
                    Integer index = search(word, 0, j - 1);
                    if (index != null && index != i) {
                        res.add(Arrays.asList(i, index));
                    }
                }
                if (j != 0 && isPalindrome(word, 0, j - 1)) {
                    Integer index = search(word, j, len - 1);
                    if (index != null && index != i) {
                        res.add(Arrays.asList(index,i));
                    }
                }
            }
        }
        return res;
    }
}
