package com.answer.backtracking;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;
import java.util.List;

public class Q47_Permutations_II {
    /**
     * 这道题⽬和回溯算法：排列问题！的区别在与给定⼀个可包含重复数字的序列，要返回所有不重复的全排列。
     * 这⾥又涉及到去重了
     * 是去重⼀定要对元素经⾏排序，这样我们才⽅便通过相邻的节点来判断是否重复使⽤了。
     *
     * ⼀般来说：组合问题和排列问题是在树形结构的叶⼦节点上收集结果，⽽⼦集问题就是取树上所有节点的结果
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length];

        Arrays.sort(nums); // 排序

        backtracking(nums, used, result, path);
        return result;
    }

    public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path) {
        if (path.size() == nums.length) { // 此时说明找到了⼀组
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if(i> 0 && nums[i] == nums[i-1] && used[i-1] == 0) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = 1;
            backtracking(nums, used, result, path);
            path.removeLast();
            used[i] = 0;
        }
        return;
    }
}
