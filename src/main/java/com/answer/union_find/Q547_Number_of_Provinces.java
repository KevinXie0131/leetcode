package com.answer.union_find;

import java.util.*;

public class Q547_Number_of_Provinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum_0a(isConnected));
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
     * Approach #1 Using Depth First Search - Reursion
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
}
