package com.leetcode.backtracking;

import java.util.*;

public class Test37_Non_Decreasing_Subsequences {
    public static void main(String[] args) {
        int[] nums = {4,6,7,7};

        List<List<Integer>> list = findSubsequences(nums);
        System.out.println(list);
    }

    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    static Deque<Integer> path = new LinkedList<>();

    static public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }

        backtracking(nums,0);
        return result;
    }

    static public void backtracking(int[] nums, int startIndex) {
        if(path.size() >= 2){
            result.add(new ArrayList(path));
            System.out.println("add -> " + path);
            //return;
        }
        HashSet<Integer> uset = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast()){
                continue;
            }
            if(!uset.add(nums[i])) {
                System.out.println("uset -> " + uset);
                continue;
            }
            System.out.println("uset -> " + uset);
            path.add(nums[i]);
            System.out.println("path -> " + path);
            backtracking(nums, i + 1);
            path.removeLast();
            System.out.println("path -> " + path);
        }


    }
}
