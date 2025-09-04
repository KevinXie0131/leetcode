package com.answer.backtracking;

import java.util.*;

public class Q491_Non_decreasing_Subsequences_1 {
    public static void main(String[] args) {
        int[]  nums = {4,7, 6,7};
    //    System.out.println(findSubsequences(nums));
    }
    /**
     * 这份代码在leetcode上提交，要⽐版本⼀耗时要好的多。
     * 其实用数组来做哈希，效率就高了很多。
     * 注意题目中说了，数值范围[-100,100]，所以完全可以用数组来做哈希
     * -100 <= nums[i] <= 100
     */
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        backtracking(nums,0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        if(path.size() >= 2){
            result.add(new ArrayList(path));
            //return;  // 注意这⾥不要加return，要取树上的节点
        }
        /**
         * 一定要新建数组
         */
        // 这里使用数组来进行去重操作，题目说数值范围[-100, 100]
        int[] used = new int[201]; // boolean[] used   = new boolean[201];

        for (int i = startIndex; i < nums.length; i++) {
            if ((!path.isEmpty() && nums[i] < path.getLast())
                    || used[nums[i] + 100] == 1) {  // it is ||
                continue;
            }
            used[nums[i] + 100] = 1; // 记录这个元素在本层⽤过了，本层后⾯不能再⽤了
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

}
