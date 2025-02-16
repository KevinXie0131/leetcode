package com.answer.graph;

import java.util.Arrays;

public class ShortestPathDijkstra {
    /**
     * Dijkstra求最短路，最短路是图论中的经典问题即：给出一个有向图，一个起点，一个终点，问起点到终点的最短路径。
     * Dijkstra算法：在有权图（权值非负数）中求从起点到其他节点的最短路径算法。
     * 需要注意两点：
     *      Dijkstra 算法可以同时求 起点到所有节点的最短路径
     *      权值不能为负数
     * Dijkstra 算法 同样是贪心的思路，不断寻找距离 源点最近的没有访问过的节点。
     *
     * Dijkstra三部曲 (prim 和 dijkstra 确实很像)：
     *      第一步，选源点到哪个节点近且该节点未被访问过
     *      第二步，该最近节点被标记访问过
     *      第三步，更新非访问节点到源点的距离（即更新minDist数组）
     * minDist数组 用来记录 每一个节点距离源点的最小距离（示例中节点编号是从1开始，所以为了让大家看的不晕，minDist数组下标我也从 1 开始计数，下标0 就不使用了，这样 下标和节点标号就可以对应上了，避免大家搞混）
     * 时间复杂度：O(n^2) 空间复杂度：O(n^2)
     *
     * prim是求 非访问节点到最小生成树的最小距离，而 dijkstra是求 非访问节点到源点的最小距离
     */
    public static void main(String[] args) {
        int[][] input = {{1, 2, 1},
                        {1, 3, 4},
                        {2, 3, 2},
                        {2, 4, 5},
                        {3, 4, 2},
                        {4, 5, 3},
                        {2, 6, 4},
                        {5, 7, 4},
                        {6, 7, 9}};
        dijkstra(7, 9, input );
    }

    public static void dijkstra(int num , int row,  int[][] input) {
        int n = num;
        int m = row;
        int[][] grid = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(grid[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < m; i++) {
            int p1 = input[i][0];
            int p2 = input[i][1];
            int val = input[i][2];
            grid[p1][p2] = val;
        }
        int start = 1;
        int end = n;
        // 存储从源点到每个节点的最短距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        // 记录顶点是否被访问过
        boolean[] visited = new boolean[n + 1];
        minDist[start] = 0;  // 起始点到自身的距离为0

        int[] parent = new int[n + 1]; // 使用一维数组就可以记录。parent[节点编号] = 节点编号，这样就把一条边记录下来了。
        Arrays.fill(parent, -1);

        for (int i = 1; i <= n; i++) { // 遍历所有节点
            int minVal = Integer.MAX_VALUE;
            int cur = 1;
            // 1、选距离源点最近且未访问过的节点
            for (int v = 1; v <= n; ++v) {
                if (!visited[v] && minDist[v] < minVal) {
                    minVal = minDist[v];
                    cur = v;
                }
            }
            visited[cur] = true;  // 2、标记该节点已被访问
            // 3、第三步，更新非访问节点到源点的距离（即更新minDist数组）
            for (int v = 1; v <= n; v++) {
                if (!visited[v] && grid[cur][v] != Integer.MAX_VALUE && minDist[cur] + grid[cur][v] < minDist[v]) {
                    // minDist表示 节点到源点的最小距离，所以 新节点 cur 的加入，需要使用 源点到cur的距离 （minDist[cur]） + cur 到 节点 v 的距离 （grid[cur][v]），
                    // 才是 源点到节点v的距离
                    minDist[v] = minDist[cur] + grid[cur][v];
                    parent[v] = cur;
                }
            }
            // 打印日志：
            System.out.println("select:" + cur );
            for (int v = 1; v <= n; v++) {
                System.out.print(v + ":" + minDist[v] + " ");
            }
            System.out.println();
        }
        if (minDist[end] == Integer.MAX_VALUE) {
            System.out.println(-1); // 不能到达终点
        } else {
            System.out.println(minDist[end]); // 到达终点最短路径
        }
        // 输出最短情况
        for (int i = 1; i <= n; i++) {
            System.out.println(parent[i] + "->" + i);
        }
    }
}
