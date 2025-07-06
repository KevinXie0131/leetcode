package com.answer.stack;

import java.util.*;

public class Q286_Walls_and_Gates {
    /**
     * 墙与门
     * 给定一个 m x n 的二维网格 rooms，初始化如下：
     *  -1 代表墙或障碍物
     *  0 代表一扇门
     *  INF 代表一个空的房间。我们用 2^31 - 1 = 2147483647 代表 INF（即房间里没有门，也没有墙，是一个空房间）
     * 请你填充每个空房间到最近门的距离。如果无法到达门，则填充为 INF。
     *
     * You are given a m x n 2D grid initialized with these three possible values.
     *  -1 - A wall or an obstacle.
     *  0 - A gate.
     *  INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     *
     * Example:
     * Given the 2D grid:
     * INF  -1  0  INF
     * INF INF INF  -1
     * INF  -1 INF  -1
     *   0  -1 INF INF
     * After running your function, the 2D grid should be:
     *   3  -1   0   1
     *   2   2   1  -1
     *   1  -1   2  -1
     *   0  -1   3   4
     */
    public static void main(String[] args) {
        int INF = 2147483647;
        int[][] rooms = {{INF, -1, 0, INF},
                         {INF, INF, INF, -1},
                         {INF, -1, INF, -1},
                         {0, -1, INF, INF}};

        wallsAndGates1(rooms);

        for (int[] row : rooms) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
    // Define constant for INF (empty room)
    private static final int INF = 2147483647;
    /**
     * 多源 BFS
     * 找出所有为0的位置/门的位置，放入队列中
     * 从为0的位置开始向四个方向遍历，直接修改迷宫的可以走的位置的数字是距离门最近的距离
     * 把新的可以走的位置放入队列中，继续搜索
     */
    static public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // Add all gates to the queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        // Directions for up, down, left, right
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0], col = point[1];
            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];
                // If out of bounds or not an empty room, skip
                if (r < 0 || r >= m || c < 0 || c >= n || rooms[r][c] != INF) continue; // 墙（-1）和门（0）都不会被更新。
                // Update distance and add to queue
                // 每个空房间到最近门的距离
                rooms[r][c] = rooms[row][col] + 1;// 每次弹出一个房间，把它四周的空房间距离更新为当前距离 + 1，并入队。
                queue.offer(new int[]{r, c});
            }
        }
    }
    /**
     * use level in BFS
     */
    static public void wallsAndGates0(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;

        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // Add all gates to the queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        // Directions for up, down, left, right
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] point = queue.poll();
                int row = point[0], col = point[1];
                for (int[] dir : dirs) {
                    int r = row + dir[0];
                    int c = col + dir[1];
                    // If out of bounds or not an empty room, skip
                    if (r < 0 || r >= m || c < 0 || c >= n || rooms[r][c] != INF) continue; // 墙（-1）和门（0）都不会被更新。
                    // Update distance and add to queue
                    // 每个空房间到最近门的距离
                    rooms[r][c] = level + 1;// 每次弹出一个房间，把它四周的空房间距离更新为当前距离 + 1，并入队。
                    queue.offer(new int[]{r, c});
                }
            }
            level++;
        }
    }
    /**
     * DFS
     */
    static public void wallsAndGates1(int[][] rooms) {
        for (int i = 0; i < rooms.length; ++i) {
            for (int j = 0; j < rooms[i].length; ++j) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    static void dfs(int[][] rooms, int i, int j, int step) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < step) {
            return;
        }

        rooms[i][j] = step;

        dfs(rooms, i + 1, j, step + 1);
        dfs(rooms, i - 1, j, step + 1);
        dfs(rooms, i, j + 1, step + 1);
        dfs(rooms, i, j - 1, step + 1);
    }
}
