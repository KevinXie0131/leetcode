package com.answer.backtracking;

import java.util.*;

public class Q139_Word_Break {
    public static void main(String[] args) {
       String s = "leetcode";
       List<String> wordDict = new ArrayList<>();
       wordDict.add("leet");
       wordDict.add("code");
       boolean result =  wordBreak(s, wordDict);
        System.out.println(result);
    }
    /**
     * DFS + Backtracking
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    public static boolean dfs(String s, List<String> wordDict, int start){
        if(start == s.length()){
            return true;
        }
        for(int i = start + 1; i <= s.length(); i ++){
            if(!wordDict.contains(s.substring(start, i))){
                continue;
            }
            if(dfs(s, wordDict, i)){
                return true;
            }
        }

        return false;
    }
}
