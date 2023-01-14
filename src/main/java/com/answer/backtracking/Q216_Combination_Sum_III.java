package com.answer.backtracking;

import java.util.*;

public class Q216_Combination_Sum_III {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] candidates = {1,2,3,4,5,6,7,8,9};

        backtracking(candidates, k, n, 0, result, path);
        return result;
    }

    public static void backtracking(int[] candidates, int size, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target && path.size() == size) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return;
        }
        if (path.size() > size) {
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking(candidates, size, target, i + 1, result, path);
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
}
