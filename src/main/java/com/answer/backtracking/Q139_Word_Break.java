package com.answer.backtracking;

import java.util.*;

public class Q139_Word_Break {
    /**
     * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
     * a space-separated sequence of one or more dictionary words.
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     * 单词拆分: 一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     */
    public static void main(String[] args) {
       String s = "leetcode"; // 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。注意，你可以重复使用字典中的单词。
       List<String> wordDict = new ArrayList<>();
       wordDict.add("leet");
       wordDict.add("code");
       boolean result = wordBreak_4(s, wordDict);
        System.out.println(result);
        s = "catsandog";
        wordDict.clear();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        result = wordBreak_4(s, wordDict);
        System.out.println(result);
    }
    /**
     * refer to Q140_Word_Break_II
     * Time Limit Exceeded
     */
    public boolean wordBreak0(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordDict);
        return backtracking(wordSet, s, 0);
    }

    private boolean backtracking(Set<String> wordSet, String s, int startIndex) {
        if(startIndex == s.length()){
            return true;
        }

        for(int i = startIndex; i < s.length(); i++){
            String word = s.substring(startIndex, i + 1);
            if(!wordSet.contains(word)){
                continue;
            }
            if(backtracking(wordSet, s, i + 1)){
                return true;
            }
        }
        return false;
    }
    /**
     * Optimized by memorization 记忆化递归
     */
    public boolean wordBreak0a(String s, List<String> wordDict) {
        Set<String> wordSet =  new HashSet<>();
        wordSet.addAll(wordDict);
        int[] memo =  new int[s.length()];
        return backtracking(wordSet, s, 0, memo);
    }

    private boolean backtracking( Set<String> wordSet, String s, int startIndex,  int[] memo) {
        if(startIndex == s.length()){
            return true;
        }
        if (memo[startIndex] == -1) {
            return false;
        }
        for(int i = startIndex; i < s.length(); i++){
            String word = s.substring(startIndex, i + 1);
            if(!wordSet.contains(word)){
                continue;
            }
            if(backtracking(wordSet, s, i + 1, memo)){
                return true;
            }
        }
        memo[startIndex] = -1;
        return false;
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
    // Time Limit Exceeded
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
        if (start == s.length()) {
            return true;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            //如果已经判断过了，就直接跳过，防止重复判断
            if (indexSet.contains(i)) {
                continue;
            }
            //截取子串，判断是否是在字典中
            if (wordDict.contains(s.substring(start, i))) {
                if (dfs_1(s, wordDict, indexSet, i)) {
                    return true;
                }
                //标记为已判断过
                indexSet.add(i);
            }
        }
        return false;
    }
    /**
     * another form
     */
    public static boolean wordBreak_1a(String s, List<String> wordDict) {
        Set<String> memory = new HashSet<>();
        Set<String> words = new HashSet<>(wordDict);
        return dfs_1a(s,  words, memory);
    }
   // 对未成功拆分的情况进行记忆，那么后续递归如果遇到相同情况即可提前结束递归
    public static boolean dfs_1a(String s, Set<String> words, Set<String> memory){
        if(s.length() == 0) {
            return true;
        }
        if(memory.contains(s)) {
            return false;//如果记忆中存在此字符串，返回false，结束递归。
        }
        StringBuilder strb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            strb.append(s.charAt(i));
            if(words.contains(strb.toString()) && !memory.contains(s.substring(i + 1))){
                if(dfs_1a(s.substring(i + 1), words, memory)){
                    return true;
                }else{
                    // can be commented
                    memory.add(s.substring(i + 1));//对子串失败的情况进行记忆
                }
            }
        }
        memory.add(s); //对s失败的情况进行记忆
        return false;
    }
    /**
     * 回溯法+记忆化
     */
    private Set<String> set;
    private int[] memo; // 可以看到，做了大量重复计算, 加入记忆化. 用一个数组，存储计算的结果，数组索引为指针位置，值为计算的结果。下次遇到相同的子问题，直接返回命中的缓存值，就不用调重复的递归。

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
            if (res) {
                return true;
            }
        }
        // 这里是关键，找遍了startIndex~s.length()也没能完全匹配，标记从startIndex开始不能找到
        memo[startIndex] = -1;
        return false;
    }
    /**
     * BFS
     * Time Limit Exceeded
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
            if (index == length) {
                return true;
            }
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
     * 未剪枝的DFS会搜索重复的子树，BFS也一样。思考一下这个用例，BFS是如何重复访问节点的？
     * 用一个 visited 数组记录访问过的节点，出列考察一个指针时，存在重复则跳过。
     */
    public static boolean wordBreak_4(String s, List<String> wordDict) {
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
            if (index == length) {
                return true;
            }
            //如果被访问过，则跳过
            if (visited[index]) {
                continue;
            }
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
        /**
         * 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词
         * 需要枚举 s[0..i−1] 中的分割点 j ，看 s[0..j−1] 组成的字符串 （默认 j=0 时 为空串）和 s[j..i−1] 组成的字符串 是否都合法，
         * 如果两个字符串均合法，那么按照定义 拼接成的字符串也同样合法
         * 对于边界条件，我们定义 dp[0]=true 表示空串且合法。
         */
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
