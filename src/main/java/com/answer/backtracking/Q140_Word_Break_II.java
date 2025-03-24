package com.answer.backtracking;

import java.util.*;

public class Q140_Word_Break_II {
    /**
     * 类似Q131 Palindrome Partitioning
     */
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        List<String> res = wordBreak(s, wordDict);
        System.out.println(res);
    }
    /**
     * DFS + Backtracking
     */
    private static Set<String> wordSet = new HashSet<>();
    private static List<String> res = new ArrayList<>();

    public static List<String> wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            wordSet.add(word);
        }
        backtrack(s, 0, new LinkedList<>());
        return res;
    }

    private static void backtrack(String s, int start, LinkedList<String> track) {
        if(start == s.length()){
            res.add(String.join(" ", track));
            return;
        }

        for(int i = start; i < s.length(); i++){
            String w = s.substring(start, i + 1);
            if(!wordSet.contains(w)){
                continue;
            }
            track.addLast(w);
            backtrack(s, i + 1, track);
            track.removeLast();
        }
    }

}
