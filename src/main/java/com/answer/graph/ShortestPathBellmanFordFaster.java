package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFordFaster {
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
        bellmanFordFaster(6, 7, input );
    }

    public static void bellmanFordFaster (int vertex , int edgeNum,  int[][] input) {
        // Input processing
        int n = vertex;
        int m = edgeNum;
        List<List<Edge3>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = input[i][0];
            int to = input[i][1];
            int val = input[i][2];
            graph.get(from).add(new Edge3(from, to, val));
        }
        // Declare the minDist array to record the minimum distance form current node to the original node
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Declare a queue to store the updated nodes instead of traversing all nodes each loop for more efficiency
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        // Declare a boolean array to record if the current node is in the queue to optimise the processing
        boolean[] isInQueue = new boolean[n + 1];

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            isInQueue[curNode] = false; // Represents the current node is not in the queue after being polled
            for (Edge3 edge : graph.get(curNode)) {
                if (minDist[edge.to] > minDist[edge.from] + edge.val) { // Start relaxing the edge
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                    if (!isInQueue[edge.to]) { // Don't add the node if it's already in the queue
                        queue.offer(edge.to);
                        isInQueue[edge.to] = true;
                    }
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
class Edge3 {
    int from;
    int to;
    int val;
    public Edge3(int from, int to, int val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }
}
