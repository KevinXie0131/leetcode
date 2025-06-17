package com.answer.dfs_bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q785_Is_Graph_Bipartite {
    /**
     * 判断二分图
     * 存在一个 无向undirected图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u 的邻接adjacent节点组成。
     * 形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边(undirected edge)。该无向图同时具有以下属性：
     *  不存在自环(no self-edges)（graph[u] 不包含 u）。
     *  不存在平行边(no parallel edges)（graph[u] 不包含重复duplicate值）。
     *  如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向undirected图）
     *  这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
     * 二分图bipartite 定义：如果能将一个图的节点集合分割成两个独立的子集(partitioned into two independent sets) A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
     * 如果图是二分图，返回 true ；否则，返回 false 。
     *
     * 输入：graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
     * 输出：false
     * 解释：不能将节点分割成两个独立的子集，以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。
     *      There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
     *
     * 输入：graph = [[1,3],[0,2],[1,3],[0,2]]
     * 输出：true
     * 解释：可以将节点分成两组: {0, 2} 和 {1, 3} 。
     */
    /**
     * Approach #1: Coloring by Depth-First Search - Recursion
     *
     * 二分图定义：图中的顶点由两个集合组成，且所有边的两个顶点正好分别处在两个集合里
     * 更形象化地去表示：我们可以用两种颜色代表这两个集合，相邻的顶点不能是同一种颜色
     *
     * 二分图的代码逻辑可以这样写：
     * - 遍历节点 v 的所有相邻节点
     *   1. 如果相邻节点 neighbor 没有被访问过, 那么应该给节点 neighbor 涂上和节点 v 不同的颜色
     *   2. 相邻节点 neighbor 已经被访问过, 那么应该比较节点 neighbor 和节点 v 的颜色, 若相同，则此图不是二分图
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
    /**
     * Approach 2: Breadth First Search
     */
    public boolean isBipartite_3(int[][] graph) {
        // 定义 visited 数组，初始值为 0 表示未被访问，赋值为 1 或者 -1 表示两种不同的颜色。
        int[] visited = new int[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        // 因为图中可能含有多个连通域，所以我们需要判断是否存在顶点未被访问，若存在则从它开始再进行一轮 bfs 染色。
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] != 0) {
                continue;
            }
            // 每出队一个顶点，将其所有邻接点染成相反的颜色并入队。
            queue.offer(i);
            visited[i] = 1;
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w: graph[v]) {
                    // 如果当前顶点的某个邻接点已经被染过色了，且颜色和当前顶点相同，说明此无向图无法被正确染色，返回 false。
                    if (visited[w] == visited[v]) {
                        return false;
                    }
                    if (visited[w] == 0) {
                        visited[w] = -visited[v];
                        queue.offer(w);
                    }
                }
            }
        }

        return true;
    }
}
