package com.answer.dfs_bfs;

public class Q463_Island_Perimeter {
    /**
     * DFS
     */
    public int islandPerimeter(int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    return dfs(grid, i, j);
                }
            }
        }

        return 0;
    }
    int dfs(int[][] grid, int r, int c) {
        if(!inArea(grid, r, c)){
            return 1;
        }
        if(grid[r][c] == 0){
            return 1;
        }
        if(grid[r][c] == 2){
            return 0;
        }
        grid[r][c] =2;
        return dfs(grid, r-1, c) + dfs(grid, r+1, c)
                + dfs(grid, r, c-1) + dfs(grid, r, c+1);

    }
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }
    /**
     * Brute force
     */
    public int islandPerimeter_1(int[][] grid) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }
}
