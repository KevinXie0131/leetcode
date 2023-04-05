package com.answer.backtracking;

import java.util.*;

public class Q90_Subsets_II_1 {

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();
    int[] used;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        int[]  used = new int[nums.length];
        used = new int[nums.length];
        Arrays.sort(nums); // 去重需要排序
        backtracking(nums, 0, used);
        return result;
    }

    public void backtracking(int[] nums, int startIndex, int[] used){
        result.add(new ArrayList(path));
        if(startIndex == nums.length){
            return;
        }
        for(int i = startIndex; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1] && used[i-1] == 0){
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backtracking(nums, i + 1, used);
            used[i] = 0;
            path.removeLast();
        }
    }
}
