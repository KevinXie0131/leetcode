package com.answer.union_find;

import java.util.*;

public class Q547_Number_of_Provinces {
    /**
     * 省份数量
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     */
    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}; // 输出：2
    //    int[][] isConnected = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
      /* 1 0 0 1
         0 1 1 0
         0 1 1 0
         1 0 1 1  */
        System.out.println(findCircleNum(isConnected));
    }
    /**
     * 递归
     */
    public static int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        int count = 0;
        boolean[] hasChecked = new boolean[m];

        for (int i = 0; i < m; i++) {
            if (hasChecked[i] == false) {
                hasChecked[i] = true;
                recrusion(isConnected, i, hasChecked);
                count++;
            }
        }
        return count;
    }

    public static void recrusion(int[][] isConnected, int i, boolean[] hasChecked) {
        for(int k = 0; k < isConnected[0].length; k++){
            if(isConnected[i][k] == 1 && !hasChecked[k]){
                hasChecked[k] = true;
                recrusion(isConnected, k, hasChecked);
            }
        }
    }
    /**
     * Approach #1 Using Depth First Search
     * The given matrix can be viewed as the Adjacency Matrix of a graph. By viewing the matrix in such a manner
     * The problem reduces to the problem of finding the number of connected components in an undirected graph
     */
    public static int findCircleNum_0(int[][] isConnected) {
        int cityNum = isConnected.length;
        Integer count = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] hasChecked = new boolean[cityNum];

        for(int i = 0; i < cityNum; i++){
            if(!hasChecked[i]){
                stack.push(i);
                while(!stack.isEmpty()){
                    int j = stack.pop();
                    hasChecked[j] = true;
                    for(int k = 0; k < cityNum; k++){
                        if(isConnected[j][k] == 1 && !hasChecked[k]){
                            stack.push(k);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
    /**
     * Approach #1 Using Depth First Search - Recursion
     */
    public static int findCircleNum_0a(int[][] isConnected) {
        int cityNum = isConnected.length;
        Integer count = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] hasChecked = new boolean[cityNum];

        for(int i = 0; i < cityNum; i++){
            if(!hasChecked[i]){
                hasChecked[i] = true;
                dfsRecursion(isConnected, hasChecked, i);
                count++;
            }
        }
        return count;
    }

    public static void dfsRecursion(int[][] isConnected, boolean[] hasChecked, int i){
        for(int j = 0; j < isConnected.length; j++){
            if(isConnected[i][j] == 1 && !hasChecked[j]){
                hasChecked[j] = true;
                dfsRecursion(isConnected, hasChecked, j);
            }
        }
    }
    /**
     * Approach #2 Using Breadth First Search
     */
    public static int findCircleNum_1(int[][] isConnected) {
        int cityNum = isConnected.length;
        Integer count = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] hasChecked = new boolean[cityNum];

        for(int i = 0; i < cityNum; i++){
            if(!hasChecked[i]){
                deque.offer(i);
                while(!deque.isEmpty()){
                    int j = deque.poll();
                    hasChecked[j] = true;
                    for(int k = 0; k < cityNum; k++){
                        if(isConnected[j][k] == 1 && !hasChecked[k]){
                            deque.offer(k);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
    /**
     * Approach #3 Using Union-Find Method
     */
    public static int findCircleNum_3(int[][] isConnected) {
        int count = 0;
        int cityNum = isConnected.length;
        int[] parent = new int[cityNum];
        for(int i = 0; i < cityNum; i++){
            parent[i] = i;
        }
        for(int i = 0; i < cityNum; i++){
            for(int j = i + 1; j < cityNum; j++) {
                if(isConnected[i][j] == 1){
                    union(parent, i , j);
                }
            }
        }
        for(int i = 0; i < cityNum; i++){
            if(parent[i] == i){
                count++;
            }
        }
        return count;
    }

    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
    /**
     * 简单实现并查集
     */
    int[] connected;
    public int findCircleNum_6(int[][] isConnected) {
        int n = isConnected.length;
        connected = new int[n];
        for(int i = 0; i < n; i++) {
            connected[i] = i;
        }
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    connected[find(i)] = find(j);
                }
            }
        }
        int count = 0;
        for(int i = 0; i < n; i++){
            if(connected[i] == i){
                count++;
            }
        }
        return count;
    }

    int find(int x) {
        if(connected[x] == x) return x;
        return connected[x] = find(connected[x]);
    }
    /**
     * int find(int parent[], int i) {
     *     if (parent[i] == -1)
     *         return i;
     *     return find(parent, parent[i]);
     * }
     *
     * void union(int parent[], int x, int y) {
     *    int xset = find(parent, x);
     *    int yset = find(parent, y);
     *    if (xset != yset)
     *        parent[xset] = yset;
     * }
     */
    /**
     * Another form of Union Find
     */
    public int findCircleNum_4(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }

        return uf.getCount();
    }

    class UnionFind {
        int root[];
        int rank[];
        int count;

        UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            count = size;
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
                count--;
            }
        };

        int getCount() {
            return count;
        }
    }
}
