package com.answer.graph;

import java.util.*;

public class MinimumSpanningTreeKruskal {
    /**
     * prim 算法是维护节点的集合，而 Kruskal 是维护边的集合。
     *
     * kruscal的思路：
     *      边的权值排序，因为要优先选最小的边加入到生成树里
     *      遍历排序后的边
     *      如果边首尾的两个节点在同一个集合，说明如果连上这条边图中会出现环
     *      如果边首尾的两个节点不在同一个集合，加入到最小生成树，并把两个节点加入同一个集合
     *
     * 但在代码中，如果将两个节点加入同一个集合，又如何判断两个节点是否在同一个集合呢？
     * 这里就涉及到我们之前讲解的并查集。我们在并查集开篇的时候就讲了，并查集主要就两个功能：
     *      将两个元素添加到一个集合中
     *      判断两个元素在不在同一个集合
     *
     * 时间复杂度：nlogn （快排） + logn （并查集） ，所以最后依然是 nlogn 。n为边的数量。
     */
    public static void main(String[] args) {
        int[][] input = {{1, 2, 1},
                {1, 3, 1},
                {1, 5, 2},
                {2, 6, 1},
                {2, 4, 2},
                {2, 3, 2},
                {3, 4, 1},
                {4, 5, 1},
                {5, 6, 2},
                {5, 7, 1},
                {6, 7, 1}};
        kruskal(7, 11, input );
    }

    static int n = 10001; // 节点数量 节点编号是从1开始的，n要大一些
    static int[] father = new int[n]; // 并查集标记节点关系的数组

    public static void kruskal(int vertex , int edgeNo,  int[][] input) {
        int v = vertex;
        int e = edgeNo;
        List<Edge> edges = new ArrayList<>();
        int resultVal = 0;

        for (int i = 0; i < e; i++) {
            int v1 = input[i][0];
            int v2 = input[i][1];
            int val = input[i][2];
            edges.add(new Edge(v1, v2, val));
        }
        // 执行Kruskal算法
        // 按边的权值对边进行从小到大排序
        edges.sort(Comparator.comparingInt(edge -> edge.val));
        // 并查集初始化
        init();
        // 从头开始遍历边
        for (Edge edge : edges) {
            // 并查集，搜出两个节点的祖先
            int x = find(edge.l);
            int y = find(edge.r);
            // 如果祖先不同，则不在同一个集合
            if (x != y) {
                resultVal += edge.val;  // 这条边可以作为生成树的边
                join(x, y); // 两个节点加入到同一个集合
            }
        }
        System.out.println(resultVal);
    }
    // 并查集初始化
    public static void init() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }
    // 并查集的查找操作
    public static int find(int u) {
        if (u == father[u]) return u;
        return father[u] = find(father[u]); // 路径压缩
    }
    // 并查集的加入集合
    public static void join(int u, int v) {
        u = find(u); // 寻找u的根
        v = find(v); // 寻找v的根
        if (u == v) return;  // 如果发现根相同，则说明在一个集合，不用两个节点相连直接返回
        father[v] = u;
    }
}

class Edge {
    int l, r, val; // l,r为 边两边的节点，val为边的数值

    Edge(int l, int r, int val) {
        this.l = l;
        this.r = r;
        this.val = val;
    }
}

