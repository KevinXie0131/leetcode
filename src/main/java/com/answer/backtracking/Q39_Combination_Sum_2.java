package com.answer.backtracking;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;
import java.util.List;

public class Q39_Combination_Sum_2 {

    public List<List<Integer>> combinationSum(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[candidates.length];

        Arrays.sort(candidates);
        backtracking(candidates, used, target, 0, result, path);
        return result;
    }
    // 应该是Q40的答案
    public void backtracking(int[] candidates, int[] used, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){ // 剪枝优化 终⽌遍历
                break;
            }
            if(i > 0 && candidates[i-1] == candidates[i] && used[i] == 0){ // 没有发生作用
                break;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            used[i] = 1;
            backtracking(candidates, used, target, i, result, path); // 关键点:不⽤i+1了，表示可以重复读取当前的数
            path.removeLast();
            target += candidates[i];
        //    used[i] = 1;
        }
        return;
    }
}
