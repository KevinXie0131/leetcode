package com.answer.dfs_bfs;

import java.util.*;

public class Q827_Making_A_Large_Island { // Hard 困难
    /**
     * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
     * Return the size of the largest island in grid after applying this operation.
     * An island is a 4-directionally connected group of 1s.
     * 最大人工岛
     * 一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。返回执行此操作后，grid 中最大的岛屿面积是多少？
     * 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
     *
     * 输入: grid = [ [1, 0],
     *               [0, 1]]
     * 输出: 3
     * 解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
     */
    /**
     * 深搜优化思路
     * 只要用一次深搜把每个岛屿的面积记录下来就好。
     * 第一步：一次遍历地图，得出各个岛屿的面积，并做编号记录。可以使用map记录，key为岛屿编号，value为岛屿面积
     * 第二步：再遍历地图，遍历0的方格（因为要将0变成1），并统计该1（由0变成的1）周边岛屿面积，将其相邻面积相加在一起，遍历所有 0 之后，就可以得出 选一个0变成1 之后的最大面积。
     * 整个解法的时间复杂度，为 n * n + n * n 也就是 n^2
     */
    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 初始化mark变量，从2开始（区别于0水，1岛屿）
        mark = 2; // 记录每个岛屿的编号
        // 定义二位boolean数组记录该位置是否被访问
        boolean[][] visited = new boolean[m][n];
        // 定义一个HashMap，记录某片岛屿的标记号和面积
        HashMap<Integer, Integer> getSize = new HashMap<>();
        // 定义一个HashSet，用来判断某一位置水四周是否存在不同标记编号的岛屿
        HashSet<Integer> set = new HashSet<>();
        // 定义一个boolean变量，看看DFS之后，是否全是岛屿
        boolean isAllIsland = true;  // 标记是否整个地图都是陆地
        // 遍历二维数组进行DFS搜索，标记每片岛屿的编号，记录对应的面积
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    isAllIsland = false;
                }
                if (grid[i][j] == 1) {
                    count = 0;
                    dfs_visited(grid, i, j, visited);
               //     dfs_without_visited(grid, i, j);
                    getSize.put(mark, count);  // 记录每一个岛屿的面积
                    mark++; // 记录下一个岛屿编号
                }
            }
        }
        int result = 0;
        if (isAllIsland) {
            result =  m * n;
        }
        // 对标记完的grid继续遍历，判断每个水位置四周是否有岛屿，并记录下四周不同相邻岛屿面积之和
        // 每次计算完一个水位置周围可能存在的岛屿面积之和，更新下result变量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    set.clear(); // 每次使用时，清空
                    // 当前水位置变更为岛屿，所以初始化为1
                    int curSize = 1;

                    for (int[] dir : dirs) {
                        int curRow = i + dir[0];
                        int curCol = j + dir[1];

                        if (curRow < 0 || curRow >= m || curCol < 0 || curCol >= n){
                            continue;
                        }
                        int curMark = grid[curRow][curCol];
                        // 如果当前相邻的岛屿已经遍历过或者HashMap中不存在这个编号，继续搜索
                        if (set.contains(curMark) || !getSize.containsKey(curMark)) { // 添加过的岛屿不要重复添加
                            continue;
                        }
                        set.add(curMark);// 标记该岛屿已经添加过
                        curSize += getSize.get(curMark);  // 把相邻四面的岛屿数量加起来
                    }
                    result = Math.max(result, curSize);
                }
            }
        }
        return result;
    }
    // 该方法采用 DFS
    // 定义全局变量
    // 记录每次每个岛屿的面积
    int count;
    // 对每个岛屿进行标记
    int mark;
    // 定义二维数组表示四个方位
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // DFS 进行搜索，将每个岛屿标记为不同的数字
    public void dfs_visited(int[][] grid, int x, int y, boolean[][] visited) {
        // 当遇到边界，直接return
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }
        // 遇到已经访问过的或者遇到海水，直接返回
        if (visited[x][y] || grid[x][y] == 0){
            return;
        }
        visited[x][y] = true;
        count++;
        grid[x][y] = mark;
        // 继续向下层搜索
        dfs_visited(grid, x, y + 1, visited);
        dfs_visited(grid, x, y - 1, visited);
        dfs_visited(grid, x + 1, y, visited);
        dfs_visited(grid, x - 1, y, visited);
    }
    /**
     * 当然这里还有一个优化的点，就是 可以不用 visited数组，因为有mark来标记，所以遍历过的grid[i][j]是不等于1的。
     */
    public void dfs_without_visited(int[][] grid, int x, int y) {
        if (grid[x][y] != 1 || grid[x][y] == 0) {
            return; // 终止条件：访问过的节点 或者 遇到海水
        }
        grid[x][y] = mark; // 给陆地标记新标签
        count++;
        for (int i = 0; i < 4; i++) {
            int nextx = x + dirs[i][0];
            int nexty = y + dirs[i][1];
            if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) {
                continue;  // 越界了，直接跳过
            }
            dfs_without_visited(grid, nextx, nexty);
        }
    }
}
