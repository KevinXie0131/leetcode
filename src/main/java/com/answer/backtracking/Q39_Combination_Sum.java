package com.answer.backtracking;

import java.util.*;

public class Q39_Combination_Sum {
    /**
     * 回溯 版本一
     * 组合没有数量要求
     * 元素可无限重复选取
     * 依据每个位置的元素可选取的次数进行搜索
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();

        backtracking(candidates, target, 0, result, path);
        return result;
    }
    /**
     * 本题还需要startIndex来控制for循环的起始位置，对于组合问题，什么时候需要startIndex呢？
     * 我举过例子，如果是一个集合来求组合的话，就需要startIndex，例如：77.组合 ，216.组合总和III。
     * 如果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex，例如：17.电话号码的字母组合
     */
    public void backtracking(int[] candidates, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<Integer>(path)); // 不要忘了new ArrayList
            return;
        }
        if(target < 0){
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            path.addLast(candidates[i]);
            target -= candidates[i];
            // 注意本题和77.组合  、216.组合总和III  的一个区别是：本题元素为可重复选取的。
            backtracking(candidates, target, i, result, path); // 关键点:不⽤i+1了，表示可以重复读取当前的数
            path.removeLast(); // 回溯
            target += candidates[i]; // 回溯
        }
        return;
    }
}
