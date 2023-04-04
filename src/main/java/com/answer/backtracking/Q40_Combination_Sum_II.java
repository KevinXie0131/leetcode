package com.answer.backtracking;

import java.util.*;

public class Q40_Combination_Sum_II {

    public List<List<Integer>> combinationSum2(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[candidates.length]; // 加⼀个bool型数组used，⽤来记录同⼀树枝上的元素是否使⽤过
                                                 // 这个集合去重的重任就是used来完成的
        // ⾸先把给candidates排序，让其相同的元素都挨在⼀起
        Arrays.sort(candidates);
        backtracking(candidates, used, target, 0, result, path);
        return result;
    }

    public void backtracking(int[] candidates, int[] used, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){ // 剪枝操作
                break;
            }
            /**
             * 去重
             * 要去重的是“同⼀树层上的使⽤过”，如果判断同⼀树层上元素（相同的元素）是否使⽤过了呢
             *
             * 如果candidates[i] == candidates[i - 1] 并且 used[i - 1] == false，就说明：前
             * ⼀个树枝，使⽤了candidates[i - 1]，也就是说同⼀树层使⽤过candidates[i - 1]
             *
             * 在candidates[i] == candidates[i - 1]相同的情况下：
             *      used[i - 1] == true，说明同⼀树⽀candidates[i - 1]使⽤过
             *      used[i - 1] == false，说明同⼀树层candidates[i - 1]使⽤过
             * 要对同⼀树层使⽤过的元素进⾏跳过
             */
            if(i > 0 && candidates[i-1] == candidates[i] && used[i-1] == 0){
                continue;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            used[i] = 1;
            /**
             * 和39.组合总和的区别1：这⾥是i+1，每个数字在每个组合中只能使⽤⼀次
             */
            backtracking(candidates, used, target, i + 1, result, path);
            path.removeLast();
            target += candidates[i];
            used[i] = 0;
        }
        return;
    }
}
