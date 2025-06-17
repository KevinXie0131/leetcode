package com.answer.dfs_bfs;

import java.util.*;

public class Q994_Rotting_Oranges {
    /**
     * 腐烂的橘子
     * You are given an m x n grid where each cell can have one of three values:
     *  0 representing an empty cell,
     *  1 representing a fresh orange, or
     *  2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
     * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
     *  值 0 代表空单元格；
     *  值 1 代表新鲜橘子；
     *  值 2 代表腐烂的橘子。
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
     * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     *
     * 示例 1：
     *  输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
     *  输出：4
     * 示例 2：
     *  输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
     *  输出：-1
     *  解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个方向上。
     */
    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
      //  int[][] grid = {{0, 2, 2}};
        System.out.println(orangesRotting(grid));
    }
    /**
     * Approach 1: Breadth-First Search (BFS)
     *
     * int ROWS = grid.length;
     * int COLS = grid[0].length;
     * for (int r = 0; r < ROWS; ++r)
     *    for (int c = 0; c < COLS; ++c)
     *       if (grid[r][c] == 2)
     *
     */
    static int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public static int orangesRotting(int[][] grid) {
        int num = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean zeroFound = false;
        boolean oneFound = false;

        Deque<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2){
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 0) {
                    zeroFound = true;
                } else if (grid[i][j] == 1) {
                    oneFound = true;
                }
            }
        }
        if(queue.size() == 0){
            if(oneFound){
                return -1;
            }else if(zeroFound){
                return 0;
            }
        }

        while(!queue.isEmpty()) {
            int len = queue.size();
            num++;
            boolean hasFound = false;
            for (int index = 0; index < len; index++) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int x = cur[0] + dir[d][0];
                    int y = cur[1] + dir[d][1];
                    if (x >= 0 && x <= n - 1 && y >= 0 && y <= m - 1) {
                        if (grid[x][y] == 1) {
                            hasFound = true;
                            grid[x][y] = 0;
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
            if (!hasFound) {
                num--;
            }
        }
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return num;
    }
}
