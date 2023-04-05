package com.answer.backtracking;

import java.util.*;

public class Q491_Non_decreasing_Subsequences_1 {
    /**
     * 这份代码在leetcode上提交，要⽐版本⼀耗时要好的多。
     */
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
            //return;  // 注意这⾥不要加return，要取树上的节点
        }

        int[] used = new int[201];

        for (int i = startIndex; i < nums.length; i++) {
            if ((!path.isEmpty() && nums[i] < path.getLast())
                    || used[nums[i] + 100] == 1) {
                continue;
            }
            used[nums[i] + 100] = 1; // 记录这个元素在本层⽤过了，本层后⾯不能再⽤了
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

}
