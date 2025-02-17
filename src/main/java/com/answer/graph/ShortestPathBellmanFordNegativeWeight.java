package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFordNegativeWeight {
    /**
     * 本题是要我们判断 负权回路，也就是图中出现环且环上的边总权值为负数。
     * 如果在这样的图中求最短路的话， 就会在这个环里无限循环 （也是负数+负数 只会越来越小），无法求出最短路径。
     * 所以对于 在有负权值的图中求最短路，都需要先看看这个图里有没有负权回路。
     *
     * 那么解决本题的 核心思路，就是在 kama94.城市间货物运输I 的基础上，再多松弛一次，看minDist数组 是否发生变化。
     * 时间复杂度： O(N * E) , N为节点数量，E为图中边的数量
     * 空间复杂度： O(N) ，即 minDist 数组所开辟的空间
     *
     * 本题也是可以使用队列优化版的bellman_ford（SPFA）来做的, 如果节点加入队列的次数 超过了 n-1次 ，那么该图就一定有负权回路。
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
        int[] count = new int[n + 1]; // 记录节点加入队列几次
        count[1]++;

        // Declare a boolean array to record if the current node is in the queue to optimise the processing
        boolean[] isInQueue = new boolean[n + 1];
        // Declare a boolean value to check if there is a negative weight loop inside the graph
        boolean flag = false;

        while (!queue.isEmpty()) { // 开始松弛
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
                    // 如果加入队列次数超过 n-1次 就说明该图与负权回路
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