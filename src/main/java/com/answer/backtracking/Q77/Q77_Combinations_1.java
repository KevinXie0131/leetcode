package com.answer.backtracking.Q77;

import java.util.ArrayList;
import java.util.*;

public class Q77_Combinations_1 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();

        backtracking(n, k, 1, result, path);
        return result;
    }

    public void backtracking(int n, int k, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        /**
         * for(int i = startIndex; i <= n - (k - path.size()) + 1; i++){
         */
        for(int i = startIndex; i <= n; i++){
            path.addLast(i);
            backtracking(n, k, i + 1, result, path);
            path.removeLast();
        }
        return;
    }
}
