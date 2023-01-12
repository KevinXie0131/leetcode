package com.leetcode.backtracking;

import java.util.*;

public class Test31_Comnbination {

    public static void main(String[] args) {
        List<List<Integer>> result = combine(4,2);
        System.out.println(result);
    }

    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    static List<Integer> path = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1);

        return result;
    }

    public static void backtracking(int n, int k, int startIndex){
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for(int i = startIndex; i <= n; i++){
            path.add(i);
            backtracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
