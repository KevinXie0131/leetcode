package com.answer.dfs_bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BfsDfsTemplate2 {
    /**
     * 省份数量 DFS - Iteration
     */
    public int findCircleNum_0(int[][] isConnected) { // isConnected是无向图的邻接矩阵，cityNum 为无向图的顶点数量
        int cityNum = isConnected.length;
        Integer count = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[cityNum];

        for(int i = 0; i < cityNum; i++){
            if(!visited[i]){
                stack.push(i);
                visited[i] = true;
                while(!stack.isEmpty()){
                    int j = stack.pop();
                    for(int k = 0; k < cityNum; k++){
                     // if(j != k && isConnected[j][k] == 1 && !visited[k]){ // works too
                        if(isConnected[j][k] == 1 && !visited[k]){
                            stack.push(k);
                            visited[k] = true;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
    /**
     * 省份数量 DFS - Recursion
     */
    public int findCircleNum_1(int[][] isConnected) {
        int cityNum = isConnected.length;
        Integer count = 0;
        boolean[] visited = new boolean[cityNum];

        for(int i = 0; i < cityNum; i++){
            if(!visited[i]){
                visited[i] = true;
                count++;  // 若当前顶点 i 未被访问，说明又是一个新的连通域，则遍历新的连通域且count+=1.
                dfsRecursion(isConnected, visited, i);
            }
        }
        return count;
    }

    public void dfsRecursion(int[][] isConnected, boolean[] visited, int i){
        for(int j = 0; j < isConnected.length; j++){
        //  if(i != j && isConnected[i][j] == 1 && !visited[j]){ // works too
            if(isConnected[i][j] == 1 && !visited[j]){
                visited[j] = true;
                dfsRecursion(isConnected, visited, j);
            }
        }
    }
    /**
     * 省份数量 BFS
     */
    public int findCircleNum_2(int[][] isConnected) {
        int cityNum = isConnected.length;
        Integer count = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[cityNum];

        for(int i = 0; i < cityNum; i++){
            if(!visited[i]){
                deque.offer(i);
                visited[i] = true;
                while(!deque.isEmpty()){
                    int j = deque.poll();
                    for(int k = 0; k < cityNum; k++){
                    //  if(j != k && isConnected[j][k] == 1 && !visited[k]){ // works too
                        if(isConnected[j][k] == 1 && !visited[k]){
                            deque.offer(k);
                            visited[k] = true;
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
}
