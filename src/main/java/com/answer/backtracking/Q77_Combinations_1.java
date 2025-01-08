package com.answer.backtracking;

import java.util.ArrayList;
import java.util.*;

public class Q77_Combinations_1 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();

        backtracking(n, k, 1, result, path);
        return result;
    }
    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    public void backtracking(int n, int k, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        /**
         * for(int i = startIndex; i <= n - (k - path.size()) + 1; i++){ // 剪枝优化
         */
        for(int i = startIndex; i <= n; i++){
            path.addLast(i);
            backtracking(n, k, i + 1, result, path);
            path.removeLast();
        }
     //   return;
    }
}
