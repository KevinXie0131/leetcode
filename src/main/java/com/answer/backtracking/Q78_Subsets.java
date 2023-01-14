package com.answer.backtracking;

import java.util.*;

public class Q78_Subsets {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        backtracking(nums,0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        result.add(new ArrayList(path));
        /**
         * if(startIndex > nums.length - 1){
         */
        if(startIndex == nums.length ){
            return;
        }

        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }

    }
}
