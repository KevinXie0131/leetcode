package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.*;

public class Test31_Comnbination1 {

    public static void main(String[] args) {
        List<List<Integer>> result = combine(4,2);
        System.out.println(result);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();

        backtracking(n, k, 1, result, path);
        return result;
    }

    public static void backtracking(int n, int k, int startIndex, List<List<Integer>> result, Deque<Integer> path ){
        if (path.size() == k) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
     //   for(int i = startIndex; i <= n; i++){
        for(int i = startIndex; i <= n - (k - path.size()) + 1; i++){
            path.addLast(i);
            backtracking(n, k, i + 1, result, path);
            path.removeLast();
        }
        return;
    }
}
