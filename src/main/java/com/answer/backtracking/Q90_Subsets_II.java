package com.answer.backtracking;

import java.util.*;

public class Q90_Subsets_II {

    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();
    int[] used;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        int[]  used = new int[nums.length]; // 定义set对同⼀节点下的本层去重
        used = new int[nums.length];
        backtracking(nums, 0, used);
        return result;
    }

    public void backtracking(int[] nums, int startIndex, int[] used){
        result.add(new ArrayList(path));
        if(startIndex == nums.length){
            return;
        }
        for(int i = startIndex; i < nums.length; i++){
            int j = i;
            boolean isFoundSame = false;
            while (j > 0) {
                if (nums[i] == nums[j - 1] && used[j-1] == 0) {
                    isFoundSame = true;
                    break;
                }
                j--;
            }
            if(isFoundSame){
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
