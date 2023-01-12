package com.leetcode.backtracking;

import java.util.ArrayDeque;
import java.util.*;

public class Test33_Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        List<List<Integer>> result = permute(nums);
        System.out.println(result);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];

        backtracking(nums, used, result, path);
        return result;
    }

    static public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
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
