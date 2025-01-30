package com.answer.trie;

import java.util.HashSet;

public class Q720_Longest_Word_in_Dictionary {
    public static void main(String[] args) {
      //  String[] words = {"w","wo","wor","worl","world"};
        String[] words = {"a","banana","app","appl","ap","apply","apple"};
        System.out.println(longestWord(words));
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
                        isPrefix = false;
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
    public String longestWord_1(String[] words) {

    }
}
