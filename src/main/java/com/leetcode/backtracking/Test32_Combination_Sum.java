package com.leetcode.backtracking;

import java.util.*;

public class Test32_Combination_Sum {

    public static void main(String[] args) {
       int[] candidates = {2,3,6,7};
       int target = 7;

        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }

    static public List<List<Integer>> combinationSum(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();

        backtracking(candidates, target, 0, result, path);
        return result;
    }

    public static void backtracking(int[] candidates, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking(candidates, target, i, result, path);
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
}
