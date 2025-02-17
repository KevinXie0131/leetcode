package com.answer.graph;

import java.util.*;

public class ShortestPathBellmanFordSingleSource {
    /**
     * 本题为单源有限最短路问题，同样是 kama94.城市间货物运输I 延伸题目。
     * 注意题目中描述是 最多经过 k 个城市的条件下，而不是一定经过k个城市，也可以经过的城市数量比k小，但要最短的路径。
     *
     * 所以本题就是求：起点最多经过k + 1 条边到达终点的最短距离。bellman_ford 标准写法是松弛 n-1 次，本题就松弛 k + 1次就好。
     * 时间复杂度： O(K * E) , K为至多经过K个节点，E为图中边的数量
     * 空间复杂度： O(N) ，即 minDist 数组所开辟的空间
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
        // 请计算在最多经过 k 个城市的条件下，从城市 src 到城市 dst 的最低运输成本。
        int src = 2;
        int dst = 6;
        int k =  1;

        int[] minDist = new int[n + 1];
        // 那么本题 为什么计算minDist 一定要基于上次 的 minDist 数值。
        // 其关键在于本题的两个因素：
        //   本题可以有负权回路，说明只要多做松弛，结果是会变的。
        //   本题要求最多经过k个节点，对松弛次数是有限制的。
        int[] minDistCopy;  // 用来记录上一次遍历的结果

        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[src] = 0;

        for (int i = 0; i < k + 1; i++) { // Relax all edges k + 1 times 对所有边松弛 k + 1次
            minDistCopy = Arrays.copyOf(minDist, n + 1);  // 获取上一次计算的结果
            for (Edge5 edge : graph) {
                int from = edge.from;
                int to = edge.to;
                int val = edge.val;
                // Use minDistCopy to calculate minDist 注意使用 minDist_copy 来计算 minDist
                if (minDistCopy[from] != Integer.MAX_VALUE && minDist[to] > minDistCopy[from] + val) {
                    minDist[to] = minDistCopy[from] + val;
                }
            }
        }
        // Output printing
        if (minDist[dst] == Integer.MAX_VALUE) {
            System.out.println("unreachable"); // 不能到达终点
        } else {
            System.out.println(minDist[dst]); // 到达终点最短路径
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