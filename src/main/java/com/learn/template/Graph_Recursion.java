package com.learn.template;

import java.util.*;

public class Graph_Recursion {
    private static final int START_NODE = 1;
    /**
     * 图: DFS (递归)
     */
    Set<Integer> seen = new HashSet<>();

    public int fn(int[][] graph) {
        seen.add(START_NODE);
        return dfs(START_NODE, graph);
    }

    public int dfs(int node, int[][] graph) {
        int ans = 0;
        // 根据题意补充代码
        for (int neighbor: graph[node]) {
            if (!seen.contains(neighbor)) {
                seen.add(neighbor);
                ans += dfs(neighbor, graph);
            }
        }

        return ans;
    }
}
