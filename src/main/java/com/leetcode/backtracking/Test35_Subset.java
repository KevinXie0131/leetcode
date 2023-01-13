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
        backtracking(nums,0);
        return result;
    }

    public static void backtracking(int[] nums, int startIndex) {
        result.add(new ArrayList(path));
  /*      if(startIndex == nums.length ){
            return;
        }*/

        for(int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }

    }

}
