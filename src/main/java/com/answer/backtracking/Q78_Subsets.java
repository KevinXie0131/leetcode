package com.answer.backtracking;

import java.util.*;

public class Q78_Subsets {
    /**
     * 求取⼦集问题，不需要任何剪枝！因为⼦集就是要遍历整棵树。
     */
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        backtracking(nums,0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        result.add(new ArrayList(path)); // 收集⼦集，要放在终⽌添加的上⾯，否则会漏掉⾃⼰
        /**
         * if(startIndex > nums.length - 1){
         */
        if(startIndex == nums.length ){
            return;
        }

        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            backtracking(nums, i + 1); // 注意从i+1开始，元素不重复取
            path.removeLast();
        }

    }
}
