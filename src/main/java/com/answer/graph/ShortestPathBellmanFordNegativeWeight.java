package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFordNegativeWeight {
    /**
     *
     */
    public static void main(String[] args) {
        int[][] input = {{1, 2, -1},
                        {2, 3, 1},
                        {3, 1, -1},
                        {3, 4, 1}};
        bellmanFordNegativeWeight(4, 4, input );
    }

    public static void bellmanFordNegativeWeight(int vertex , int edgeNum,  int[][] input) {
        // Input processing
        // Input processing
        int n = vertex;
        int m = edgeNum;
        List<List<Edge4>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = input[i][0];
            int to = input[i][1];
            int val = input[i][2];
            graph.get(from).add(new Edge4(from, to, val));
        }

        // Declare the minDist array to record the minimum distance form current node to the original node
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Declare a queue to store the updated nodes instead of traversing all nodes each loop for more efficiency
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        // Declare an array to record the times each node has been offered in the queue
        int[] count = new int[n + 1];
        count[1]++;

        // Declare a boolean array to record if the current node is in the queue to optimise the processing
        boolean[] isInQueue = new boolean[n + 1];

        // Declare a boolean value to check if there is a negative weight loop inside the graph
        boolean flag = false;

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            isInQueue[curNode] = false; // Represents the current node is not in the queue after being polled
            for (Edge4 edge : graph.get(curNode)) {
                if (minDist[edge.to] > minDist[edge.from] + edge.val) { // Start relaxing the edge
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                    if (!isInQueue[edge.to]) { // Don't add the node if it's already in the queue
                        queue.offer(edge.to);
                        count[edge.to]++;
                        isInQueue[edge.to] = true;
                    }

                    if (count[edge.to] == n) { // If some node has been offered in the queue more than n-1 times
                        flag = true;
                        while (!queue.isEmpty()) queue.poll();
                        break;
                    }
                }
            }
        }

        if (flag) {
            System.out.println("circle");
        } else if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");
        } else {
            System.out.println(minDist[n]);
        }
    }
}
// 基于Bellman_ford-SPFA方法
// Define an inner class Edge
class Edge4 {
    int from;
    int to;
    int val;
    public Edge4(int from, int to, int val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }
}