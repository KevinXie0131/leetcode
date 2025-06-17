package com.answer.graph;

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
     *  输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
     *  输出：3
     *  解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
     *       There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
     * 示例 2：
     *  输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
     *  输出：0
     *  解释：所有 1 都在边界上或可以到达边界。
     *       All 1s are either on the boundary or can reach the boundary.
     */
}
