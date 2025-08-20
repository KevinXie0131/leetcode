package com.answer.trie;

import java.util.*;

public class Q720_Longest_Word_in_Dictionary {
    /**
     * Given an array of strings words representing an English Dictionary, return the longest word in words that
     * can be built one character at a time by other words in words.
     * If there is more than one possible answer, return the longest word with the smallest lexicographical order.
     * If there is no answer, return the empty string.
     * Note that the word should be built from left to right with each additional character being added to the end of a previous word.
     * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
     * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
     * 请注意，单词应该从左到右构建，每个额外的字符都添加到前一个单词的结尾。
     * words[i] consists of lowercase English letters.
     * 示例 2：
     *  输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
     *  输出："apple"
     *  解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
     */
    public static void main(String[] args) {
      //  String[] words = {"w","wo","wor","worl","world"};
        String[] words = {"a","banana","app","appl","ap","apply","apple"};
        System.out.println(longestWord(words));
    }
    /**
     * 暴力法 HashSet
     * 用集合统计全部字符，遍历统计所有前缀都在集合中的字符串，返回最长且字典序最小的那个。
     */
   static public String longestWord(String[] words) {
        String result = "";

        HashSet<String> set = new HashSet<>();
        for(String s : words){
            set.add(s);
        }
        for(String word : words){
            if(word.length() > result.length() // 利用当前的最长单词来做剪枝
                    || (word.length() == result.length() && word.compareTo(result) < 0)){ // 若其中有多个可行的答案，则返回答案中字典序最小的单词
                boolean isPrefix = true;
                for(int i = 1; i <= word.length() - 1; i++){
                    if(!set.contains(word.substring(0, i))){
                        isPrefix = false; // 有一个前缀都不匹配，就跳出循环
                        break;
                    }
                }
                if(isPrefix){ // 所有前缀都匹配
                    result = word;
                }
            }
        }
        return result;
    }
    /**
     * another form
     */
    public String longestWord3(String[] words) {
        Set<String> set = new HashSet<>();
        for(String word: words) {
            set.add(word);
        }
        String longestWord = "";

        for(String word: words) {
            boolean isValid = true;
            for (int i = 1; i < word.length(); i++) {
                if (!set.contains(word.substring(0, i))) {
                    isValid = false;
                    break;
                }
            }
            if(isValid){
                if(word.length() > longestWord.length()) {
                    longestWord = word;
                } else if(word.length() == longestWord.length()) {
                    if(word.compareTo(longestWord) < 0) {
                        longestWord = word;
                    }
                }
            }
        }
        return longestWord;
    }
    /**
     * 哈希集合 + 排序
     * 定义「符合要求的单词」如下：
     *  空字符串是符合要求的单词；
     *  在符合要求的单词的末尾添加一个字母，得到的新单词是符合要求的单词。
     * 这道题要求返回数组 words 中的最长的符合要求的单词，如果有多个最长的符合要求的单词则返回其中字典序最小的单词。
     *
     * 为了方便处理，需要将数组 words 排序，排序的规则是首先按照单词的长度升序排序，如果单词的长度相同则按照字典序降序排序。
     * 排序之后，可以确保当遍历到每个单词时，比该单词短的全部单词都已经遍历过，且每次遇到符合要求的单词一定是最长且字典序
     * 最小的单词，可以直接更新答案。
     */
    public String longestWord2(String[] words) {
      /*  Arrays.sort(words, (a, b) ->  {
            if (a.length() != b.length()) {
                return a.length() - b.length();
            } else {
                return b.compareTo(a);
            }
        });*/
        Arrays.sort(words, (a, b) -> a.length() == b.length() ? b.compareTo(a) : a.length() - b.length());// 相同长度下把字典序较大的排在前面

        String longest = "";
        Set<String> candidates = new HashSet<>(); // 使用哈希集合存储所有符合要求的单词
        candidates.add("");
        int n = words.length;
        for (int i = 0; i < n; i++) {
            String word = words[i];
            // 判断当前单词去掉最后一个字母之后的前缀是否在哈希集合中，如果该前缀在哈希集合中则当前单词是符合要求的单词，
            // 将当前单词加入哈希集合，并将答案更新为当前单词
            if (candidates.contains(word.substring(0, word.length() - 1))) {
                candidates.add(word);  // 注意是逐步多一，所以，这个添加要放在if里面
                longest = word;
            }
        }
        return longest;
    }
    /**
     * Trie 字典树/前缀树
     */
    static public String longestWord_1(String[] words) {
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode cur = root;

            for(int i = 1; i <= word.length(); i++) {
                if(cur.children.containsKey(word.substring(0, i))){
                    cur = cur.children.get(word.substring(0, i));
                }else{
                    TrieNode newNode = new TrieNode();
                    cur.children.put(word.substring(0, i), newNode);
                    cur = newNode;
                }
            }
            cur.val = word;
            cur.isEnd = true;
        }

        String result = "";
        for(String word : words) {
            TrieNode cur = root;
            if(word.length() > result.length() || (word.length() == result.length() && word.compareTo(result) < 0)){
                boolean isPrefix = true;
                for(int i = 1; i <= word.length(); i++){
                    cur = cur.children.get(word.substring(0, i));
                    if(!cur.isEnd){
                        isPrefix = false;
                        break;
                    }
                }
                if(isPrefix){
                    result = word;
                }
            }
        }
        return result;
    }
}

class TrieNode {
    HashMap<String, TrieNode> children = new HashMap<>();
    boolean isEnd;
    String val;
}
