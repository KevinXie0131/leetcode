package com.answer.graph;

public class Q1254_Number_of_Closed_Islands {
    /**
     * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s
     * and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
     * Return the number of closed islands.
     * 统计封闭岛屿的数目
     * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
     * 请返回 封闭岛屿 的数目。
     *
     * 示例 1：
     *  输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     *  输出：2
     *  解释：灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
     */
    public static void main(String[] args) {
     //   int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        /*   int[][] grid = {{0,1,1,1,0},
                        {1,0,1,0,1},
                        {1,0,1,0,1},
                        {1,0,0,0,1},
                        {0,1,1,1,0}};*/
        int[][]  grid = {{0,0,1,0,0},
                         {0,1,0,1,0},
                         {0,1,1,1,0}};
        int res = closedIsland( grid);
        System.out.println(res);
    }

    static public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int count = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (!visited[i][j] && grid[i][j] == 0) {
                    boolean isIsland = dfs(i, j, grid, visited);
                    if(isIsland) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }
   static int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    static public boolean dfs(int i, int j, int[][] grid, boolean[][] visited) {
        visited[i][j] = true;
        if((i == 0 || i == grid.length - 1 || j == 0 || j == grid[0].length - 1) && grid[i][j] == 0){
            return false;
        }

        for(int k = 0; k < 4; k++){
            int x = i + dirs[k][0];
            int y = j + dirs[k][1];
            if(x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1){
                continue;
            }
            if((x == 0 || x == grid.length - 1 || y == 0 || y == grid[0].length - 1) && grid[x][y] == 0){
                return false;
            }
            if (grid[x][y] == 0 && !visited[x][y]) {
                dfs(x, y, grid, visited);
            }
        }
        return true;
    }
}
