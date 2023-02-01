package com.answer.array;

import java.util.*;

public class Q200_Number_of_Islands {
    public static void main(String[] args) {
        /**
         *  1 1 1 1 0
         *  1 1 0 1 0
         *  1 1 0 0 0
         *  0 0 0 0 0
         */
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        /**
         * 1 1 0 0 0
         * 1 1 0 0 0
         * 0 0 1 0 0
         * 0 0 0 1 1
         */
        char[][] grid1 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(numIslands_1(grid));
        System.out.println(numIslands_1(grid1));
    }
    /**
     * Approach #1 DFS
     */
    static int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public static int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    public static void dfs(char[][] grid, int i, int j){
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{i, j});
        grid[i][j] = '2';
        while(!stack.isEmpty()){
            int[] cur = stack.pop();
            for(int index = 0; index < 4; index++){
                int[] d = dir[index];
                int x = d[0] + cur[0];
                int y = d[1] + cur[1];
                if(isWithin(grid, x, y) && grid[x][y] == '1'){
                    grid[x][y] = '2';
                    stack.push(new int[]{x, y});
                }
            }
        }
    }
    public static boolean isWithin(char[][] grid, int x, int y){
        if(x >= 0 && x <= grid.length - 1 && y >= 0 && y <= grid[0].length - 1){
            return true;
        }
        return false;
    }
    /**
     * Approach #2: BFS
     */
    public static int numIslands_1(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    public static void bfs(char[][] grid, int i, int j){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '2';
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int index = 0; index < 4; index++){
                int[] d = dir[index];
                int x = d[0] + cur[0];
                int y = d[1] + cur[1];
                if(isWithin(grid, x, y) && grid[x][y] == '1'){
                    grid[x][y] = '2';
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }
    /**
     * Approach #3: Union Find (aka Disjoint Set)
     */
}
