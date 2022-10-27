package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test6_Permutation1 {

    public static void main(String[] args) {

        int[] nums = new int[]{1,1,2};
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

        Set<Integer> set = new HashSet<>();

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            if (set.add(nums[index])) {
                dfs(res, nums, index + 1);
            }
            swap(nums, i, index);
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
