package com.answer.graph;

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
}
