package com.answer.graph;

import java.util.*;

public class ShortestPathDijkstraHeap {
    /**
     * 邻接矩阵 (二维数组来表示图结构)
     *      在 边少，节点多的情况下，会导致申请过大的二维数组，造成空间浪费。而且在寻找节点链接情况的时候，需要遍历整个矩阵，即 n * n 的时间复杂度，同样造成时间浪费。
     * 邻接矩阵的优点：
     *      表达方式简单，易于理解
     *      检查任意两个顶点间是否存在边的操作非常快
     *      适合稠密图，在边数接近顶点数平方的图中，邻接矩阵是一种空间效率较高的表示方法。
     * 缺点：
     *      遇到稀疏图，会导致申请过大的二维数组造成空间浪费 且遍历 边 的时候需要遍历整个n * n矩阵，造成时间浪费
     *
     * 邻接表 (数组 + 链表)
     *       邻接表是从边的数量来表示图，有多少边 才会申请对应大小的链表。有多少边 邻接表才会申请多少个对应的链表节点。
     * 邻接表的优点：
     *      对于稀疏图的存储，只需要存储边，空间利用率高
     *      遍历节点链接情况相对容易
     * 缺点：
     *      检查任意两个节点间是否存在边，效率相对低，需要 O(V)时间，V表示某节点链接其他节点的数量。
     *      实现相对复杂，不易理解
     *
     * 直接把 边（带权值）加入到 小顶堆（利用堆来自动排序），那么每次我们从 堆顶里 取出 边 自然就是 距离源点最近的节点所在的边。
     * 这样我们就不需要两层for循环来寻找最近的节点了。
     *
     * 以上代码思路 和 朴素版dijkstra 是一样一样的，主要区别是两点：
     *      邻接表的表示方式不同
     *      使用优先级队列（小顶堆）来对新链接的边排序
     *
     * 时间复杂度：O(ElogE) E 为边的数量 (堆优化的时间复杂度 只和边的数量有关 和节点数无关，在 优先级队列中 放的也是边。所以该算法时间复杂度 和 节点没有关系)
     * 空间复杂度：O(N + E) N 为节点的数量 (邻接表是 数组 + 链表 数组的空间 是 N ，有E条边 就申请对应多少个链表节点，所以是 复杂度是 N + E)
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
        dijkstraHeap(7, 9, input );
    }

    public static void dijkstraHeap(int num , int row,  int[][] input) {
        int n = num;
        int m = row;

        List<List<Edge1>> grid = new ArrayList<>(n + 1);  // 邻接表
        for (int i = 0; i <= n; i++) {
            grid.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int p1 = input[i][0];
            int p2 = input[i][1];
            int val = input[i][2];
            grid.get(p1).add(new Edge1(p2, val)); // // p1 指向 p2，权值为 val
        }

        int start = 1;  // 起点
        int end = n;    // 终点

        // 存储从源点到每个节点的最短距离
        int[] minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // 记录顶点是否被访问过
        boolean[] visited = new boolean[n + 1];

        // 优先队列中存放 Pair<节点，源点到该节点的权值>
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(new MyComparison());

        // 初始化队列，源点到源点的距离为0，所以初始为0
        pq.add(new Pair<>(start, 0));

        minDist[start] = 0;  // 起始点到自身的距离为0

        while (!pq.isEmpty()) {
            // 1. 第一步，选源点到哪个节点近且该节点未被访问过（通过优先级队列来实现）
            // <节点， 源点到该节点的距离>
            Pair<Integer, Integer> cur = pq.poll();

            if (visited[cur.first]) continue; // cur.first 是指取 pair<int, int> 里的第一个int，即节点编号

            // 2. 第二步，该最近节点被标记访问过
            visited[cur.first] = true;

            // 3. 第三步，更新非访问节点到源点的距离（即更新minDist数组）
            // for循环是用来做什么的？ 是为了 找到 节点cur 链接指向了哪些节点，因为使用邻接矩阵的表达方式 所以把所有节点遍历一遍。
            // 所以在邻接表中，我们要获取 节点cur 链接指向哪些节点，就是遍历 grid[cur节点编号] 这个链表。
            for (Edge1 edge : grid.get(cur.first)) { // 遍历 cur指向的节点，cur指向的节点为 edge
                // cur指向的节点edge.to，这条边的权值为 edge.val
                if (!visited[edge.to] && minDist[cur.first] + edge.val < minDist[edge.to]) { // 更新minDist
                    minDist[edge.to] = minDist[cur.first] + edge.val;
                    pq.add(new Pair<>(edge.to, minDist[edge.to]));// 由于cur节点的加入，而新链接的边，加入到优先级队里中
                }
            }
        }

        if (minDist[end] == Integer.MAX_VALUE) {
            System.out.println(-1); // 不能到达终点
        } else {
            System.out.println(minDist[end]); // 到达终点最短路径
        }
    }
}

class Edge1 {
    int to;  // 邻接顶点
    int val; // 边的权重

    Edge1(int to, int val) {
        this.to = to;
        this.val = val;
    }
}
// 小顶堆
class MyComparison implements Comparator<Pair<Integer, Integer>> {
    @Override
    public int compare(Pair<Integer, Integer> lhs, Pair<Integer, Integer> rhs) {
        return Integer.compare(lhs.second, rhs.second); // 小顶堆需要按照权值来排序
    }
}
// pair<节点编号，源点到该节点的权值>
class Pair<U, V> {
    public final U first;
    public final V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}
