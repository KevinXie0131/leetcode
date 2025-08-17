package com.answer.dfs_bfs;

import java.util.*;

public class BfsDfsTemplate {
    /**
     * 如果不需要确定当前遍历到了哪一层，BFS 模板如下。
     * while queue 不空：
     *     cur = queue.pop()
     *     for 节点 in cur的所有相邻节点：
     *         if 该节点有效且未访问过：
     *             queue.push(该节点)
     *
     * 如果要确定当前遍历到了哪一层，BFS 模板如下。
     * level = 0
     * while queue 不空：
     *     size = queue.size()
     *     while (size --) {
     *         cur = queue.pop()
     *         for 节点 in cur的所有相邻节点：
     *             if 该节点有效且未被访问过：
     *                 queue.push(该节点)
     *     }
     *     level ++;
     */
    /**
     * BFS
     */
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}}; // Directions for up, down, left, right
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0], col = point[1];
            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];
                if (r < 0 || r >= rooms.length || c < 0 || c >=  rooms[0].length || rooms[r][c] != Integer.MAX_VALUE){
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                queue.offer(new int[]{r, c});
            }
        }
    }
    /**
     * use level in BFS
     */
    public void wallsAndGates0(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

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
                    if (r < 0 || r >= rooms.length || c < 0 || c >=  rooms[0].length || rooms[r][c] != Integer.MAX_VALUE){
                        continue;
                    }
                    rooms[r][c] = level + 1;
                    queue.offer(new int[]{r, c});
                }
            }
            level++;
        }
    }
    /**
     * DFS
     */
     public void wallsAndGates1(int[][] rooms) {
        dfs(rooms, 0, 0, 0);

    }

    void dfs(int[][] rooms, int i, int j, int step) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < step) {
            return;
        }
        rooms[i][j] = step;

        dfs(rooms, i + 1, j, step + 1);
        dfs(rooms, i - 1, j, step + 1);
        dfs(rooms, i, j + 1, step + 1);
        dfs(rooms, i, j - 1, step + 1);
    }
    /**
     * DFS iterative
     */
    public void wallsAndGates2(int[][] rooms) {
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[]{0, 0});

        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        while (!stack.isEmpty()) {
            int[] point = stack.pop();
            int row = point[0], col = point[1];
            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];
                if (r < 0 || r >= rooms.length || c < 0 || c >=  rooms[0].length || rooms[r][c] == Integer.MAX_VALUE || rooms[row][col] >= rooms[r][c]){
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                stack.push(new int[]{r, c});
            }
        }
    }
}
