package com.answer.backtracking;

import java.util.*;

public class Q46_Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];

        backtracking(nums, used, result, path);
        return result;
    }

    public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path){
        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if( used[i] == 1){
                continue;
            }
            path.addLast(nums[i]);
            used[i] = 1;
            backtracking(nums, used, result, path);
            path.removeLast();
            used[i] = 0;
        }
        return;
    }
}
