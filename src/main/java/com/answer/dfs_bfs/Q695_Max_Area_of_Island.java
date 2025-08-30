package com.answer.dfs_bfs;

import java.util.*;

public class Q695_Max_Area_of_Island {
    /**
     * 岛屿的最大面积
     * 一个大小为 m x n 的二进制矩阵 grid 。
     * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上(4-directionally) 相邻。你可以假设(assume) grid 的四个边缘都被 0（代表水）包围着。
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     *
     * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *              [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *              [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *              [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *              [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *              [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * 输出：6
     * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
     */
    /**
     * 深度优先搜索
     */
    final int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // 四个方向

    public int maxAreaOfIsland_0(int[][] grid) {
        int result = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && grid[i][j] == 1){  // 没有访问过的 同时 是陆地的
                    int max = dfs_visited(grid, visited, i, j);
                    result = Math.max(max, result);
                }
            }
        }
        return result;
    }

    int dfs_visited(int[][] grid, boolean[][] visited, int x, int y){
        int count = 0;
        visited[x][y] = true;  // 将与其链接的陆地都标记上 true
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            //水或者已经访问过的跳过
            if(nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length // 越界了，直接跳过
                    || visited[nextX][nextY] || grid[nextX][nextY] == 0){                // 水或者已经访问过的跳过
                continue;
            }
            count += dfs_visited(grid, visited, nextX, nextY);
        }
        return count + 1;
    }
    /**
     * 广度优先搜索
     */
    public int maxAreaOfIsland_2(int[][] grid) {
        int result = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {  // 节点没有被访问过且是陆地
                    int max = bfs_visited(grid, visited, i, j);
                    result = Math.max(max, result);
                }
            }
        }
        return result;
    }

    int bfs_visited(int[][] grid, boolean[][] visited, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});  // 加入队列就意味节点是陆地可到达的点
        visited[x][y] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = node[0] + dir[i][0];
                int nextY = node[1] + dir[i][1];
                if (nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length
                        || visited[nextX][nextY] || grid[nextX][nextY] == 0){
                    continue;  // 越界
                }
                queue.offer(new int[]{nextX, nextY});
                visited[nextX][nextY] = true; // 将与其链接的陆地都标记上 true
                count++;
            }
        }
        return count;
    }
    /**
     * Approach #1: Depth-First Search (Recursive)
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    //   dfs(grid, i, j);
                    int res = dfs(grid, i, j);
                    max = Math.max(max, res);
                }
            }
        }
        return max;
    }

    public static int dfs(int[][] grid, int i, int j){
        if(!isWithin(grid, i, j)){
            return 0;
        }
        if(grid[i][j] == 0 || grid[i][j] == 2){
            return 0;
        }
        grid[i][j] = 2;  // 访问过的设置为2，下次不再访问
        return 1 + dfs(grid, i - 1, j) // 向四个方向发散求和
                 + dfs(grid, i + 1, j)
                 + dfs(grid, i, j - 1)
                 + dfs(grid, i, j + 1);
    }

    public static boolean isWithin(int[][] grid, int x, int y){
        if(x >= 0 && x <= grid.length - 1 && y >= 0 && y <= grid[0].length - 1){
            return true;
        }
        return false;
    }
    /**
     * Approach #2: Depth-First Search (Iterative)
     * 深度优先搜索 + 栈
     */
    public int maxAreaOfIsland_1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int max = 0;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    int shape = 0;
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{row, col});
                    visited[row][col] = true;

                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        shape++;
                        for (int d[] : dir) {
                            int newRow = cur[0] + d[0];
                            int newCol = cur[1] + d[1];
                            if (0 <= newRow && newRow < grid.length && 0 <= newCol && newCol < grid[0].length
                                    && grid[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                                stack.push(new int[]{newRow, newCol});
                                visited[newRow][newCol] = true;
                            }
                        }
                    }
                    max = Math.max(max, shape);
                }
            }
        }
        return max;
    }
}
