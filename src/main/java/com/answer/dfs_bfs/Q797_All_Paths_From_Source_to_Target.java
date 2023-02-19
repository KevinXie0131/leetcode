package com.answer.dfs_bfs;

import java.util.*;

public class Q797_All_Paths_From_Source_to_Target {
    /**
     * 本题中的图为有向无环图DAG, 搜索过程中不会反复遍历同一个点, 因此我们无需判断当前点是否遍历过
     * Approach 1: Backtracking
     */
    List<List<Integer>> res= new ArrayList<>();
    Deque<Integer> stack = new ArrayDeque<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        stack.push(0);
        dfs(graph, 0 , graph.length - 1);
        return res;
    }

    public void dfs(int[][] graph, int x, int n) {
        if(x == n){
            List<Integer> list = new ArrayList<>(stack);
            Collections.reverse(list);
            res.add(list);
            return;
        }
        for(int y : graph[x]){
            stack.push(y);
            dfs(graph, y , graph.length - 1);
            stack.pop();
        }
    }
}
