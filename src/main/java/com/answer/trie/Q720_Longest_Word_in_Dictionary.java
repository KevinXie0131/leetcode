package com.answer.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Q720_Longest_Word_in_Dictionary {
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