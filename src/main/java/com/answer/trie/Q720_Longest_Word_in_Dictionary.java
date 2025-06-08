package com.answer.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
     *
     */
    public static void main(String[] args) {
      //  String[] words = {"w","wo","wor","worl","world"};
        String[] words = {"a","banana","app","appl","ap","apply","apple"};
        System.out.println(longestWord_1(words));
    }
    /**
     * 暴力法 HashSet
     */
   static public String longestWord(String[] words) {
        String result = "";

        HashSet<String> set = new HashSet<>();
        for(String s : words){
            set.add(s);
        }
        for(String word : words){
            if(word.length() > result.length()
                    || (word.length() == result.length() && word.compareTo(result) < 0)){
                boolean isPrefix = true;
                for(int i = 1; i <= word.length(); i++){
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
            if(word.length() > result.length()
                    || (word.length() == result.length() && word.compareTo(result) < 0)){
                boolean isPrefix = true;
                for(int i = 1; i <= word.length(); i++){
                    cur = cur.children.get(word.substring(0, i));
                    if(!cur.isEnd){
                        isPrefix = false;
                        break;
                    }
                }
                if(isPrefix ){
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
