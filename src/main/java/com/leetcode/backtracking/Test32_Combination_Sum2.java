package com.leetcode.backtracking;

import java.util.*;

public class Test32_Combination_Sum2 {
    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;

        List<List<Integer>> result = combinationSum2(candidates, target);
        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[candidates.length];

        Arrays.sort(candidates);
        backtracking(candidates, used, target, 0, result, path);
        return result;
    }

    public static void backtracking(int[] candidates, int[] used, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if(target < 0){
            return;
        }
        for(int i = startIndex; i < candidates.length; i++){
            /**
             * // 大剪枝：减去 candidates[i] 小于 0，减去后面的 candidates[i + 1]、candidates[i + 2] 肯定也小于 0，因此用 break
             */
            if(target < candidates[i]){
                break;
            }
            /**
             * remove duplicates
             */
            if(i > 0 && candidates[i-1] == candidates[i] && used[i-1] == 0){
                continue;
            }
            /**
             * // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
             */
            //正确剔除重复解的办法
            //跳过同一树层使用过的元素
/*            if ( i > startIndex && candidates[i] == candidates[i - 1] ) {
                continue;
            }*/
            path.addLast(candidates[i]);
            target -= candidates[i];
            used[i] = 1;
            backtracking(candidates, used, target, i+1, result, path);
            path.removeLast();
            target += candidates[i];
            used[i] = 0;
        }
        return;
    }
}
