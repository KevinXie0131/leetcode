package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFordSingleSource {
    /**
     *
     */
    public static void main(String[] args) {
        int[][] input = {{1, 2, 1},
                        {2, 4, -3},
                        {2, 5, 2},
                        {1, 3, 5},
                        {3, 5, 1},
                        {4, 6, 4},
                        {5, 6, -2}};
        bellmanFordSingleSource(6, 7, input );
    }

    public static void bellmanFordSingleSource(int vertex , int edgeNum,  int[][] input) {
        // Input processing
        int n = vertex;
        int m = edgeNum;

        List<Edge5> graph = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = input[i][0];
            int to = input[i][1];
            int val = input[i][2];
            graph.add(new Edge5(from, to, val));
        }
        int src = 2;
        int dst = 6;
        int k =  1;

        int[] minDist = new int[n + 1];
        int[] minDistCopy;

        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0;

        for (int i = 0; i < k + 1; i++) { // Relax all edges k + 1 times
            minDistCopy = Arrays.copyOf(minDist, n + 1);
            for (Edge5 edge : graph) {
                int from = edge.from;
                int to = edge.to;
                int val = edge.val;
                // Use minDistCopy to calculate minDist
                if (minDistCopy[from] != Integer.MAX_VALUE && minDist[to] > minDistCopy[from] + val) {
                    minDist[to] = minDistCopy[from] + val;
                }
            }
        }
        // Output printing
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable");
        } else {
            System.out.println(minDist[dst]);
        }
    }
}
// 基于Bellman_for一般解法解决单源最短路径问题
// Define an inner class Edge
class Edge5 {
    int from;
    int to;
    int val;
    public Edge5(int from, int to, int val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }
}