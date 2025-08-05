package com.answer.tree;

import java.util.*;

public class Q1631_Path_With_Minimum_Effort {
    /**
     * 最小体力消耗路径
     * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     * 请你返回从左上角走到右下角的最小 体力消耗值 。
     * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
     * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     */
    public static void main(String[] args) {
       int[][] heights = {{1,2,2},{3,8,2},{5,3,5}};
        int res = minimumEffortPath(heights);
        System.out.println(res);
    }

    static int[][] dirs = {{-1, 0}, {1, 0}, {0 , -1}, {0, 1}};
    static boolean visited[][];
    static int m, n;
    /**
     * Time Limit Exceeded
     */
    static public int minimumEffortPath(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, heights[i][j]);
                min = Math.min(min, heights[i][j]);
            }
        }
        for(int k = 0; k <= max - min; k++){
            visited = new boolean[m][n];
            if(dfs(heights, 0, 0, k)){
                return k;
            }
        }
        return -1;
    }
    /**
     * 体力消耗的最小值可以用二分查找来尝试
     */
    static public int minimumEffortPath1(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, heights[i][j]);
                min = Math.min(min, heights[i][j]);
            }
        }
        int res = -1;
        int left = 0, right = max - min;
        while(left <= right){
            visited = new boolean[m][n];
            int mid = (left + right) >>> 1;
            if(dfs(heights, 0, 0, mid)){
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    /**
     * 1 <= heights[i][j] <= 10^6
     */
    static public int minimumEffortPath1a(int[][] heights) {
        m = heights.length;
        n = heights[0].length;

        int res = -1;
        int left = 0, right = 1000000;
        while(left <= right){
            visited = new boolean[m][n];
            int mid = (left + right) >>> 1;
            if(dfs(heights, 0, 0, mid)){
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    /**
     * 对每个尝试值 K，DFS 检查从左上能否到达右下，每步只允许高度差 <= limit
     */
    static public boolean dfs(int[][] heights, int x, int y, int limit){
        if(x == m - 1 && y == n - 1){
            return true;
        }
        visited[x][y] = true;
        for(int[] dir : dirs){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(newX >= 0 && newY >= 0 && newX <= m - 1 && newY <= n - 1 && !visited[newX][newY]){
                if(Math.abs(heights[newX][newY] - heights[x][y]) <= limit){
                    if(dfs(heights, newX, newY, limit)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * 用 BFS 检查
     */
    static public boolean bfs(int[][] heights, int limit){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            if(point[0] == m - 1 && point[1] == n - 1){
                return true;
            }
            for(int[] dir : dirs){
                int newX = point[0] + dir[0];
                int newY = point[1] + dir[1];
                if(newX >= 0 && newY >= 0 && newX <= m - 1 && newY <= n - 1 && !visited[newX][newY]){
                    if(Math.abs(heights[newX][newY] - heights[point[0]][point[1]]) <= limit){
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
        return false;
    }
    /**
     * Dijkstra 算法求解，effort[x][y] 表示到点 (x, y) 的最小体力消耗
     * 维护一个最小堆，每次扩展体力消耗最小的点。
     */
    public int minimumEffortPath3(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] effort = new int[m][n]; // effort[x][y] 表示到达 (x, y) 点的最小体力消耗（路径上最大高度差的最小值）
        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        effort[0][0] = 0; // 起点体力消耗为 0

        // 小根堆，存储 [体力消耗, x, y]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0, 0});

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}}; // 四个方向移动数组

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currEffort = curr[0], x = curr[1], y = curr[2];
            if (x == m - 1 && y == n - 1) { // 如果到达终点，直接返回当前的最小体力消耗
                return currEffort;
            }
            for (int[] dir : dirs) {  // 遍历四个方向
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {     // 判断新坐标是否在网格内
                    // 计算到达下一个点的体力值（取当前体力值和高度差的最大值）
                    int nextEffort = Math.max(currEffort, Math.abs(heights[nx][ny] - heights[x][y]));  // 计算从当前点到新点的体力消耗
                    // 如果找到了一条到达下一个点的更小体力值的路径
                    if (nextEffort < effort[nx][ny]) {// 如果到新点的体力消耗更小，则更新，并加入堆
                        effort[nx][ny] = nextEffort;
                        pq.offer(new int[]{nextEffort, nx, ny});
                    }
                }
            }
        }
        return 0;  // 理论上不会到这一步，题目保证有解
    }
}
