package com.answer.dfs_bfs;

import com.template.UnionFind;

import java.util.Arrays;
import java.util.*;

public class Q130_Surrounded_Regions {
    /**
     * 被围绕的区域
     * 一个 m x n 的矩阵 board ，由若干字符(letters) 'X' 和 'O' 组成，捕获 所有 被围绕的区域(capture regions that are surrounded)：
     *  Connect: A cell is connected to adjacent cells horizontally or vertically.
     *  Region: To form a region connect every 'O' cell.
     *  Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
     *  连接：一个单元格与水平或垂直方向上相邻的单元格连接。
     *  区域：连接所有 'O' 的单元格来形成一个区域。
     *  围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
     * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
     * 通过 原地 将输入矩阵中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。你不需要返回任何值。
     *
     * 输入：board = [ ["X","X","X","X"],
     *                ["X","O","O","X"],
     *                ["X","X","O","X"],
     *                ["X","O","X","X"]]
     * 输出：[ ["X","X","X","X"],
     *        ["X","X","X","X"],
     *        ["X","X","X","X"],
     *        ["X","O","X","X"]]
     * 解释： 在上图中，底部的区域没有被捕获，因为它在 board 的边缘并且不能被围绕。
     */
    public static void main(String[] args) {
        /**
         *  X X X X
         *  X O X X
         *  X X O X
         *  X O O X
         */
    //    char[][] board = {{'X','X','X','X'},{'X','O','X','X'},{'X','X','O','X'},{'X','O','O','X'}};
        char[][] board = {{'O','O','O'},{'O','O','O'},{'O','O','O'}};
   //     char[][] board = {{'O','O'},{'O','O'}};
        solve_3(board);
        for(char[] c : board){
            System.out.println(Arrays.toString(c));
        }
    }
    /**
     * Approach 1: DFS (Depth-First Search) Recursive
     * 从四周向中间 DFS，将所有与最外层 O 相连通的区域标记，最后将剩余的 O 更改为 X。
     */
    public static void solve_1(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if((i == 0 || i == m - 1 || j == 0 || j == n - 1) // 第一列/最后一行/第一列最后一列
                        && board[i][j] == 'O'){    // 从边缘o开始搜索
                    dfs_1(board, i, j);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void dfs_1(char[][] board, int i, int j) {
        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] == 'X' || board[i][j] == '#'){
            return; // board[i][j] == '#' 说明已经搜索过了.
        }
        board[i][j] = '#';
        dfs_1(board, i-1, j);
        dfs_1(board, i+1, j);
        dfs_1(board, i, j-1);
        dfs_1(board, i, j+1);
    }
    /**
     * dfs_1()的另一种形式
     */
    final int[][] dir={{0,1},{1,0},{0,-1},{-1,0}}; // 四个方向

    void dfs_1a(char[][] board, int x, int y) {
        board[x][y] = '#';
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx >= 0 && nx < (board.length - 1) && ny >= 0 && ny < (board[0].length - 1)  && board[nx][ny] == 'O') {
                dfs_1a(board, nx, ny);  // 从边缘第一个是o的开始搜索
            }
        }
    }
    /**
     * Approach 1a: DFS (Depth-First Search) Iterative
     */
    public static void solve_2(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if((i == 0 || i == m - 1 || j == 0 || j == n - 1) // 第一列/最后一行/第一列最后一列
                        && board[i][j] == 'O'){
                    dfs_2(board, i, j);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    // 深度优先搜索
    public static void dfs_2(char[][] board, int i, int j) {
        Deque<Pos> stack = new ArrayDeque<>();
        stack.push(new Pos(i,j));
        board[i][j] = '#';
        while(!stack.isEmpty()){
            Pos current = stack.peek(); // 取出当前stack 顶, 不弹出.
            if(current.i - 1 >= 0
                    && board[current.i-1][current.j] == 'O'){
                stack.push(new Pos(current.i-1,current.j));
                board[current.i-1][current.j] = '#';
                continue; // 有continue.
            }
            if (current.i + 1 <= board.length - 1
                    && board[current.i + 1][current.j] == 'O') {
                stack.push(new Pos(current.i + 1, current.j));
                board[current.i + 1][current.j] = '#';
                continue;
            }
            if (current.j - 1 >= 0
                    && board[current.i][current.j - 1] == 'O') {
                stack.push(new Pos(current.i, current.j - 1));
                board[current.i][current.j - 1] = '#';
                continue;
            }
            if (current.j + 1 <= board[0].length - 1
                    && board[current.i][current.j + 1] == 'O') {
                stack.push(new Pos(current.i, current.j + 1));
                board[current.i][current.j + 1] = '#';
                continue;
            }
            stack.pop(); // 如果上下左右都搜索不到,本次搜索结束，弹出stack
        }
    }
    // 深度优先搜索 another form
    public static void dfs_2a(char[][] board, int i, int j) {
        Deque<Pos> stack = new ArrayDeque<>();
        stack.push(new Pos(i,j));
        board[i][j] = '#';
        while(!stack.isEmpty()){
            Pos current = stack.pop(); // 取出当前stack 顶
            if(current.i - 1 >= 0
                    && board[current.i-1][current.j] == 'O'){
                stack.push(new Pos(current.i-1,current.j));
                board[current.i-1][current.j] = '#';
                // 没有continue.
            }
            if (current.i + 1 <= board.length - 1
                    && board[current.i + 1][current.j] == 'O') {
                stack.push(new Pos(current.i + 1, current.j));
                board[current.i + 1][current.j] = '#';
            }
            if (current.j - 1 >= 0
                    && board[current.i][current.j - 1] == 'O') {
                stack.push(new Pos(current.i, current.j - 1));
                board[current.i][current.j - 1] = '#';
            }
            if (current.j + 1 <= board[0].length - 1
                    && board[current.i][current.j + 1] == 'O') {
                stack.push(new Pos(current.i, current.j + 1));
                board[current.i][current.j + 1] = '#';
            }
        }
    }
    /**
     * Approach 2: BFS (Breadth-First Search) Iterative
     */
    public static void solve_3(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if((i == 0 || i == m - 1 || j == 0 || j == n - 1) // 第一列/最后一行/第一列最后一列
                        && board[i][j] == 'O'){
                    bfs_a(board, i, j);
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }
    // 广度优先搜索
    public static void bfs(char[][] board, int i, int j) {
        Deque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(i,j));
        board[i][j] = '#';
        while(!queue.isEmpty()){
            Pos current = queue.poll();
            if(current.i - 1 >= 0
                    && board[current.i-1][current.j] == 'O'){
                queue.offer(new Pos(current.i-1,current.j));
                board[current.i-1][current.j] = '#';
                // 没有continue.
            }
            if (current.i + 1 <= board.length - 1
                    && board[current.i + 1][current.j] == 'O') {
                queue.offer(new Pos(current.i + 1, current.j));
                board[current.i + 1][current.j] = '#';
            }
            if (current.j - 1 >= 0
                    && board[current.i][current.j - 1] == 'O') {
                queue.offer(new Pos(current.i, current.j - 1));
                board[current.i][current.j - 1] = '#';
            }
            if (current.j + 1 <= board[0].length - 1
                    && board[current.i][current.j + 1] == 'O') {
                queue.offer(new Pos(current.i, current.j + 1));
                board[current.i][current.j + 1] = '#';
            }
        }
    }
    /**
     * another form
     */
  static  int[][] dirs1 = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void bfs_a(char[][] board, int i, int j) {
        Deque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(i,j));
   //     board[i][j] = '#';
        while(!queue.isEmpty()){
            Pos current = queue.poll();
            if(current.i >= 0 && current.i <= board.length - 1 && current.j >= 0 && current.j <= board[0].length - 1
                    && board[current.i][current.j] == 'O'){
                board[current.i][current.j] = '#';
                for(int[] dir : dirs1){
               //     if(current.i + dir[0] >=0 && current.i + dir[0]<board.length && current.j + dir[1] >=0&& current.j + dir[1]<board[0].length) {
                        queue.offer(new Pos(current.i + dir[0], current.j + dir[1]));
                //    }
                }
            }
        }
    }
    /**
     * Approach 3: Union-Find
     */
    public static void solve_4(char[][] board) {
        if (board == null || board.length == 0)
            return;

        int rows = board.length;
        int cols = board[0].length;

        // 用一个虚拟节点, 边界上的O 的父节点都是这个虚拟节点
        UnionFind uf = new UnionFind(rows * cols + 1);
        int dummyNode = rows * cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    // 遇到O进行并查集操作合并
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) { // 第一列/最后一行/第一列最后一列
                        // 边界上的O,把它和dummyNode 合并成一个连通区域.
                        uf.union(node(i, j, cols), dummyNode);
                    } else {
                        // 和上下左右合并成一个连通区域.
                        if (i > 0 && board[i - 1][j] == 'O')
                            uf.union(node(i, j, cols), node(i - 1, j, cols));
                        if (i < rows - 1 && board[i + 1][j] == 'O')
                            uf.union(node(i, j, cols), node(i + 1, j, cols));
                        if (j > 0 && board[i][j - 1] == 'O')
                            uf.union(node(i, j, cols), node(i, j - 1, cols));
                        if (j < cols - 1 && board[i][j + 1] == 'O')
                            uf.union(node(i, j, cols), node(i, j + 1, cols));
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (uf.isConnected(node(i, j, cols), dummyNode)) {
                    // 和dummyNode 在一个连通区域的,那么就是O；
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }

    }

    static int node(int i, int j, int cols) {
        return i * cols + j;
    }
}

class Pos{
    int i;
    int j;
    Pos(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
