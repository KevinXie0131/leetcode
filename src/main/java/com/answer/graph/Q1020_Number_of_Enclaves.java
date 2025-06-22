package com.answer.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Q1020_Number_of_Enclaves {
    /**
     * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
     * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
     * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
     * 飞地的数量
     * 一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
     * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
     * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
     * grid[i][j] 的值为 0 或 1
     *
     * 示例 1：
     *  输入：grid = [[0,0,0,0],
     *               [1,0,1,0],
     *               [0,1,1,0],
     *               [0,0,0,0]]
     *  输出：3
     *  解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
     *       There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
     * 示例 2：
     *  输入：grid = [[0,1,1,0],
     *               [0,0,1,0],
     *               [0,0,1,0],
     *               [0,0,0,0]]
     *  输出：0
     *  解释：所有 1 都在边界上或可以到达边界。
     *       All 1s are either on the boundary or can reach the boundary.
     */
    public static void main(String[] args) {
        int[][] grid = {{0,1,1,0},
                        {0,0,1,0},
                        {0,0,1,0},
                        {0,0,0,0}};
        System.out.println(numEnclaves3(grid));
    }
    /**
     * DFS
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1 ) dfs(i, 0, grid);
            if (grid[i][n - 1] == 1) dfs(i, n - 1, grid);
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1 ) dfs(0, j, grid);
            if (grid[m - 1][j] == 1) dfs(m - 1, j, grid);
        }
        int count = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(int i, int j, int[][] grid) {
        if( i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == 0){
            return;
        }

        grid[i][j] = 0;// 标记 (x,y) 被访问，避免重复访问
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
        dfs(i, j - 1, grid);
        dfs(i, j + 1, grid);
    }
    /**
     * 深度优先搜索
     */
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numEnclaves1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Deque<int[]> stack = new ArrayDeque<int[]>();
                    grid[i][j] = 0;
                    boolean closed = true;
                    int count = 1;

                    stack.push(new int[]{i, j});
                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int cx = cur[0], cy = cur[1];
                        if (cx == 0 || cy == 0 || cx == m - 1 || cy == n - 1) {
                            closed = false;
                        }
                        for (int d = 0; d < 4; d++) {
                            int nx = cx + dir[d][0];
                            int ny = cy + dir[d][1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                                grid[nx][ny] = 0;
                                stack.push(new int[]{nx, ny});
                                count++;
                            }
                        }
                    }
                    if (closed) {
                        result += count;
                    }
                }
            }
        }
        return result;
    }
    /**
     * 广度优先搜索
     */
    public int numEnclaves2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> qu = new ArrayDeque<int[]>();
                    grid[i][j] = 0;
                    boolean closed = true;
                    int count = 1;

                    qu.offer(new int[]{i, j});
                    while (!qu.isEmpty()) {
                        int[] arr = qu.poll();
                        int cx = arr[0], cy = arr[1];
                        if (cx == 0 || cy == 0 || cx == m - 1 || cy == n - 1) {
                            closed = false;
                        }
                        for (int d = 0; d < 4; d++) {
                            int nx = cx + dir[d][0];
                            int ny = cy + dir[d][1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1) {
                                grid[nx][ny] = 0;
                                qu.offer(new int[]{nx, ny});
                                count++;
                            }
                        }
                    }
                    if (closed) {
                        ans += count;
                    }
                }
            }
        }
        return ans;
    }
    /**
     * 并查集
     */
    static int[] connected;

    static public int numEnclaves3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        connected = new int[m * n + 1];
        for(int i = 0; i < m * n + 1; i++) {
            connected[i] = i;
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    union(connected, i * n + j, m * n);
                } else if (grid[i][j] == 0) {
                    union(connected, i * n + j, m * n);
                } else  if (grid[i][j] == 1) {
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        union(connected, i * n + j, (i - 1) * n + j);
                    }
                    if (i + 1 < m && grid[i + 1][j] == 1) {
                        union(connected, i * n + j, (i + 1) * n + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        union(connected, i * n + j, i * n + j - 1);
                    }
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        union(connected, i * n + j, i * n + j + 1);
                    }
                }
            }
        }

        for(int i = 0; i < m * n + 1; i++) {
            connected[i] = find(connected, i);
        }

        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                if (grid[i][j] == 1 && connected[i * n + j] != connected[m * n]) {
                    ++ans;
                }
            }
        }
        return ans;
    }

   static public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    static public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
