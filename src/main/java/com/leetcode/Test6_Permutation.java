package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Test6_Permutation {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = permu(nums);
        System.out.println(result.size());
        System.out.println(result);
    }

    public static List<List<Integer>> permu(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, 0);
        return res;
    }

    private static void dfs(List<List<Integer>> res, int[] nums, int index) {

        if (index == nums.length) {
            List<Integer> result = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                result.add(nums[i]);
            }
            res.add(result);
            return;
        }

        for (int i = index; i < nums.length; i++) {
             swap(nums, index, i);
             dfs(res, nums, index + 1);
             swap(nums, i, index);
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}