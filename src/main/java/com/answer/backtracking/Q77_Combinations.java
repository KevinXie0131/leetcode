package com.answer.backtracking;

import java.util.*;

public class Q77_Combinations {
    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }
    /**
     * n个数中求k个数的组合问题
     * 时间复杂度: O(n * 2^n)
     * 空间复杂度: O(n)
     * 回溯法模板
     * void backtracking(参数) {
     *     if (终止条件) {
     *         存放结果;
     *         return;
     *     }
     *     for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
     *         处理节点;
     *         backtracking(路径，选择列表); // 递归
     *         回溯，撤销处理结果
     *     }
     * }
     */
    static List<List<Integer>> result = new ArrayList<List<Integer>>(); // 存放符合条件结果的集合
    static List<Integer> path = new ArrayList<>(); // 用来存放符合条件结果

    public static List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);

        return result;
    }

    public static void backtracking(int n, int k, int startIndex){ // 未剪枝优化
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return; // 不要省略
        }
        for(int i = startIndex; i <= n - (k-path.size()) + 1; i++){
            path.add(i);  // 处理节点
            backtracking(n, k, i + 1);  // 递归
            path.remove(path.size() - 1);  // 回溯，撤销处理的节点
        }
    }
}
