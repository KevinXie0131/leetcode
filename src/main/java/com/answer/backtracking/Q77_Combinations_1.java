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
         * 如果for循环选择的起始位置之后的元素个数 已经不足 我们需要的元素个数了，那么就没有必要搜索了
         * 优化过程如下：
         *  已经选择的元素个数：path.size();
         *  所需需要的元素个数为: k - path.size();
         *  列表中剩余元素（n-i） >= 所需需要的元素个数（k - path.size()）
         *  在集合n中至多要从该起始位置 : i <= n - (k - path.size()) + 1，开始遍历
         *  为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。
         */
/*        for(int i = startIndex; i <= n - (k - path.size()) + 1; i++){  */ // 剪枝优化
        for(int i = startIndex; i <= n; i++){
            path.addLast(i);
            backtracking(n, k, i + 1, result, path);
            path.removeLast();
        }
     //   return;
    }
    /**
     * 另一种形式
     */
    public void backtracking1(int n, int k, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (k == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for(int i = startIndex; i <= n; i++){
            path.addLast(i);
            backtracking1(n, k - 1, i + 1, result, path);
            path.removeLast();
        }
    }
}
