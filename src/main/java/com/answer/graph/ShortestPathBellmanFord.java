package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFord {
    /**
     *
     */
    public static void main(String[] args) {
        int[][] input = {{5, 6, -2},
                {1, 2, 1},
                {5, 3, 1},
                {2, 5, 2},
                {2, 4, -3},
                {4, 6, 4},
                {1, 3, 5}};
        bellmanFord(6, 7, input );
    }

    public static void bellmanFord(int vertex , int edgeNum,  int[][] input) {
        // Input processing
        int n = vertex;
        int m = edgeNum;
        List<Edge2> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = input[i][0];
            int to = input[i][1];
            int val = input[i][2];
            edges.add(new Edge2(from, to, val));
        }

        // Represents the minimum distance from the current node to the original node
        int[] minDist = new int[n + 1];

        // Initialize the minDist array
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Starts the loop to relax all edges n - 1 times to update minDist array
        for (int i = 1; i < n; i++) {

            for (Edge2 edge : edges) {
                // Updates the minDist array
                if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to]) {
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                }
            }
        }

        // Outcome printing
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}

// Define an inner class Edge
class Edge2 {
    int from;
    int to;
    int val;
    public Edge2(int from, int to, int val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }
}