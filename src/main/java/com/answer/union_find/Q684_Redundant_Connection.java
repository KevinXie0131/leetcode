package com.answer.union_find;

import java.util.Arrays;
import java.util.*;

public class Q684_Redundant_Connection {
    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}};
     //   int[][] edges = {{1,2},{2,3},{3,4},{1,4},{1,5}};
        System.out.println(Arrays.toString(findRedundantConnection(edges)));
    }
    /**
     * 并查集基础题目。并查集可以解决什么问题：两个节点是否在一个集合，也可以将两个节点添加到一个集合中。
     * 已经判断 节点A 和 节点B 在在同一个集合（同一个根），如果将 节点A 和 节点B 连在一起就一定会出现环。
     * 判断一下边的两个节点在不在同一个集合就可以了
     */
    public int[] findRedundantConnection0(int[][] edges) {
        DisJoint disJoint = new DisJoint(edges.length + 1);
        for (int i = 0; i < edges.length; ++i) {
            if(disJoint.isSame(edges[i][0], edges[i][1])){
                return new int[]{edges[i][0], edges[i][1]};
            }else{
                disJoint.join(edges[i][0], edges[i][1]);
            }
        }
        return new int[]{0, 0};
    }
    /**
     * Approach #2: Union-Find
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);
            } else {
                return edge;
            }
        }
        return new int[0];
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
     * Approach #1: DFS
     */
    Set<Integer> seen = new HashSet();
    int MAX_EDGE_VAL = 1000;

    public int[] findRedundantConnection_1(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGE_VAL + 1];
        for (int i = 0; i <= MAX_EDGE_VAL; i++) {
            graph[i] = new ArrayList();
        }

        for (int[] edge: edges) {
            seen.clear();
            if (!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() &&
                    dfs(graph, edge[0], edge[1])) {
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        throw new AssertionError();
    }
    public boolean dfs(ArrayList<Integer>[] graph, int source, int target) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(graph, nei, target)) return true;
            }
        }
        return false;
    }
}
