package com.answer.backtracking;

import java.util.*;

public class Q491_Non_decreasing_Subsequences {

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }

        backtracking(nums,0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        if(path.size() >= 2){
            result.add(new ArrayList(path));
            //return;
        }
        HashSet<Integer> uset = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast()){
                continue;
            }
            if(!uset.add(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

}
