package com.answer.graph;

import java.util.*;

public class Q1034_Coloring_A_Border {
    /**
     * You are given an m x n integer matrix grid, and three integers row, col, and color. Each value in the grid represents the color of the grid square at that location.
     * Two squares are called adjacent if they are next to each other in any of the 4 directions.
     * Two squares belong to the same connected component if they have the same color and they are adjacent.
     * The border of a connected component is all the squares in the connected component that are either adjacent to (at least) a square not in the component, or on the boundary of the grid (the first or last row or column).
     * You should color the border of the connected component that contains the square grid[row][col] with color.
     * Return the final grid.
     * 边界着色
     * 一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
     * 如果两个方块在任意 4 个方向上相邻，则称它们 相邻 。
     * 如果两个方块具有相同的颜色且相邻，它们则属于同一个 连通分量 。
     * 连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
     *  在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
     *  在网格的边界上（第一行/列或最后一行/列）
     * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色。
     * 并返回最终的网格 grid 。
     *
     * 示例 1：
     *  输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
     *  输出：[[3,3],[3,2]]
     * 示例 2：
     *  输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
     *  输出：[[1,3,3],[2,3,3]]
     * 示例 3：
     *  输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
     *  输出：[[2,2,2],[2,1,2],[2,2,2]]
     */
    public static void main(String[] args) {
        int[][] grid = {{1,1,1},{1,1,1},{1,1,1}};
        int row = 1, col = 1, color = 2;
        System.out.println(Arrays.deepToString(colorBorder(grid, row, col, color)));
    }
    /**
     * DFS
     * 用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回grid，
     * 连通分量的边界其实就是连通分量最外围那一圈挨着边界或者其他连通分量的网格
     */
    static public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] used=new boolean[m][n];
        dfs(row, col, color, grid[row][col], grid, used);
        return grid;
    }
    // 如何判断连通变量的边界？
    // 处于连通分量边界的网格，它的上下左右相邻网格一定至少存在一个是边界或非同一连通分量的网格，
    static  public boolean dfs(int i, int j, int color, int value, int[][] grid, boolean[][] used) {
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1){//1.处理边界
            return true;
        }
        if(used[i][j]){  //2.处理已经走过的网格
            return false;
        }
        if(grid[i][j] != value){//3.处理不属于同一连通分量的网格
            return true;
        }
        used[i][j] = true; //标记网格
        boolean b1 = dfs(i - 1, j, color, value, grid, used);
        boolean b2 = dfs(i + 1, j, color, value, grid, used);
        boolean b3 = dfs(i, j - 1, color, value, grid, used);
        boolean b4 = dfs(i, j + 1, color, value, grid, used);
        if(b1 || b2 || b3 || b4){ // 那么递到边界和非同一连通分量的网格就返回true；递到同一连通分量的网格就返回false；
            grid[i][j] = color;
        }
        return false;
    }
    /**
     * BFS
     */
    public int[][] colorBorder1(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;// 获取网格的尺寸
        int[][] visited = new int[m][n];  // 创建一个与原网格相同大小的答案数组，初始值为0
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};   // 定义四个方向的移动（下、上、右、左）

        Deque<int[]> d = new ArrayDeque<>();// 使用双端队列进行广度优先搜索（BFS），并添加起始坐标
        d.addLast(new int[]{row, col});

        while (!d.isEmpty()) {
            int[] poll = d.pollFirst(); // 从队列头部取出元素
            int x = poll[0], y = poll[1], cnt = 0;
            // 遍历四个方向
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {// 如果新的位置在网格外，跳过
                    continue;
                }
                if (grid[x][y] != grid[nx][ny]) { // 如果相邻格子的颜色与当前格子不同，说明是边界，不增加计数器
                    continue;
                } else {
                    cnt++; // 否则，计数器加一
                }
                if (visited[nx][ny] != 0) { // 如果该位置已经处理过，则跳过
                    continue;
                }
                d.addLast(new int[]{nx, ny}); // 将相邻且未访问过的同色格子加入队列
            }
            // 如果cnt == 4，意味着所有相邻格子都是同色的，不是边界，保留原色；否则着新颜色
            visited[x][y] = cnt == 4 ? grid[x][y] : color;
        }
        // 对于没有被处理到的位置，复制原网格的颜色
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    visited[i][j] = grid[i][j];
                }
            }
        }
        return visited;
    }
    /**
     * DFS
     */

    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};

    public int[][] colorBorder3(int[][] grid, int row, int col, int color) {
        int m = grid.length; int n = grid[0].length;
        int[][] visited = new int[m][n];
        dfs1(row, col, m, n, color, grid, visited);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    visited[i][j] = grid[i][j];
                }
            }
        }
        return visited;
    }

    void dfs1(int x, int y, int m, int n, int color, int[][] grid, int[][] visited) {
        int cnt = 0;
        for (int[] di : dirs) {
            int nx = x + di[0], ny = y + di[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                continue;
            }
            if (grid[x][y] != grid[nx][ny]) {
                continue;
            } else{
                cnt++;
            }
            if (visited[nx][ny] != 0){
                continue;
            }
            visited[nx][ny] = 1;  //标记

            dfs1(nx, ny, m, n, color, grid, visited);
        }
        visited[x][y] = cnt == 4 ? grid[x][y] : color;
    }
}
