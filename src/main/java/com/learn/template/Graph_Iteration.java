package com.learn.template;

import java.util.*;

public class Graph_Iteration {
    private static final Integer START_NODE = 1;

    /**
     * 图: DFS (迭代)
     */
    public int fn(int[][] graph) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        stack.push(START_NODE);
        seen.add(START_NODE);
        int ans = 0;

        while (!stack.empty()) {
            int node = stack.pop();
            // 根据题意补充代码
            for (int neighbor: graph[node]) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }

        return ans;
    }

}
