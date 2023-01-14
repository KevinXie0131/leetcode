package com.answer.backtracking.Q77;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;
import java.util.List;

public class Q39_Combination_Sum_1 {

    public List<List<Integer>> combinationSum(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, result, path);
        return result;
    }

    public void backtracking(int[] candidates, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){
                break;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking(candidates, target, i, result, path);
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
}
