package com.answer.union_find;

import java.util.*;

public class Q1971_Find_if_Path_Exists_in_Graph {
    /**
     * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
     * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes
     * a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge,
     * and no vertex has an edge to itself.
     * You want to determine if there is a valid path that exists from vertex source to vertex destination.
     * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
     * 寻找图中是否存在路径
     * 有一个具有 n 个顶点(vertices)的 双向(bi-directional ) 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。图中的边用一个二维整数数组 edges 表示，
     * 其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
     * 请你确定是否存在从顶点 source 开始，到顶点 destination 结束的 有效路径 。
     * 给你数组 edges 和整数 n、source 和 destination，如果从 source 到 destination 存在 有效路径 ，则返回 true，否则返回 false 。
     *
     * 输入：n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
     * 输出：true
     * 解释：存在由顶点 0 到顶点 2 的路径:
     *      - 0 → 1 → 2
     *      - 0 → 2
     */
    /**
     * 这道题目是并查集基础题目，题目中各个点是双向图链接，那么判断 一个顶点到另一个顶点有没有有效路径其实就是看这两个顶点是否在同一个集合里
     * 主要就是集合问题，两个节点在不在一个集合，也可以将两个节点添加到一个集合中。如何算是同一个集合呢，有边连在一起，就算是一个集合。
     */
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        _UnionFindTemplate disJoint = new _UnionFindTemplate(n + 1);
        for (int i = 0; i < edges.length; ++i) {
            disJoint.join(edges[i][0], edges[i][1]);
        }
        return disJoint.isSame(source, destination);
    }
    /**
     * 简单实现并查集
     */
    int[] connected;
    public boolean validPath6(int n, int[][] edges, int source, int destination) {
        connected = new int[n];
        for(int i = 0; i < n; i++) {
            connected[i] = i;
        }
        for(int[] edge : edges) {
            connected[find(edge[0])] = find(edge[1]);
        }
        return find(source) == find(destination);
    }

    int find(int x) {
        if(connected[x] == x) return x;
        return connected[x] = find(connected[x]);
    }
    /**
     * DFS深度优先搜索
     * 先将 edges 转换成图 graph，然后使用 DFS，判断是否存在从 source 到 destination 的路径。
     * 过程中，用数组 vis 记录已经访问过的顶点，避免重复访问。
     */
    private boolean[] visited;
    private ArrayList<Integer>[] graph;

    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        visited = new boolean[n];
        graph = new ArrayList[n];
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return dfs(source, destination);
    }

    private boolean dfs(int source, int destination) {
        if (source == destination) {
            return true;
        }
        visited[source] = true;
        for (int next : graph[source]) {
            if (!visited[next] && dfs(next, destination)) {
                return true;
            }
        }
        return false;
    }
    /**
     * DFS递归
     */
    public boolean validPath5(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[] adjacentArr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacentArr[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adjacentArr[edge[0]].add(edge[1]);
            adjacentArr[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        visited[source] = true;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(source);

        while (!stack.isEmpty() && !visited[destination]) { // 剪枝 !visited[destination]
            int vertex = stack.pop();
            List<Integer> adjacent = adjacentArr[vertex];
            for (int next : adjacent) {
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
        return visited[destination];
    }
    /**
     * BFS 广度优先搜索
     */
    public boolean validPath3(int n, int[][] edges, int source, int destination) {
        ArrayList<Integer>[] adjacentArr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjacentArr[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            adjacentArr[edge[0]].add(edge[1]);
            adjacentArr[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        visited[source] = true;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.offer(source);

        while (!queue.isEmpty() && !visited[destination]) { // 剪枝 !visited[destination]
            int vertex = queue.poll();
            List<Integer> adjacent = adjacentArr[vertex];
            for (int next : adjacent) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        return visited[destination];
    }
 }
