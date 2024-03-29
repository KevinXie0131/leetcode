package com.leetcode.backtracking;

import java.util.*;

public class Test35_Subset {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};

        List<List<Integer>>  list = subsets(nums);
        System.out.println(list);
    }

    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    static Deque<Integer> path = new LinkedList<>();

    public static List<List<Integer>> subsets(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }

        int[]  used = new int[nums.length];

        backtracking(nums,0, used);
        return result;
    }

    public static void backtracking(int[] nums, int startIndex, int[] used) {
        result.add(new ArrayList(path));
        if(startIndex == nums.length ){
            return;
        }

        for(int i = startIndex; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1] && used[i-1] == 0){
                continue;
            }

            path.add(nums[i]);
            used[i] = 1;
            backtracking(nums, i + 1, used);
            path.removeLast();
            used[i] = 0;
        }

    }

}
