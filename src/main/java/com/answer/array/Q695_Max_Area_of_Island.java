package com.answer.array;

public class Q695_Max_Area_of_Island {

    /**
     * Approach #1: Depth-First Search (Recursive)
     */
    static int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

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

}
