package com.answer.backtracking;

import java.util.*;

public class BacktrackingTemplate1 {
    /**
     * 组合总和: 无重复元素的整数数组. 同一个数字可以无限制重复被选取. 解集不能包含重复的组合
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        backtracking1(candidates, target, 0, result, path);
        return result;
    }

    public void backtracking1(int[] candidates, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path){
        if (0 == target) {
            result.add(new ArrayList<>(path)); // 不要忘了new ArrayList
            return;
        }
        if(target < 0){ // should have this code
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            path.addLast(candidates[i]);
            target -= candidates[i];
            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            backtracking1(candidates, target, i, result, path); // 关键点:不⽤i + 1了，表示可以重复读取当前的数
            path.removeLast(); // 回溯
            target += candidates[i]; // 回溯
        }
        return;
    }
    /**
     * 组合总和: 排序 + 剪枝优化
     */
    public List<List<Integer>> combinationSum1a(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates); // 先进行排序
        backtracking1a(candidates, target, 0, result, path);
        return result;
    }

    public void backtracking1a(int[] candidates, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(target < 0){
            return;
        }
        // 对总集合排序之后，如果下一层的sum（就是本层的 sum + candidates[i]）已经大于target，就可以结束本轮for循环的遍历。
        // for(int i = startIndex; i < candidates.length && target >= candidates[i]; i++){
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){  // 剪枝优化 终⽌遍历 (先进行排序了)
                break;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking1a(candidates, target, i, result, path); // 关键点:不⽤i + 1了，表示可以重复读取当前的数
            path.removeLast();
            target += candidates[i];
        }
    }
    /**
     * 组合总和 II: 有重复元素的整数数组. 每个数字在每个组合中只能使用一次. 解集不能包含重复的组合.
     *             与 组合总和 的区别在于需要对结果去重，同时每个元素只能选取一次
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[candidates.length]; // 使用标记数组
        Arrays.sort(candidates); // 排序
        backtracking2(candidates, used, target, 0, result, path);
        return result;
    }

    public void backtracking2(int[] candidates, int[] used, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path){
        if (0 == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(target < 0){
            return;
        }
        // for(int i = startIndex; i < candidates.length && target >= candidates[i] ; i++){ //剪枝操作
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){ // 剪枝操作
                break;
            }
            // 去重
            if(i > 0 && candidates[i - 1] == candidates[i] && used[i - 1] == 0){ // 要对同⼀树层使⽤过的元素进⾏跳过
                continue;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            used[i] = 1;
            // 和 组合总和 的区别：这⾥是i + 1，每个数字在每个组合中只能使⽤⼀次
            backtracking2(candidates, used, target, i + 1, result, path); // 每个数组元素只能被选择一次, 设定下一轮从索引 i + 1 开始向后遍历
            path.removeLast();
            target += candidates[i];
            used[i] = 0;
        }
        return;
    }
    /**
     * 组合总和 II: 不使用标记数组used
     */
    public List<List<Integer>> combinationSum2a(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates); // 排序
        backtracking2a(candidates, target, 0, result, path);
        return result;
    }

    public void backtracking2a(int[] candidates, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path){
        if (0 == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(target < 0){
            return;
        }
        //  for(int i = startIndex; i < candidates.length && target >= candidates[i] ; i++){ //剪枝操作
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){ // 剪枝操作
                break;
            }
            // 去重: 跳过同一树层使用过的元素. 由于数组是已排序的，因此相等元素都是相邻的.
            // 这意味着在某轮选择中，若当前元素与其左边元素相等，则说明它已经被选择过，因此直接跳过当前元素。
            if(i > startIndex && candidates[i - 1] == candidates[i] ){ // 与used数组相似，当前数值已经在过去的遍历中被考虑进去了，所以这次可以跳过
                continue;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking2a(candidates, target,i + 1, result, path);// i+1 代表当前组内元素只选取一次
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
    /**
     * 组合总和 II: 使用HashSet本层去重
     */
    public List<List<Integer>> combinationSum2b(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates); // 排序
        backtracking2b(candidates, target, 0, result, path);
        return result;
    }

    public void backtracking2b(int[] candidates,  int target, int startIndex, List<List<Integer>> result, Deque<Integer> path){
        if (0 == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(target < 0){
            return;
        }
        HashSet<Integer> uset = new HashSet<>();  // 使用HashSet本层去重
        //  for(int i = startIndex; i < candidates.length && target >= candidates[i] ; i++){ //剪枝操作
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){ // 剪枝操作
                break;
            }

            if(uset.contains(candidates[i])){ // 同⼀树层只能使用一次
                continue;
            }
            uset.add(candidates[i]);

            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking2b(candidates, target,i + 1, result, path); // 每个数组元素只能被选择一次, 设定下一轮从索引 i+1 开始向后遍历
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
    /**
     * 组合总和 III: 在[1,2,3,4,5,6,7,8,9]这个集合中找到和为n的k个数的组合.
     * 无重复元素的整数数组. 每个数字最多使用一次. 解集不能包含重复的组合.
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] candidates = {1,2,3,4,5,6,7,8,9};
        backtracking3(candidates, k, n, 0, result, path);
        return result;
    }

    public void backtracking3(int[] candidates, int k, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path){
        if (0 == target && path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(target < 0){
            return; // 直接返回
        }
        if (path.size() > k) {
            return; // 直接返回
        }
        for(int i = startIndex; i < candidates.length; i++){
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking3(candidates, k, target, i + 1, result, path); // 注意i + 1调整
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
}
