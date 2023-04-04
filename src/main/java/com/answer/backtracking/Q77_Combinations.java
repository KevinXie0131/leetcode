package com.answer.backtracking;

import java.util.*;

public class Q77_Combinations {
    public static void main(String[] args) {
        System.out.println(combine(4, 2));
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
        }
        for(int i = startIndex; i <= n; i++){
            path.add(i);
            backtracking(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
