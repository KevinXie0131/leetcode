package com.answer.backtracking;

import java.util.*;

public class Q40_Combination_Sum_II {

    public List<List<Integer>> combinationSum2(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[candidates.length];

        Arrays.sort(candidates);
        backtracking(candidates, used, target, 0, result, path);
        return result;
    }

    public void backtracking(int[] candidates, int[] used, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
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
            if(i > 0 && candidates[i-1] == candidates[i] && used[i-1] == 0){
                continue;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            used[i] = 1;
            backtracking(candidates, used, target, i+1, result, path);
            path.removeLast();
            target += candidates[i];
            used[i] = 0;
        }
        return;
    }
}
