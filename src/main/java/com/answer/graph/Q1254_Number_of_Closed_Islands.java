package com.answer.graph;

import java.util.*;

public class Q1254_Number_of_Closed_Islands {
    /**
     * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s
     * and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
     * Return the number of closed islands.
     * 统计封闭岛屿的数目
     * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
     * 请返回 封闭岛屿 的数目。
     *
     * 示例 1：
     *  输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     *  输出：2
     *  解释：灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
     */
    public static void main(String[] args) {
/*        int[][] grid = {{1,1,1,1,1,1,1,0},
                        {1,0,0,0,0,1,1,0},
                        {1,0,1,0,1,1,1,0},
                        {1,0,0,0,0,1,0,1},
                        {1,1,1,1,1,1,1,0}};*/
         /*  int[][] grid = {{0,1,1,1,0},
                           {1,0,1,0,1},
                           {1,0,1,0,1},
                           {1,0,0,0,1},
                           {0,1,1,1,0}};*/
/*        int[][]  grid = {{0,0,1,0,0},
                         {0,1,0,1,0},
                         {0,1,1,1,0}};*/
/*        int[][]  grid = {{0,0,1,1,0,1,0,0,1,0},
                         {1,1,0,1,1,0,1,1,1,0},
                         {1,0,1,1,1,0,0,1,1,0},
                         {0,1,1,0,0,0,0,1,0,1},
                         {0,0,0,0,0,0,1,1,1,0},
                         {0,1,0,1,0,1,0,1,1,1},
                         {1,0,1,0,1,1,0,0,0,1},
                         {1,1,1,1,1,1,0,0,0,0},
                         {1,1,1,0,0,1,0,1,0,1},
                         {1,1,1,0,1,1,0,1,1,0}};*/
        int[][]   grid = {{1,1,1,1,1,1,1},
                          {1,0,0,0,0,0,1},
                          {1,0,1,1,1,0,1},
                          {1,0,1,0,1,0,1},
                          {1,0,1,1,1,0,1},
                          {1,0,0,0,0,0,1},
                          {1,1,1,1,1,1,1}};

        int res = closedIsland1( grid);
        System.out.println(res);

        boolean[] r = new boolean[]{true, false, true, false};
        boolean f = true;
        for(int i = 0; i < 4; i++){
            f = f && r[i];
        }
        System.out.println(f);
    }
    /**
     * Similar with Q200 Number of Islands 本质是均为遍历图中的连通区域，唯一不同的是本题中的岛屿要求是「封闭」的
     */
    static public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if(dfs(i, j, grid)) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    static public boolean dfs(int i, int j, int[][] grid) {
        // dfs返回结果为：是否为封闭岛屿，两个终止条件
        //  如果能够触及边界外，说明不是封闭岛屿，return false
        //  如果grid[i][j]为水域，说明被阻拦了，return true
        // 而非封闭岛屿它一定可以触及到边界外。
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1){
            return false;
        }
        if(grid[i][j] == 1){
            return true;
        }

        grid[i][j] = 1;
        // must run the following function separately 避免短路运算
        boolean b1 = dfs(i - 1, j, grid); //这种做法稳妥
        boolean b2 = dfs(i + 1, j, grid);
        boolean b3 = dfs(i, j - 1, grid);
        boolean b4 = dfs(i, j + 1, grid);

        return b1 && b2 && b3 && b4;
        // 短路运算大坑，直接不计算后面的dfs了，导致相连的0没有设置为1，结果不对
        // return dfs(i - 1, j, grid) && dfs(i + 1, j, grid) && dfs(i, j - 1, grid) && dfs(i, j + 1, grid); // doesn't work
        // return dfs(i - 1, j, grid) & dfs(i + 1, j, grid) & dfs(i, j - 1, grid) & dfs(i, j + 1, grid); // works too
    }
    /**
     * another form
     * 对于每个dfs分支返回值，对f做一次 "f = f & dfs()" ，即 与 运算， 因此只要有一个分支结果判断为false，最终返回值就是false，代表这个岛屿不封闭。
     * 不可以在找到不封闭条件时直接return出来，因为可能没有dfs完全，而图论的dfs一般是一边dfs一边标记，因此不完全的dfs是会影响后续结果的，
     * 我们需要让它递归直到完全结束，而这个代码中我们一直要维护的其实就是f这个布尔变量，它从始至终都在做与运算，所以不必担心最后结果会出问题，
     * 因为只要遇到不封闭的条件就注定了最终返回的是false（就像无数个1和一个零一起做相乘运算）
     */
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean dfs_a(int i, int j, int[][] grid) {
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1){
            return false;
        }
        if(grid[i][j] == 1){
            return true;
        }

        grid[i][j] = 1;
        boolean result = true;
        //  因此只要有一个分支结果判断为false，最终返回值就是false，代表这个岛屿不封闭
        for(int k = 0; k < 4; k++){
            // must use &, not &&. If && is used, it is the same as "dfs(i - 1, j, grid) && dfs(i + 1, j, grid) && dfs(i, j - 1, grid) && dfs(i, j + 1, grid)"
            result = result & dfs_a(i + dirs[k][0], j + dirs[k][1], grid);  //关键步骤，与运算 &
       //   result = result && dfs(i + dirs[k][0], j + dirs[k][1], grid); // doesn't work
        }
        return result;
    }
    /**
     * anther form
     */
    public boolean dfs_b(int i, int j, int[][] grid) {
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1){
            return false;
        }
        if(grid[i][j] == 1){
            return true;
        }

        grid[i][j] = 1;
        boolean result = true;
        for(int k = 0; k < 4; k++){
            if(!dfs_b(i + dirs[k][0], j + dirs[k][1], grid)) {
                result = false;  // 这里必须要将四个方向都跑完，不能遇到一个为false就返回
            }
        }
        return result;
    }
    /**
     * 同上 works too
     */
    static public int closedIsland1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int count = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (!visited[i][j] && grid[i][j] == 0) {
                    boolean isIsland = dfs1(i, j, grid, visited);
                    if(isIsland) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }

    static public boolean dfs1(int i, int j, int[][] grid, boolean[][] visited) {
        if( i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1){
            return false;
        }
        if(grid[i][j] == 1){
            return true;
        }
        if(visited[i][j]){
            return true;
        }

        visited[i][j] = true;
        // must run the following function seperately
        boolean ret1 = dfs1(i - 1, j, grid, visited);
        boolean ret2 = dfs1(i + 1, j, grid, visited);
        boolean ret3 = dfs1(i, j - 1, grid, visited);
        boolean ret4 = dfs1(i, j + 1, grid, visited);
        return ret1 && ret2 && ret3 && ret4;
     // return ret1 & ret2 & ret3 & ret4; // works too
     // return dfs1(i - 1, j, grid, visited) && dfs1(i + 1, j, grid, visited) && dfs1(i, j - 1, grid, visited) && dfs1(i, j + 1, grid, visited); // doesn't work
     // return dfs1(i - 1, j, grid, visited) & dfs1(i + 1, j, grid, visited) & dfs1(i, j - 1, grid, visited) & dfs1(i, j + 1, grid, visited); // works too
    }
    /**
     * 先外后内
     * 从网格图的第一行、最后一行、第一列和最后一列的所有 0 出发，DFS 访问四方向的 0，并把这些 0 标记成「访问过」。
     * 代码实现时可以直接把 0 修改成 1。
     * 然后从剩下的 0 出发，按照同样的方式 DFS 访问四方向的 0，同时把 0 改成 1。每次从一个新的 0 出发（起点），
     * 就意味着找到了一个新的封闭岛屿，答案加一。
     */
    static public int closedIsland2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 如果是第一行和最后一行，访问所有格子  如果不是，只访问第一列和最后一列的格子
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 0 ) {
                dfs2(i, 0, grid);
            }
            if (grid[i][n - 1] == 0) {
                dfs2(i, n - 1, grid);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 0 ) {
                dfs2(0, j, grid);
            }
            if (grid[m - 1][j] == 0) {
                dfs2(m - 1, j, grid);
            }
        }

        int count = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) { // 从没有访问过的 0 出发
                    ++count;  // 一定是封闭岛屿
                    dfs2(i, j, grid);
                }
            }
        }
        return count;
    }

    static public void dfs2(int i, int j, int[][] grid) {
        if( i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1
                || grid[i][j] == 1){
            return;
        }

        grid[i][j] = 1;// 标记 (x,y) 被访问，避免重复访问

        dfs2(i - 1, j, grid);
        dfs2(i + 1, j, grid);
        dfs2(i, j - 1, grid);
        dfs2(i, j + 1, grid);
    }
    /**
     * 出界标记
     * 注意每次 DFS 应当把这个岛屿的所有格子都遍历完，不能中途退出 DFS。如果在中途退出 DFS，会导致某些格子没有遍历到，
     * 那么在后续以这个格子为起点 DFS 时，可能会误把它当作封闭岛屿上的格子，从而算出比预期结果更大的值。
     */
    private boolean closed;

    public int closedIsland3(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        if (m < 3 || n < 3) {
            return 0;
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 0) { // 从没有访问过的 0 出发
                    closed = true; // 从不在边界的 0 出发，DFS 访问四方向的 0。DFS 之前，设置全局变量 closed 为 true。
                    dfs4(grid, i, j);
                    if (closed) {
                        ans++; // 递归结束时，如果 closed 仍然为 true，说明当前遍历的是一个封闭岛屿，答案加一。
                    }
                }
            }
        }
        return ans;
    }

    private void dfs4(int[][] grid, int x, int y) {
        if (x == 0 || x == grid.length - 1 || y == 0 || y == grid[x].length - 1) {
            if (grid[x][y] == 0) { // 如果 DFS 中到达边界，设置 closed 为 false，意味着当前遍历的岛屿不是封闭岛屿。
                closed = false; // 到达边界
            }
            return;
        }
        if (grid[x][y] == 1){
            return;
        }
        grid[x][y] = 1; // 标记{ (x,y) 被访问，避免重复访问

        dfs4(grid, x - 1, y);
        dfs4(grid, x + 1, y);
        dfs4(grid, x, y - 1);
        dfs4(grid, x, y + 1);
    }
    /**
     * 广度优先搜索
     */
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int closedIsland5(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Queue<int[]> queue = new ArrayDeque<int[]>();
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 1;
                    boolean closed = true;

                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();
                        int cx = arr[0], cy = arr[1];
                        if (cx == 0 || cy == 0 || cx == m - 1 || cy == n - 1) {
                            closed = false;
                        }
                        for (int d = 0; d < 4; d++) {
                            int nx = cx + dir[d][0];
                            int ny = cy + dir[d][1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                                grid[nx][ny] = 1;
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }
                    if (closed) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    /**
     * 深度优先搜索
     */
    public int closedIsland6(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Deque<int[]> stack = new ArrayDeque<int[]>();
                    stack.push(new int[]{i, j});
                    grid[i][j] = 1;
                    boolean closed = true;

                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int cx = cur[0], cy = cur[1];
                        if (cx == 0 || cy == 0 || cx == m - 1 || cy == n - 1) {
                            closed = false;
                        }
                        for (int d = 0; d < 4; d++) {
                            int nx = cx + dir[d][0];
                            int ny = cy + dir[d][1];
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                                grid[nx][ny] = 1;
                                stack.push(new int[]{nx, ny});
                            }
                        }
                    }
                    if (closed) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}
