package com.answer.graph;

import java.util.*;

public class ShortestPathDijkstraHeap {
    /**
     *
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

        List<List<Edge1>> grid = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            grid.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int p1 = input[i][0];
            int p2 = input[i][1];
            int val = input[i][2];
            grid.get(p1).add(new Edge1(p2, val));
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

            if (visited[cur.first]) continue;

            // 2. 第二步，该最近节点被标记访问过
            visited[cur.first] = true;

            // 3. 第三步，更新非访问节点到源点的距离（即更新minDist数组）
            for (Edge1 edge : grid.get(cur.first)) { // 遍历 cur指向的节点，cur指向的节点为 edge
                // cur指向的节点edge.to，这条边的权值为 edge.val
                if (!visited[edge.to] && minDist[cur.first] + edge.val < minDist[edge.to]) { // 更新minDist
                    minDist[edge.to] = minDist[cur.first] + edge.val;
                    pq.add(new Pair<>(edge.to, minDist[edge.to]));
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

class MyComparison implements Comparator<Pair<Integer, Integer>> {
    @Override
    public int compare(Pair<Integer, Integer> lhs, Pair<Integer, Integer> rhs) {
        return Integer.compare(lhs.second, rhs.second);
    }
}

class Pair<U, V> {
    public final U first;
    public final V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}
