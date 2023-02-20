package com.learn.template;

import java.util.*;

public class Graph_BFS {
    private static final Integer START_NODE = 1;

    /**
     * 图: BFS
     */
    public int fn(int[][] graph) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        queue.add(START_NODE);
        seen.add(START_NODE);
        int ans = 0;

        while (!queue.isEmpty()) {
            int node = queue.remove();
            // 根据题意补充代码
            for (int neighbor: graph[node]) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return ans;
    }
}
