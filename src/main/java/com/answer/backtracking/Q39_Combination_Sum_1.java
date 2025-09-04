package com.answer.backtracking;

import java.util.*;

public class Q39_Combination_Sum_1 {
    /**
     * 剪枝优化
     * 时间复杂度: O(n * 2^n)，注意这只是复杂度的上界，因为剪枝的存在，真实的时间复杂度远小于此
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target)    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates); // 先进行排序
        backtracking(candidates, target, 0, result, path);
        return result;
    }

    public void backtracking(int[] candidates, int target, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (0 == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(target < 0){
            return;
        }
        // 对总集合排序之后，如果下一层的sum（就是本层的 sum + candidates[i]）已经大于target，就可以结束本轮for循环的遍历。
       /* for(int i = startIndex; i < candidates.length && target >= candidates[i]; i++){*/
        for(int i = startIndex; i < candidates.length; i++){
            if(target < candidates[i]){  // 剪枝优化 终⽌遍历
                break;
            }
            path.addLast(candidates[i]);
            target -= candidates[i];
            backtracking(candidates, target, i, result, path); // 关键点:不⽤i+1了，表示可以重复读取当前的数
            path.removeLast();
            target += candidates[i];
        }
        return;
    }
}
