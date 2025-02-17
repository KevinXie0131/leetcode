package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFordFaster {
    /**
     * Bellman_ford 队列优化算法，也叫SPFA算法 (Shortest Path Faster Algorithm) 也称为Bellman-Ford队列优化算法（Queue improved Bellman-Ford）
     *  Bellman_ford 算法每次松弛 都是对所有边进行松弛. 但真正有效的松弛，是基于已经计算过的节点在做的松弛。所以 Bellman_ford 算法 每次都是对所有边进行松弛，其实是多做了一些无用功。
     *
     * 只需要对 上一次松弛的时候更新过的节点作为出发节点所连接的边 进行松弛就够了。
     * 用队列来记录上次松弛的时候更新过的节点。（其实用栈也行，对元素顺序没有要求）
     *
     * 所以如果图越稠密，则 SPFA的效率越接近与 Bellman_ford。反之，图越稀疏，SPFA的效率就越高。
     * 一般来说，SPFA 的时间复杂度为 O(K * N) K 为不定值，因为 节点需要计入几次队列取决于 图的稠密度。
     * 如果图是一条线形图且单向的话，每个节点的入度为1，那么只需要加入一次队列，这样时间复杂度就是 O(N)。
     * 所以 SPFA 在最坏的情况下是 O(N * E)，但 一般情况下 时间复杂度为 O(K * N)。
     *
     * 在有环且只有正权回路的情况下，即使元素重复加入队列，最后，也会因为 所有边都松弛后，节点数值（minDist数组）不在发生变化了 而终止。
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
        for (int i = 0; i < m; i++) {  // 将所有边保存起来
            int from = input[i][0];
            int to = input[i][1];
            int val = input[i][2];
            graph.get(from).add(new Edge3(from, to, val));   // p1 指向 p2，权值为 val
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
        // 加入优化，已经在队里里的元素不用重复添加
        boolean[] isInQueue = new boolean[n + 1];

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            // 从队列里取出的时候，要取消标记，我们只保证已经在队列里的元素不用重复加入
            isInQueue[curNode] = false; // Represents the current node is not in the queue after being polled
            for (Edge3 edge : graph.get(curNode)) {
                if (minDist[edge.to] > minDist[edge.from] + edge.val) { // Start relaxing the edge开始松弛
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                    if (!isInQueue[edge.to]) { // Don't add the node if it's already in the queue 已经在队列里的元素不用重复添加
                        queue.offer(edge.to);
                        isInQueue[edge.to] = true;
                    }
                }
            }
        }
        // Outcome printing
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected");  // 不能到达终点
        } else {
            System.out.println(minDist[n]); // 到达终点最短路径
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
