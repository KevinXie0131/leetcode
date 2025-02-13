package com.answer.dfs_bfs;

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
        System.out.println(numIslands_2(grid));
        System.out.println(numIslands_2(grid1));
    }
    /**
     * 深度优先搜索
     */
    public int numIslands1(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][]visited = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j] && grid[i][j] == '1') { // 没有访问过的 同时 是陆地的
                    result++;
                 //   visited[i][j] = true;  // uncomment for 版本二
                    dfs_visited(visited, i, j, grid);
                }
            }
        }
        return result;
    }
    // 版本一的写法是 ：下一个节点是否能合法已经判断完了，传进dfs函数的就是合法节点。
    void dfs_visited0(boolean[][] visited, int x, int y, char [][]grid) {
        if (visited[x][y] || grid[x][y] == 0) {
            return; // 终止条件：访问过的节点 或者 遇到海水
        }
        visited[x][y] = true; // 标记访问过
        for (int i = 0; i < 4; i++) {
            int nextx = x + dir[i][0];
            int nexty = y + dir[i][1];
            if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length) {
                continue;  // 越界了，直接跳过
            }
            dfs_visited0(visited, nextx, nexty, grid);
        }
    }
    // 版本二的写法是：不管节点是否合法，上来就dfs，然后在终止条件的地方进行判断，不合法再return。
    // 理论上来讲，版本一的效率更高一些，因为避免了 没有意义的递归调用，在调用dfs之前，就做合法性判断。 但从写法来说，可能版本二 更利于理解一些。（不过其实都差不太多）
    public void dfs_visited(boolean[][] visited, int x, int y, char [][]grid) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dir[i][0];
            int nextY = y + dir[i][1];
            if(nextY < 0 || nextX < 0 || nextX >= grid.length || nextY >= grid[0].length){  // 越界了，直接跳过
                continue;
            }
            if(!visited[nextX][nextY] && grid[nextX][nextY] == '1') {
                visited[nextX][nextY] = true; // 遇到没访问过的陆地，+1
                dfs_visited(visited, nextX, nextY, grid);  // 将与其链接的陆地都标记上 true
            }
        }
    }
    /**
     * Approach #1 DFS 深度优先搜索
     */
    static int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}}; // 四个方向

    public static int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;    // row
        int n = grid[0].length; // column

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
    // 使用stack做DFS
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
     * Recursive 递归
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
     * Approach #2: BFS 使用Queue
     */
    public static int numIslands_1(char[][] grid) {
        int res = 0;
        int m = grid.length;    // row
        int n = grid[0].length; // column

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
     * Approach #3: Union Find (aka Disjoint Set) 并查集
     * 并查集：找到共同的祖先
     *          Union(x, y)：合并x, y成为公同的祖先
     *          find(x)：找到x的祖先
     *          x索引 = root[x]
     */
    public static int numIslands_2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        UnionFind_Simple uf = new UnionFind_Simple(grid);

        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * col + c, (r - 1) * col + c);
                    }
                    if (r + 1 < row && grid[r + 1][c] == '1') {
                        uf.union(r * col + c, (r + 1) * col + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * col + c, r * col + c - 1);
                    }
                    if (c + 1 < col && grid[r][c + 1] == '1') {
                        uf.union(r * col + c, r * col + c + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }
}
/**
 *
 */
class UnionFind_Simple {
    int count;
    int[] parent;
    public UnionFind_Simple(char[][] grid) { // 二维数组 -> 一维数组
        count = 0;
        int row = grid.length;
        int col = grid[0].length;
        parent = new int[row * col];

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == '1') {
                    parent[i * col + j] = i * col + j;
                    ++count;
                }
            }
        }
    }

    public int find(int index) { // 找到index的祖先
        if (parent[index] != index) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }

    public int find_1(int index) { // 另一种形式
        if (parent[index] == index) { //找到祖先
            return index;
        } else {
            parent[index] = find(parent[index]); // Quick find
        }
        return parent[index];
    }

    public int find_2(int index) { // 另一种形式
        if (parent[index] == index) { //找到祖先
            return index;
        } else {
            return find(parent[index]);
        }
    }

    public void union(int x, int y) { // 合并x, y成为公同的祖先
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            parent[rooty] = rootx;
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}
/**
 *
 */
class UnionFind {
    int count;
    int[] parent;
    int[] rank; // like weight

    public UnionFind(char[][] grid) { // 二维数组 -> 一维数组
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
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int x, int y) { // Quick Union: Avoid too high tree
        int rootx = find(x);
        int rooty = find(y);
        if (rootx != rooty) {
            if (rank[rootx] > rank[rooty]) { // link lower tree to higher tree
                parent[rooty] = rootx; // link y yo x, since x is higher
            } else if (rank[rootx] < rank[rooty]) {
                parent[rootx] = rooty; // link x yo y, since y is higher
            } else {
                parent[rooty] = rootx; // y is the same high as x, link y yo x
                rank[rootx] += 1; // x is higher by 1
            }
            --count;
        }
    }

    public int getCount() {
        return count;
    }

}
