package com.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test7_backtrack {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> result = subsets(nums);
        System.out.println(result);

    }
    static List<List<Integer>> output = new ArrayList();
    static int n, k;

    public static void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k) {
            output.add(new ArrayList(curr));
            return;
        }
        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    public static List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrack(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }

}


