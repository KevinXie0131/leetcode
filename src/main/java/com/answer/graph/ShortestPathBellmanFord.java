package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFord {
    /**
     * Bellman-Ford 是可以计算 负权值的单源最短路算法。
     * 本题依然是单源最短路问题，求 从 节点1 到节点n 的最小费用。 但本题不同之处在于 边的权值是有负数了。
     * Bellman_ford算法的核心思想是 对所有边进行松弛 (relax the edge) n-1 次操作（n为节点数量），从而求得目标最短路。
     * 如果 通过 A 到 B 这条边可以获得更短的到达B节点的路径，即如果 minDist[B] > minDist[A] + value，那么我们就更新 minDist[B] = minDist[A] + value ，这个过程就叫做 “松弛” 。
     * 其实 Bellman_ford算法 也是采用了动态规划的思想，即：将一个问题分解成多个决策阶段，通过状态之间的递归关系最后计算出全局最优解。
     *
     * 对所有边松弛一次，相当于计算 起点到达 与起点一条边相连的节点 的最短距离。
     * 那对所有边松弛两次 可以得到与起点 两条边相连的节点的最短距离。
     * 那对所有边松弛三次 可以得到与起点 三条边相连的节点的最短距离
     * 节点数量为n，那么起点到终点，最多是 n-1 条边相连。那么无论图是什么样的，边是什么样的顺序，我们对所有边松弛 n-1 次 就一定能得到 起点到达 终点的最短距离。
     *
     * 时间复杂度： O(N * E) , N为节点数量，E为图中边的数量
     * 空间复杂度： O(N) ，即 minDist 数组所开辟的空间
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
        // 将所有边保存起来
        for (int i = 0; i < m; i++) {
            int from = input[i][0];
            int to = input[i][1];
            int val = input[i][2];
            edges.add(new Edge2(from, to, val));  // p1 指向 p2，权值为 val
        }
        // Represents the minimum distance from the current node to the original node
        // 用minDist数组来表达 起点到各个节点的最短距离
        int[] minDist = new int[n + 1];
        // Initialize the minDist array
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[1] = 0;

        // Starts the loop to relax all edges n - 1 times to update minDist array
        // 对所有边 松弛 n-1 次: 松弛 n-1 次 是保证对任何图 都能最后求得到终点的最小距离
        // 1: 起点 n: 终点
        for (int i = 1; i < n; i++) {
            for (Edge2 edge : edges) { // 每一次松弛，都是对所有边进行松弛
                // Updates the minDist array
                // 松弛操作
                // minDist[from] != INT_MAX 防止从未计算过的节点出发
                if (minDist[edge.from] != Integer.MAX_VALUE && (minDist[edge.from] + edge.val) < minDist[edge.to]) {
                    minDist[edge.to] = minDist[edge.from] + edge.val;
                }
            }
            System.out.print("minDist数组: ");
            for (int k = 1; k <= n; k++) {
                System.out.print(minDist[k] + " ");
            }
            System.out.println();
        }
        // Outcome printing
        if (minDist[n] == Integer.MAX_VALUE) {
            System.out.println("unconnected"); // 不能到达终点
        } else {
            System.out.println(minDist[n]); // 到达终点最短路径
        }
    }
}
// Define an inner class Edge
class Edge2 {
    int from; // 边的出发点
    int to;   // 边的到达点
    int val;  // 边的权值
    public Edge2(int from, int to, int val) {
        this.from = from;
        this.to = to;
        this.val = val;
    }
}