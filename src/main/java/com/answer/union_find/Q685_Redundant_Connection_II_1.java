package com.answer.union_find;

import java.util.*;

public class Q685_Redundant_Connection_II_1 { // Hard 困难
    /**
     * 并查集
     * 在第 684 题「冗余连接」中，我们处理的是 无向图，只需要检测循环并删除造成循环的边即可。然而，第 685 题「冗余连接 II」则涉及 有向图，并且需要满足以下两个条件：
     *  除了根节点之外，每个节点只有一个父节点（即每个节点的入度为1，根节点的入度为0）。
     *  图中没有循环
     * 因此，我们需要处理以下两种可能的异常情况：
     *  某个节点有两个父节点（入度为2）。
     *  图中存在循环。
     * 为了解决这些问题，我们结合使用 入度检查 和 并查集（Union-Find） 来有效地找到并删除冗余的边。
     */
    private int N = 1001;
    private int[] parent = new int[N];

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] inDegree = new int[n + 1];
        Arrays.fill(inDegree, 0);
        for(int i = 0; i < n; i++){
            inDegree[edges[i][1]]++;
        }
        Deque<Integer> st = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            if(inDegree[edges[i][1]] == 2){
                st.push(i);
            }
        }
        if(!st.isEmpty()){
            if(isTreeAfterRemoveEdge(edges, st.peek())){
                return edges[st.peek()];
            }else{
                st.pop();
                return edges[st.pop()];
            }
        }
        return getRemoveEdge(edges);
    }

    public int[] getRemoveEdge(int[][] edges){
        int n = edges.length;
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            if(find(parent, node1) == find(parent, node2)){
                return edge;
            }else{
                parent[find(parent, node2)] = find(parent, node1);
            }
        }
        return new int[0];
    }

    public boolean isTreeAfterRemoveEdge(int[][] edges, int index){
        int n = edges.length;
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        for(int i = 0; i < n; i++){
            if(i == index){
                continue;
            }
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if(find(parent, node1) == find(parent, node2)){
                return false;
            }else{
                parent[find(parent, node2)] = find(parent, node1);
            }
        }
        return true;
    }

    public int find(int[] parent, int index){
        if(parent[index] != index){
            parent[index] = parent[find(parent, parent[index])];
        }
        return parent[index];
    }
    /**
     * refer to template
     * works too
     */
    public int[] getRemoveEdge1(int[][] edges){
        int n = edges.length;
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            if(isSame(node1, node2)){
                return edge;
            }else{
                join(node1, node2);
            }
        }
        return new int[0];
    }

    public boolean isTreeAfterRemoveEdge1(int[][] edges, int index){
        int n = edges.length;
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }
        for(int i = 0; i < n; i++){
            if(i == index){
                continue;
            }
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if(isSame(node1, node2)){
                return false;
            }else{
               join(node1, node2);
            }
        }
        return true;
    }

    public int find(int n) {
        return n == parent[n] ? n : (parent[n] = find(parent[n]));
    }

    public void join (int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        }
        parent[m] = n; // 找到根节点后，x根做y根的子树，y根做x根的子树都可以
    }

    public boolean isSame(int n, int m){
        n = find(n);
        m = find(m);
        return n == m;
    }
}
