package com.answer.dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q785_Is_Graph_Bipartite_1 {
    /**
     * 并查集
     * 遍历图中每个顶点，将当前顶点的所有邻接点进行合并，并判断这些邻接点中是否存在某一邻接点已经和当前顶点处于同一个集合中了，若是，则说明不是二分图。
     */
    int[] connected;

    public boolean isBipartite(int[][] graph) {
        connected = new int[graph.length];
        for(int i = 0; i < graph.length; i++) {
            connected[i] = i;
        }
        for(int i = 0; i < graph.length; i++){
            int current = find(connected, i);
            for(int j = 0; j < graph[i].length; j++){
                if(find(connected, graph[i][j]) == current){
                    return false;
                }
                union(connected, graph[i][0], graph[i][j]); // 如果是二分图的话，那么图中每个顶点的所有邻接点都应该属于同一集合，且不与顶点处于同一集合。
            }
        }
        return true;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
