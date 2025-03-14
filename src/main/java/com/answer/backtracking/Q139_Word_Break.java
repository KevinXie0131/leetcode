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
     * 类似Q131.分割回文串（枚举分割后的所有子串，判断是否回文）
     * 本题是枚举分割所有字符串，判断是否在字典里出现过
     *
     * 时间复杂度：O(2^n)，因为每一个单词都有两个状态，切割和不切割
     * 空间复杂度：O(n)，算法递归系统调用栈的空间
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
     * DFS + Backtracking + memorization 记忆化递归
     *
     * 使用memory数组保存每次计算的以startIndex起始的计算结果，如果memory[startIndex]里已经被赋值了，直接用memory[startIndex]的结果。
     * 时间复杂度其实也是：O(2^n)。只不过对于超时测试用例优化效果特别明显
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
     * 回溯法+记忆化
     */
    private Set<String> set;
    private int[] memo;
    public boolean wordBreak_8(String s, List<String> wordDict) {
        memo = new int[s.length()];
        set = new HashSet<>(wordDict);
        return backtracking(s, 0);
    }

    public boolean backtracking(String s, int startIndex) {
        // System.out.println(startIndex);
        if (startIndex == s.length()) {
            return true;
        }
        if (memo[startIndex] == -1) {
            return false;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String sub = s.substring(startIndex, i + 1);
            // 拆分出来的单词无法匹配
            if (!set.contains(sub)) {
                continue;
            }
            boolean res = backtracking(s, i + 1);
            if (res) return true;
        }
        // 这里是关键，找遍了startIndex~s.length()也没能完全匹配，标记从startIndex开始不能找到
        memo[startIndex] = -1;
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
     *
     * 背包问题： 单词就是物品，字符串s就是背包，单词能否组成字符串s，就是问物品能不能把背包装满。
     * 拆分时可以重复使用字典中的单词，说明就是一个完全背包！
     * 时间复杂度：O(n^3)，因为substr返回子串的副本是O(n)的复杂度（这里的n是substring的长度）
     * 空间复杂度：O(n)
     */
    public static boolean wordBreak_5(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1]; // dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单
        dp[0] = true; // dp[i] 的状态依靠 dp[j]是否为true，那么dp[0]就是递推的根基，dp[0]一定要为true，否则递推下去后面都都是false
        // 如果求组合数就是外层for循环遍历物品，内层for遍历背包
        // 如果求排列数就是外层for遍历背包，内层for循环遍历物品
        // 本题其实我们求的是排列数, 一定是 先遍历 背包，再遍历物品
        for(int i = 1; i <= s.length(); i++){ // 背包
            for(int j = 0; j < i; j++){       // 物品
                // 如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。（j < i ）。
                // 所以递推公式是 if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true。
                String word = s.substring(j, i);
                if(wordDict.contains(word) && dp[j]){ // dp[j]为true，而且j到i是有效字符，则dp[i]为true
                    dp[i] = true;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[s.length()];
    }
    /**
     * 另一种思路的背包算法
     */
    public boolean wordBreak_7(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && dp[i - len] && word.equals(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
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
