package com.answer.backtracking;

import java.util.*;

public class Q216_Combination_Sum_III {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 9));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>(); // 存放结果集
        Deque<Integer> path = new ArrayDeque<>(); // 符合条件的结果
        int[] candidates = {1,2,3,4,5,6,7,8,9};
        // targetSum：⽬标和，也就是题⽬中的n。
        // k：题⽬中要求k个数的集合。
        // sum：已经收集的元素的总和，也就是path⾥元素的总和。
        // startIndex：下⼀层for循环搜索的起始位置。
        backtracking(candidates, k, n, 0, result, path);
        return result;
    }

    public static void backtracking(int[] candidates, int size, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target && path.size() == size) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return; // 直接返回
        }
        if (path.size() > size) {
            return; // 直接返回
        }
        for(int i = startIndex; i < candidates.length; i++){
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking(candidates, size, target, i + 1, result, path); // 注意i+1调整
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
}
