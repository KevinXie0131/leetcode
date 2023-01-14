package com.answer.backtracking;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;
import java.util.List;

public class Q47_Permutations_II {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];

        Arrays.sort(nums);

        backtracking(nums, used, result, path);
        return result;
    }

    public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            if(i> 0 && nums[i] == nums[i-1] && used[i-1] == 0) {
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
