package com.answer.backtracking;

import java.util.*;

public class Q139_Word_Break {
    public static void main(String[] args) {
       String s = "leetcode";
       List<String> wordDict = new ArrayList<>();
       wordDict.add("leet");
       wordDict.add("code");
       boolean result =  wordBreak_5(s, wordDict);
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
    /**
     * DFS + Backtracking + memorization
     */
    public static boolean wordBreak_1(String s, List<String> wordDict) {
        return dfs_1(s, wordDict, new HashSet<>(), 0);
    }
    //start表示的是从字符串s的哪个位置开始
    public static boolean dfs_1(String s, List<String> wordDict, HashSet<Integer> indexSet, int start){
        //字符串都拆分完了，返回true
        if (start == s.length())
            return true;
        for (int i = start + 1; i <= s.length(); i++) {
            //如果已经判断过了，就直接跳过，防止重复判断
            if (indexSet.contains(i))
                continue;
            //截取子串，判断是否是在字典中
            if (wordDict.contains(s.substring(start, i))) {
                if (dfs_1(s, wordDict, indexSet, i))
                    return true;
                //标记为已判断过
                indexSet.add(i);
            }
        }
        return false;
    }
    /**
     * BFS
     */
    public static boolean wordBreak_2(String s, List<String> wordDict) {
        //这里为了提高效率，把list转化为set，因为set的查找效率要比list高
        Set<String> setDict = new HashSet<>(wordDict);
        //记录当前层开始遍历字符串s的位置
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int length = s.length();
        while (!queue.isEmpty()) {
            int index = queue.poll();
            //如果字符串到遍历完了，自己返回true
            if (index == length)
                return true;
            for (int i = index + 1; i <= length; i++) {
                if (setDict.contains(s.substring(index, i))) {
                    queue.add(i);
                }
            }
        }
        return false;
    }
    /**
     * BFS + Memorization
     */
    public boolean wordBreak_4(String s, List<String> wordDict) {
        //这里为了提高效率，把list转化为set，因为set的查找效率要比list高
        Set<String> setDict = new HashSet<>(wordDict);
        //记录当前层开始遍历字符串s的位置
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int length = s.length();
        //记录访问过的位置，减少重复判断
        boolean[] visited = new boolean[length];
        while (!queue.isEmpty()) {
            int index = queue.poll();
            //如果字符串都遍历完了，直接返回true
            if (index == length)
                return true;
            //如果被访问过，则跳过
            if (visited[index])
                continue;
            //标记为访问过
            visited[index] = true;
            for (int i = index + 1; i <= length; i++) {
                if (setDict.contains(s.substring(index, i))) {
                    queue.add(i);
                }
            }
        }
        return false;
    }
    /**
     * Approach 4: Using Dynamic Programming
     * Time complexity : O(n3)
     *
     * dp(i) = true if s.substring(i - word.length + 1, i + 1) == word and dp[i - word.length] == true
     * for any word in wordDict, otherwise false
     *
     * 如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * 完全背包：相当于物品能否装满背包（每个物品可以使用多次）
     */
    public static boolean wordBreak_5(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                String word = s.substring(j, i);
                if(wordDict.contains(word) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }
    /**
     * Bottom-up Implementation
     */
    public boolean wordBreak_6(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (String word : wordDict) {
                // Make sure to stay in bounds while checking criteria
                if (i >= word.length() - 1 && (i == word.length() - 1 || dp[i - word.length()])) {
                    if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }
}
