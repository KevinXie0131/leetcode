package com.answer.dfs_bfs;

import java.util.*;

public class Q695_Max_Area_of_Island {
    /**
     * 深度优先搜索
     */
    final int[][] dir={{0,1},{1,0},{0,-1},{-1,0}}; // 四个方向

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
                if (nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length || visited[nextX][nextY] || grid[nextX][nextY] == 0){
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
        grid[i][j] = 2;
        return 1 + dfs(grid, i - 1, j)
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
     */
    public int maxAreaOfIsland_1(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int ans = 0;
        for (int r0 = 0; r0 < grid.length; r0++) {
            for (int c0 = 0; c0 < grid[0].length; c0++) {
                if (grid[r0][c0] == 1 && !seen[r0][c0]) {
                    int shape = 0;
                    Stack<int[]> stack = new Stack();
                    stack.push(new int[]{r0, c0});
                    seen[r0][c0] = true;
                    while (!stack.empty()) {
                        int[] node = stack.pop();
                        int r = node[0], c = node[1];
                        shape++;
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < grid.length &&
                                    0 <= nc && nc < grid[0].length &&
                                    grid[nr][nc] == 1 && !seen[nr][nc]) {
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                    ans = Math.max(ans, shape);
                }
            }
        }
        return ans;
    }
}
