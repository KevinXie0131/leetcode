package com.answer.backtracking;

import java.util.*;

public class Q51_N_Queens_Hard {
    /**
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that
     * no two queens attack each other.
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 等价于要求任何两个皇后都不能在同一行、同一列以及同一条斜线上。
     */
    /**
     * 回溯 (棋盘的宽度就是for循环的长度，递归的深度就是棋盘的高度)
     * 时间复杂度: O(n!): 第一个皇后有 N 列可以选择，第二个皇后最多有 N−1 列可以选择，第三个皇后最多有 N−2 列可以选择（如果考虑到不能在同一条斜线上，可能的选择数量更少），因此所有可能的情况不会超过 N! 种
     * 空间复杂度: O(n)
     */
    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for(char[] chars : board)
        {
            Arrays.fill(chars,'.');
        }
        //创建棋盘board，dfs从 第一行开始遍历
        dfs(board,0, n);
        return res;
    }

    //dfs遍历每一行，其中的for循环遍历每一列
    public void dfs(char[][] board, int r, int n) { // 参数n是棋盘的大小，然后用row来记录当前遍历到棋盘的第几层了
        if(r == n) { // 当递归到棋盘最底层（也就是叶子节点）的时候，就可以收集结果并返回了
            res.add(toList(board));
            return;
        }
        // 递归深度就是row控制棋盘的行，每一层里for循环的col控制棋盘的列，一行一列，确定了放置皇后的位置。
        // 每次都是要从新的一行的起始位置开始搜，所以都是从0开始。
        for(int i = 0; i < n; i++) {
            if(isValid(board, r, i, n)) {  // 验证合法就可以放
                board[r][i] = 'Q'; // 放置皇后
                dfs(board,r + 1, n);
                board[r][i] = '.'; // 回溯，撤销皇后
            }
        }
    }
    // 检查某一点 (r,c) 能否放皇后
    // 皇后们的约束条件: 不能同行, 不能同列, 不能同斜线
    // 没有在同行进行检查呢？因为在单层搜索的过程中，每一层递归，只会选for循环（也就是同一行）里的一个元素，所以不用去重了。
    public boolean isValid(char[][] board, int r, int c, int n) {
        //检查该列
        for(int i = 0; i < n; i++) { // 这是一个剪枝
            if(board[i][c] == 'Q') {
                return false;
            }
        }
        //检查左上45
        for(int i = r - 1,j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        //检查右上45
        for(int i = r - 1, j = c + 1; i >= 0 && j < n; i--, j++) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    //将 char[][] board 转换为 List<String>
    public List<String> toList(char[][] board) {
        List<String> list = new ArrayList<>();
        for(char[] chars : board) {
            list.add(String.valueOf(chars));
        }
        return list;
    }
    /**
     * 方法2：使用boolean数组表示已经占用的直(斜)线
     * 使用 3 个布尔数组记录列、对角线、反对角线是否存在皇后，在回溯时选择三者不存在的格子放置皇后
     */
    List<List<String>> res1 = new ArrayList<>();
    boolean[] usedCol, usedDiag45, usedDiag135;    // boolean数组中的每个元素代表一条直(斜)线
    public List<List<String>> solveNQueens1(int n) {
        usedCol = new boolean[n];                  // 列方向的直线条数为 n
        usedDiag45 = new boolean[2 * n - 1];       // 45°方向的斜线条数为 2 * n - 1
        usedDiag135 = new boolean[2 * n - 1];      // 135°方向的斜线条数为 2 * n - 1
        //用于收集结果, 元素的index表示棋盘的row，元素的value代表棋盘的column
        int[] board = new int[n];
        backTracking(board, n, 0);
        return res1;
    }
    private void backTracking(int[] board, int n, int row) {
        if (row == n) {
            //收集结果
            List<String> temp = new ArrayList<>();
            for (int i : board) {
                char[] str = new char[n];
                Arrays.fill(str, '.');
                str[i] = 'Q';
                temp.add(new String(str));
            }
            res1.add(temp);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (usedCol[col] || usedDiag45[row + col] || usedDiag135[row - col + n - 1]) {
                continue;
            }
            board[row] = col;
            // 标记该列出现过
            usedCol[col] = true;
            // 同一45°斜线上元素的row + col为定值, 且各不相同
            usedDiag45[row + col] = true;
            // 同一135°斜线上元素row - col为定值, 且各不相同
            // row - col 值有正有负, 加 n - 1 是为了对齐零点
            usedDiag135[row - col + n - 1] = true;
            // 递归
            backTracking(board, n, row + 1);
            usedCol[col] = false;
            usedDiag45[row + col] = false;
            usedDiag135[row - col + n - 1] = false;
        }
    }
}
