package com.answer.dfs_bfs;

import java.util.Arrays;
import java.util.Stack;

public class Q785_Is_Graph_Bipartite {

    /**
     * Approach #1: Coloring by Depth-First Search - Recursion
     */
    //涂颜色使用的颜色，分别是：无色、红色和绿色
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    private int[] color; //用于记录节点颜色
    private boolean valid;  //记录是否二分图

    public boolean isBipartite(int[][] graph) {
        int n = graph.length; //图节点数量
        valid = true;
        color = new int[n];
        Arrays.fill(color, UNCOLORED);

        for (int i = 0; i < n && valid; ++i) {
            if (color[i] == UNCOLORED) { //题中没有说明一定是连通图，所以需要每个没有涂色的节点作为起始节点涂颜色
                dfs(i, RED, graph);
            }
        }
        return valid;
    }
    public void dfs(int node, int col, int[][] graph) {
        color[node] = col;
        int cNei; //这次遍历中当前节点相邻节点应该涂的颜色
        if(col == RED){
            cNei = GREEN;
        }else{
            cNei = RED;
        }
        //遍历当前节点相邻所有节点
        for (int neighbor : graph[node]) {
            if (color[neighbor] == UNCOLORED) { //如果相邻接点还没涂色，则继续深度优先遍历涂色
                dfs(neighbor, cNei, graph);
                //判断上面遍历已经确定图不是二分图
                if (!valid) {
                    return;
                }
            } else if (color[neighbor] != cNei) { //如果相邻节点已经有颜色，并且跟这次遍历本该涂的颜色不一致，说明不是二分图
                valid = false;
                return;
            }
        }
    }
    /**
     * Approach #1: Coloring by Depth-First Search - Use stack
     */
    public boolean isBipartite_1(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int start = 0; start < n; ++start) {
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack();
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == -1) {
                            stack.push(nei);
                            color[nei] = color[node] ^ 1;
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
