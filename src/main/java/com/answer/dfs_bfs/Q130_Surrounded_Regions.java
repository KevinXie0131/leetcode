package com.answer.dfs_bfs;

import com.template.UnionFind;

import java.util.Arrays;
import java.util.*;

public class Q130_Surrounded_Regions {
    public static void main(String[] args) {
        /**
         *  X X X X
         *  X O X X
         *  X X O X
         *  X O O X
         */
        char[][] board = {{'X','X','X','X'},{'X','O','X','X'},{'X','X','O','X'},{'X','O','O','X'}};
   //     char[][] board = {{'O','O'},{'O','O'}};
        solve_4(board);
        for(char[] c : board){
            System.out.println(Arrays.toString(c));
        }
    }
    /**
     * Approach 1: DFS (Depth-First Search) Recursive
     */
    public static void solve_1(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if((i == 0 || i == m - 1 || j == 0 || j == n - 1)
                        && board[i][j] == 'O'){
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
            return;
        }
        board[i][j] = '#';
        dfs_1(board, i-1, j);
        dfs_1(board, i+1, j);
        dfs_1(board, i, j-1);
        dfs_1(board, i, j+1);
    }
    /**
     * Approach 1a: DFS (Depth-First Search) Iterative
     */
    public static void solve_2(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if((i == 0 || i == m - 1 || j == 0 || j == n - 1)
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
                continue;
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
    /**
     * Approach 2: BFS (Breadth-First Search) Iterative
     */
    public static void solve_3(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if((i == 0 || i == m - 1 || j == 0 || j == n - 1)
                        && board[i][j] == 'O'){
                    dfs_3(board, i, j);
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

    public static void dfs_3(char[][] board, int i, int j) {
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
                    if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
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