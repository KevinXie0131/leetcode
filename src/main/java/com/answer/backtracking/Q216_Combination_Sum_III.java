package com.answer.backtracking;

import java.util.*;

public class Q216_Combination_Sum_III {
    /**
     * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
     *  - Only numbers 1 through 9 are used.
     *  - Each number is used at most once.
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     *  - 只使用数字1到9
     *  - 每个数字 最多使用一次
     */
    /**
     * 本题就是在[1,2,3,4,5,6,7,8,9]这个集合中找到和为n的k个数的组合。
     * 本题k相当于树的深度，9（因为整个集合就是9个数）就是树的宽度。
     */
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 9));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>(); // 存放结果集
        Deque<Integer> path = new ArrayDeque<>(); // 符合条件的结果
        int[] candidates = {1,2,3,4,5,6,7,8,9};
        // targetSum：⽬标和，也就是题⽬中的n。
        // k：题⽬中要求k个数的集合。
        // sum：已经收集的元素的总和，也就是path⾥元素的总和。
        // startIndex：下⼀层for循环搜索的起始位置。
        backtracking(candidates, k, n, 0, result, path);
        return result;
    }

    public static void backtracking(int[] candidates, int size, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target && path.size() == size) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return; // 直接返回
        }
        if (path.size() > size) {
            return; // 直接返回
        }
        for(int i = startIndex; i < candidates.length; i++){
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking(candidates, size, target, i + 1, result, path); // 注意i+1调整
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
    /**
     * 别忘了处理过程 和 回溯过程是一一对应的，处理有加，回溯就要有减！
     */
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> combinationSum3_1(int k, int n) {
        backtracking1(k, n, 0, 1);
        return result;
    }
    public void backtracking1(int k, int targetSum, int sum, int startIndex){
        if (sum > targetSum) { // 剪枝操作
            return;
        }
        // 上面剪枝 i <= 9 - (k - path.size()) + 1; 如果还是不清楚
        // 也可以改为 if (path.size() > k) return; 执行效率上是一样的
        if (path.size() > k) return;

        if(path.size() == k && sum == targetSum){
            result.add(new ArrayList(path)); // 如果path.size() == k 但sum != targetSum 直接返回
            return;
        }

        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) { // 剪枝
     //   for(int i = startIndex; i <=9; i++){
            sum += i;  // 处理
            path.add(i); // 处理
/*            if (sum > targetSum) { // 剪枝操作
                sum -= i; // 剪枝之前先把回溯做了
                path.removeLast(); // 剪枝之前先把回溯做了
                return;
            }*/
            backtracking1(k, targetSum, sum, i + 1); // 注意i+1调整startIndex
            sum -= i;  // 回溯
            path.removeLast(); // 回溯
        }
    }
    /**
     * 没有剪枝操作
     */
    public void backtracking2(int k, int targetSum, int sum, int startIndex){
        if(path.size() == k && sum == targetSum){
            result.add(new ArrayList(path));
            return;
        }

        for(int i = startIndex; i <=9; i++){
            sum += i;
            path.add(i);
            backtracking2(k, targetSum, sum, i + 1);
            sum -= i;
            path.removeLast();
        }
    }
}
