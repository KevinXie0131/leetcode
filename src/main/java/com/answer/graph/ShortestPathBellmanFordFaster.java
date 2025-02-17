package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFordFaster {
    /**
     * Bellman_ford 队列优化算法，也叫SPFA算法 (Shortest Path Faster Algorithm) 也称为Bellman-Ford队列优化算法（Queue improved Bellman-Ford）
     *  Bellman_ford 算法每次松弛 都是对所有边进行松弛. 但真正有效的松弛，是基于已经计算过的节点在做的松弛。所以 Bellman_ford 算法 每次都是对所有边进行松弛，其实是多做了一些无用功。
     *
     * 只需要对 上一次松弛的时候更新过的节点作为出发节点所连接的边 进行松弛就够了。
     * 用队列来记录上次松弛的时候更新过的节点。（其实用栈也行，对元素顺序没有要求）
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
        // minDist数组来表达 起点到各个节点的最短距离，例如minDist[3] = 5 表示起点到达节点3 的最小距离为5
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
