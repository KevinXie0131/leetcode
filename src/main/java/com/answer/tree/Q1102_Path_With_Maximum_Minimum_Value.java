package com.answer.tree;

import java.util.PriorityQueue;

public class Q1102_Path_With_Maximum_Minimum_Value {
    /**
     * 得分最高的路径
     * 给你一个 m x n 的整数矩阵 grid ，矩阵中的每一个单元格都有一个对应的值。你可以从左上角单元格 (0, 0) 出发，移动到右下角单元格 (m-1, n-1) 。
     * 每一步，你可以移动到相邻的上、下、左、右四个单元格之一（不能移出边界）。
     *
     * 一条路径的得分定义为：这条路径上所有单元格中的最小值。
     * 请你找出所有从左上角到右下角路径中，得分最高的那条路径，返回其得分。
     * Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
     * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
     * A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).
     */
    public static void main(String[] args) {
       Q1102_Path_With_Maximum_Minimum_Value sol = new Q1102_Path_With_Maximum_Minimum_Value();
       int[][] A = {{5,4,5},
                    {1,2,6},
                    {7,4,6}};
       System.out.println(4 == sol.maximumMinimumPath(A));
       int[][] B = {{2,2,1,2,2,2},
                    {1,2,2,2,1,2}};
       System.out.println(2 == sol.maximumMinimumPath(B));
       int[][] C= { {3,4,6,3,4},
                    {0,2,1,1,7},
                    {8,8,3,2,7},
                    {3,2,4,9,8},
                    {4,1,2,0,0},
                    {4,6,5,4,3}};
       System.out.println(3 == sol.maximumMinimumPath(C));
       int[][] D = {{42}};
       System.out.println(42 == sol.maximumMinimumPath(D));
       int[][] E = {{7,7,7},
                    {7,7,7},
                    {7,7,7}};
       System.out.println(7 == sol.maximumMinimumPath(E));
    }
    /**
     * 优先队列（最大堆）
     * 优先队列保证每次选择当前“得分”最高的走法，直到终点
     *
     * 从左上角出发到右下角，每次只能上下左右移动到相邻的格子。
     * 一条路径的得分定义为路径上所有格子的最小值。
     * 求从左上到右下所有路径中得分最大的那条路径的分数。
     */
    static public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 优先队列（大根堆）：每次扩展得分最高的路径
        // 队列元素为 int[]{当前路径分数, 行, 列}
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] visited = new boolean[m][n];  // 记录每个格子是否访问过，防止重复遍历
        visited[0][0] = true;
        queue.offer(new int[]{grid[0][0], 0, 0});  // 从左上角出发，初始路径分数为 grid[0][0]

        int[][] dirs = {{-1, 0}, {1, 0}, {0 , -1}, {0, 1}};
        while(!queue.isEmpty()){
            int[] point = queue.poll();  // 取出当前路径分数最高的格子
            int curValue = point[0];
            int x = point[1], y = point[2];
            if(x == m - 1 && y == n - 1){  // 到达右下角，直接返回当前路径分数
                return curValue;
            }
            for(int[] dir : dirs){
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx >= 0 && ny >= 0 && nx <= m - 1 && ny <= n - 1 && !visited[nx][ny]){ // 判断新坐标是否在网格内且未访问过
                    visited[nx][ny] = true;
                    int newValue = Math.min(curValue, grid[nx][ny]); // 新路径分数为 min(当前路径分数, 新格子的值)
                    queue.offer(new int[]{newValue, nx, ny});
                }
            }
        }
        return -1;   // 理论上不会到达这里（题目保证有解）
    }
}
