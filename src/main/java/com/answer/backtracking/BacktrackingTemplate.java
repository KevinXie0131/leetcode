package com.answer.backtracking;

import java.util.*;

public class BacktrackingTemplate {
    /**
     * 排列问题，讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为不同列表时），需要记录哪些数字已经使用过，此时用 used 数组；
     * 组合问题，不讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为相同列表时），需要按照某种顺序搜索，此时使用 startIndex 变量。
     *
     * 如果是一个集合来求组合的话，就需要startIndex
     * 如果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex
     */
    /**
     * 组合: 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合
     *       不含重复数字的数组
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        backtracking(n, k, 1, result, path); // start from 1
        return result;
    }

    public void backtracking(int n, int k, int startIndex, List<List<Integer>> result, Deque<Integer> path){
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
     // for(int i = startIndex; i <= n - (k - path.size()) + 1; i++){  // 剪枝优化 (列表中剩余元素 (n - i) = 所需需要的元素个数 (k - path.size()). 为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。)
        for(int i = startIndex; i <= n; i++){ // 要求的是组合数，不考虑元素顺序，i 是从startIndex开始的，这个和Permutation不同
            if(n - i <  k - path.size() - 1) {  // 剪枝优化
                break;
            }
            path.addLast(i);
            backtracking(n, k, i + 1, result, path);
            path.removeLast();
        }
    }
    /**
     * 全排列: 不含重复数字的数组
     *        排列问题的不同: 每层都是从0开始搜索⽽不是startIndex
     *                       需要used数组记录path⾥都放了哪些元素了
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length]; // 排列问题需要一个used数组，标记已经选择的元素
        backtracking(nums, used, result, path);
        return result;
    }

    public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path){
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){ // i 是从0开始的，这个和Combination不同, for循环里不用startIndex
            if(used[i] == 1){ // used数组，其实就是记录此时path里都有哪些元素使用了，一个排列里一个元素只能使用一次
                continue;
            }
            path.addLast(nums[i]);
            used[i] = 1;
            backtracking(nums, used, result, path); // 处理排列问题就不⽤使⽤startIndex
            path.removeLast();
            used[i] = 0;
        }
    }
    /**
     * 全排列: (解法2) 通过判断path中是否存在数字，排除已经选择的数字
     */
    public void backtracking1(int[] nums, List<List<Integer>> result, Deque<Integer> path){
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0 ; i < nums.length; i++){
            if(path.contains(nums[i])){ // 如果path中已有，则跳过
                continue;
            }
            path.addLast(nums[i]);
            backtracking1(nums, result, path);
            path.removeLast();
        }
    }
    /**
     * 全排列 II: 包含重复数字的数组，返回所有不重复的全排列
     *           有重复元素，所以需要去重
     *           去重⼀定要对元素经⾏排序，这样才⽅便通过相邻的节点来判断是否重复使⽤了
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];
        Arrays.sort(nums); // 排序
        backtracking2(nums, used, result, path);
        return result;
    }

    public void backtracking2(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path) {
        if (path.size() == nums.length) { // 此时说明找到了⼀组
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            // 如果要对树层中前一位去重，就用used[i - 1] == 0，
            // 如果要对树枝前一位去重, 用used[i - 1] == 1。
            // 对于排列问题，树层上去重和树枝上去重，都是可以的，但是树层上去重效率更高！
         // if(i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 1) // works too
            // 因为是排列，从0开始
            if(i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) { //如果与上一个数值相同，但是上一个数值没有使用过，则跳过
                continue; // 有重复元素，所以需要去重
            }
            path.addLast(nums[i]);
            used[i] = 1;
            backtracking2(nums, used, result, path);
            path.removeLast();
            used[i] = 0;
        }
    }
    /**
     * 全排列 II: 使用set去重的版本 (相对于used数组的版本效率都要低很多)
     */
    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used  = new boolean[nums.length];
        Arrays.sort(nums); // 排序
        backtracking3(res, path, nums, used);
        return res;
    }

    public void backtracking3(List<List<Integer>> res, List<Integer> path, int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        HashSet<Integer> hashSet = new HashSet<>(); // 层去重

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true) { // 枝去重
                continue;
            }
            if (hashSet.contains(nums[i])) { // 控制某一节点下的同一层元素不能重复
                continue;
            }
            hashSet.add(nums[i]); // 记录元素

            used[i] = true;
            path.add(nums[i]); // path.addLast(nums[i]); // works too
            backtracking3(res, path, nums, used);
            path.remove(path.size() - 1); // path.removeLast( ); // works too
            used[i] = false;
        }
    }
    /**
     * 电话号码的字母组合: 每一个数字代表的是不同集合，也就是求不同集合之间的组合
     * 不需要startIndex来控制for循环的起始位置. 果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex
     */
    public List<String> letterCombinations(String digits) {
        String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result = new ArrayList<>();
        backtracking(digits, result, 0, new StringBuffer(), map);
        return result;
    }

    public void backtracking(String digits, List<String> result, int index, StringBuffer path, String[] map){
        if(path.length() == digits.length()){
            result.add(path.toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        String str = map[digit];
        for(int i = 0; i < str.length(); i++){
            path.append(str.charAt(i));
            backtracking(digits, result, index + 1, path, map);
            path.deleteCharAt(path.length()- 1);
        }
    }
    /**
     * 单词拆分: Optimized by memorization 记忆化递归
     */
    public boolean wordBreak0a(String s, List<String> wordDict) {
        Set<String> wordSet =  new HashSet<>();
        wordSet.addAll(wordDict);
        int[] memo =  new int[s.length()]; // 记忆化递归
        return backtracking(wordSet, s, 0, memo);
    }

    private boolean backtracking( Set<String> wordSet, String s, int startIndex,  int[] memo) {
        if(startIndex == s.length()){
            return true;
        }
        if (memo[startIndex] == -1) { // 记忆化递归
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
        memo[startIndex] = -1; // 这里是关键，找遍了startIndex~s.length()也没能完全匹配，标记从startIndex开始不能找到
        return false;
    }
}
