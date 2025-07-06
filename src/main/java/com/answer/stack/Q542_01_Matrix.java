package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Q542_01_Matrix {
    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     * The distance between two cells sharing a common edge is 1.
     * 01 矩阵
     * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
     * 两个相邻元素间的距离为 1 。
     *
     * 示例 2：
     *   输入：mat = [[0,0,0],
     *                [0,1,0],
     *                [1,1,1]]
     *   输出：[[0,0,0],
     *         [0,1,0],
     *         [1,2,1]]
     */
    public static void main(String[] args) {
        int[][] mat = {{0,0,0},
                       {0,1,0},
                       {1,1,1}};
        System.out.println(Arrays.deepToString(updateMatrix3(mat)));
    }
    /**
     * 广度优先搜索
     * 广度优先搜索可以找到从起点到其余所有点的 最短距离
     */
    public int[][] updateMatrix(int[][] mat) { // 找出每个 1 到最近 0 的距离 -> 多源 BFS, 首先需要把多个源点都入队
        int m = mat.length;
        int n = mat[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j] == 0){ // “多源”，多个起点，多个0
                    queue.offer(new int[]{i, j});  // 首先将所有的 0 都入队
                } else {// 将 1 的位置设置成 MIN_VALUE，表示该位置是 未被访问过的
                    mat[i][j] = Integer.MIN_VALUE;  //标记非零元素为负，和遍历后设定的正数距离加以区分
                }
            }
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 1;
        while(!queue.isEmpty()){
            int size = queue.size();     //对当前队列中所有零元素遍历，所有元素向四周走一步
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();   //获取队列中的元素位置
                for(int k = 0; k < 4; k++){
                    int x = cur[0] + dirs[k][0];   //向四个方向依次走一步
                    int y = cur[1] + dirs[k][1];
                    if(x < 0 || x >= m || y < 0 || y >= n || mat[x][y] != Integer.MIN_VALUE){   //如果超出矩阵范围，或者遇见零元素及设置过距离step的元素则跳过，只对未遍历到的操作
                        continue;
                    }

                    mat[x][y] = step;
                    queue.offer(new int[]{x, y});
                }
            }
            step++;  //下次遍历到的-1元素相比前一次距离step加1
        }
        return mat;
    }
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
     * 回溯
     */
    private int min;

    public int[][] updateMatrix0(int[][] mat) {
        if (mat == null) return mat;
        // 标记数组
        boolean[][] visitied = new boolean[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    int count = 0;
                    min = Integer.MAX_VALUE;
                    dfs(mat, i, j, count, visitied);
                    mat[i][j] = min;
                }
            }
        }
        return mat;
    }

    public void dfs (int[][] arr,int i,int j,int count,boolean[][] visitied) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || visitied[i][j]) return;
        // 剪枝
        if (count > min) {
            return;
        }
        if (arr[i][j] == 0) {
            min = Math.min(min, count);
            return;
        }
        visitied[i][j] = true;
        dfs(arr, i + 1, j, count + 1, visitied);
        dfs(arr, i, j + 1, count + 1, visitied);
        dfs(arr, i - 1, j, count + 1, visitied);
        dfs(arr, i, j - 1, count + 1, visitied);
        // 回溯
        visitied[i][j] = false;
    }
    /**
     * DFS
     * refer to Q286_Walls_and_Gates / Time Limit Exceeded
     */
    public int[][] updateMatrix1(int[][] mat) {
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[i].length; ++j) {
                if (mat[i][j] == 1) {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[i].length; ++j) {
                if (mat[i][j] == 0) {
                    dfs(mat, i, j, 0);
                }
            }
        }
        return mat;
    }

    void dfs(int[][] mat, int i, int j, int step) {
        if (i < 0 || i >= mat.length || j < 0 || j >= mat[i].length || mat[i][j] < step) {
            return;
        }
        mat[i][j] = step;

        dfs(mat, i + 1, j, step + 1);
        dfs(mat, i - 1, j, step + 1);
        dfs(mat, i, j + 1, step + 1);
        dfs(mat, i, j - 1, step + 1);
    }
    /**
     * DFS
     * can pass all test cases
     */
   static public int[][] updateMatrix3(int[][] mat) {
        int[][] res = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat[i].length; ++j) {
                if (mat[i][j] == 0) {
                    dfs1(mat, i, j, 0, res);
                }
            }
        }
        return res; // return res
    }

    static void dfs1(int[][] mat, int i, int j, int step, int[][] res) {
        if (i < 0 || i >= mat.length || j < 0 || j >= mat[i].length
                || (mat[i][j] == 0 && step > 0) // let the first mat[i][j] == 0 pass
                || (mat[i][j] == 1 && res[i][j] > 0 && res[i][j] <= step)) { // let mat[i][j] == 1 and distance > step pass
            return;
        }

        res[i][j] = step;

        dfs1(mat, i + 1, j, step + 1, res);
        dfs1(mat, i - 1, j, step + 1, res);
        dfs1(mat, i, j + 1, step + 1, res);
        dfs1(mat, i, j - 1, step + 1, res);
    }
}
