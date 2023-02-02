package com.answer.array;

import java.util.*;

public class Q200_Number_of_Islands {
    public static void main(String[] args) {
        /**
         *  1 1 1 1 0
         *  1 1 0 1 0
         *  1 1 0 0 0
         *  0 0 0 0 0
         */
        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        /**
         * 1 1 0 0 0
         * 1 1 0 0 0
         * 0 0 1 0 0
         * 0 0 0 1 1
         */
        char[][] grid1 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println(numIslands(grid));
        System.out.println(numIslands(grid1));
    }
    /**
     * Approach #1 DFS
     */
    static int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public static int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                 //   dfs(grid, i, j);
                    dfs_recursive(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    public static void dfs(char[][] grid, int i, int j){
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{i, j});
        grid[i][j] = '2';
        while(!stack.isEmpty()){
            int[] cur = stack.pop();
            for(int index = 0; index < 4; index++){
                int[] d = dir[index];
                int x = d[0] + cur[0];
                int y = d[1] + cur[1];
                if(isWithin(grid, x, y) && grid[x][y] == '1'){
                    grid[x][y] = '2';
                    stack.push(new int[]{x, y});
                }
            }
        }
    }
    /**
     * Recursive
     */
    public static void dfs_recursive(char[][] grid, int i, int j){
        if(!isWithin(grid, i, j)){
            return;
        }
        if(grid[i][j] == '0' || grid[i][j] == '2'){
            return;
        }
        grid[i][j] = '2';
        dfs_recursive(grid, i - 1, j);
        dfs_recursive(grid, i + 1, j);
        dfs_recursive(grid, i, j - 1);
        dfs_recursive(grid, i, j + 1);
    }
    public static boolean isWithin(char[][] grid, int x, int y){
        if(x >= 0 && x <= grid.length - 1 && y >= 0 && y <= grid[0].length - 1){
            return true;
        }
        return false;
    }
    /**
     * Approach #2: BFS
     */
    public static int numIslands_1(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    public static void bfs(char[][] grid, int i, int j){
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = '2';
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int index = 0; index < 4; index++){
                int[] d = dir[index];
                int x = d[0] + cur[0];
                int y = d[1] + cur[1];
                if(isWithin(grid, x, y) && grid[x][y] == '1'){
                    grid[x][y] = '2';
                    queue.offer(new int[]{x, y});
                }
            }
        }
    }
    /**
     * Approach #3: Union Find (aka Disjoint Set)
     */
    public static int numIslands_2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }
}
class UnionFind {
    int count;
    int[] parent;
    int[] rank;
    public UnionFind(char[][] grid) {
        count = 0;
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        rank = new int[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    parent[i * n + j] = i * n + j;
                    ++count;
                }
                rank[i * n + j] = 0;
            }
        }
    }
    public int find(int i) {
        if (parent[i] != i) parent[i] = find(parent[i]);
        return parent[i];
    }

    public void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) {
                parent[rooty] = rootx;
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty;
            } else {
                parent[rooty] = rootx;
                rank[rootx] += 1;
            }
            --count;
        }
    }

    public int getCount() {
        return count;
    }

}