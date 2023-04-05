package com.answer.backtracking;

import java.util.*;

public class Q46_Permutations {
    /**
     * 排列问题的不同:
     *    每层都是从0开始搜索⽽不是startIndex
     *    需要used数组记录path⾥都放了哪些元素了
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];

        backtracking(nums, used, result, path);
        return result;
    }

    public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path){
        if (path.size() == nums.length) {  // 此时说明找到了⼀组
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if( used[i] == 1){
                continue; // path⾥已经收录的元素，直接跳过
            }
            path.addLast(nums[i]);
            used[i] = 1;
            backtracking(nums, used, result, path); // 所以处理排列问题就不⽤使⽤startIndex了
            path.removeLast();
            used[i] = 0;
        }
        return;
    }
}
