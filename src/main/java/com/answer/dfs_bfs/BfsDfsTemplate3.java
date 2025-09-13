package com.answer.dfs_bfs;

import java.util.*;

public class BfsDfsTemplate3 {
    /**
     * 最小体力消耗路径 DFS recursion
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0 , -1}, {0, 1}};
    boolean visited[][];
    int m, n;

    public boolean dfs(int[][] heights, int x, int y, int limit){
        if(x == m - 1 && y == n - 1){
            return true;
        }
        visited[x][y] = true;

        for(int[] dir : dirs){
            int newX = x + dir[0];
            int newY = y + dir[1];

            if(newX >= 0 && newY >= 0 && newX <= m - 1 && newY <= n - 1 && !visited[newX][newY]){
                if(Math.abs(heights[newX][newY] - heights[x][y]) <= limit){
                    if(dfs(heights, newX, newY, limit)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * DFS iterative
     */
    public boolean dfs(int[][] heights, int limit){
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});
        visited[0][0] = true;

        while(!stack.isEmpty()){
            int[] point = stack.pop();
            if(point[0] == m - 1 && point[1] == n - 1){
                return true;
            }
            for(int[] dir : dirs){
                int newX = point[0] + dir[0];
                int newY = point[1] + dir[1];

                if(newX >= 0 && newY >= 0 && newX <= m - 1 && newY <= n - 1 && !visited[newX][newY]){
                    if(Math.abs(heights[newX][newY] - heights[point[0]][point[1]]) <= limit){
                        visited[newX][newY] = true;
                        stack.push(new int[]{newX, newY});
                    }
                }
            }
        }
        return false;
    }
    /**
     * BFS iterative
     */
    public boolean bfs(int[][] heights, int limit){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] point = queue.poll();
            if(point[0] == m - 1 && point[1] == n - 1){
                return true;
            }
            for(int[] dir : dirs){
                int newX = point[0] + dir[0];
                int newY = point[1] + dir[1];

                if(newX >= 0 && newY >= 0 && newX <= m - 1 && newY <= n - 1 && !visited[newX][newY]){
                    if(Math.abs(heights[newX][newY] - heights[point[0]][point[1]]) <= limit){
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
        return false;
    }
}
