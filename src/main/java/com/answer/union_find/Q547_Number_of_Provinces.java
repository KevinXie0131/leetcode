package com.answer.union_find;

import java.util.*;

public class Q547_Number_of_Provinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum_3(isConnected));
    }
    /**
     * Wrong answer
     */
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int findCircleNum(int[][] isConnected) {
        int m = isConnected.length;
        int n = isConnected[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    recrusion(isConnected, i, j, count);
                    count++;
                }
            }
        }
        return count;
    }
    public static void recrusion(int[][] isConnected, int i, int j, int count) {
        if (i < 0 || i > isConnected.length - 1 || j < 0 || j > isConnected[0].length - 1) {
            return;
        }
        if (isConnected[i][j] == 1) {
            isConnected[i][j] = 2;
            for (int[] d : dir) {
                recrusion(isConnected, i + d[0], j + d[1], count);
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
