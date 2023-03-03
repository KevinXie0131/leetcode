package com.learn.template.graph;

import java.util.*;

public class BreadthFirstSearch {
    /**
     * 1971. Find if Path Exists in Graph
     */
    public boolean validPath(int n, int[][] edges, int start, int end) {
        List<List<Integer>> adjacency_list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacency_list.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adjacency_list.get(edge[0]).add(edge[1]);
            adjacency_list.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean seen[] = new boolean[n];
        Arrays.fill(seen, false);
        seen[start] = true;

        while (!queue.isEmpty()) {
            // Get the current node.
            int node = queue.poll();

            // Check if we have reached the target node.
            if (node == end) {
                return true;
            }

            // Add all neighbors to the stack.
            for (int neighbor : adjacency_list.get(node)) {
                // Check if neighbor has been added to the queue before.
                if (!seen[neighbor]) {
                    seen[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }
}
