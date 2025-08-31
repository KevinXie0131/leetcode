package com.answer.graph;

import java.util.*;

public class Q323_Number_of_Connected_Components_in_an_Undirected_Graph {
    /**
     * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
     * write a function to find the number of connected components in an undirected graph.
     * Example 1:
     * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
     *      0          3
     *      |          |
     *      1 --- 2    4
     * Output: 2
     * Example 2:
     * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
     *      0           4
     *      |           |
     *      1 --- 2 --- 3
     * Output:  1
     * Note:
     * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     * 无向图中连通分量的数目
     * 有一个包含 n 个节点的图。给定一个整数 n 和一个数组 edges ，其中 edges[i] = [ai, bi] 表示图中 ai 和 bi 之间有一条边。
     * 返回 图中已连接分量的数目 。
     * 注意:你可以假设在 edges 中不会出现重复的边。而且由于所以的边都是无向边，[0, 1] 与 [1, 0] 相同，所以它们不会同时在 edges 中出现。
     */
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(countComponents_4(n, edges));
        int n1 = 5;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(countComponents_4(n1, edges1));
    }
    /**
     * 并查集
     */
    static int[] connected;

    static public int countComponents(int n, int[][] edges) {
        connected = new int[n];
        for(int i = 0; i < n; i++) {
            connected[i] = i;
        }

        for(int[] edge : edges){
            if(find(connected, edge[0]) != find(connected, edge[1])){
                union(connected, edge[0], edge[1]);
            }
        }
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (connected[i] == i) {
                ++count;
            }
        }
        return count;
    }
    /**
     * Every time two different roots are connected, we decrease the component count.
     */
    static public int countComponents_0(int n, int[][] edges) {
        connected = new int[n];
        for(int i = 0; i < n; i++) {
            connected[i] = i;
        }
        int count = n;
        for(int[] edge : edges){
            if(find(connected, edge[0]) != find(connected, edge[1])){
                union(connected, edge[0], edge[1]);
                count--;
            }
        }
        return count;
    }

    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    public static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
    /**
     * 深度优先搜索
     */
    static List<Integer>[] edgesList;
    static boolean[] visited;

    static public int countComponents1(int n, int[][] edges) {
        edgesList = new List[n];
        for (int i = 0; i < n; ++i) {
            edgesList[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            edgesList[edge[0]].add(edge[1]);
            edgesList[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(i);
                ++count;
            }
        }
        return count;
    }

    static public void dfs(int u) {
        visited[u] = true;
        for (int v : edgesList[u]) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }
    /**
     * BFS
     */
    static public int countComponents_4(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(graph, visited, i);
                count++;
            }
        }
        return count;
    }

    static void bfs(List<List<Integer>> graph, boolean[] visited, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }
}
