package com.answer.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q52_N_Queens_II {
    /**
     * 回溯: 参考51.N皇后，基本没有区别
     */
    int count = 0;
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        backTrack(n, 0, board);
        return count;
    }
    private void backTrack(int n, int row, char[][] board) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, board)) { // 验证合法就可以放
                board[row][col] = 'Q';// 放置皇后
                backTrack(n, row + 1, board);
                board[row][col] = '.'; // 回溯
            }
        }
    }
    private boolean isValid(int row, int col, int n, char[][] board) {
        // 检查列
        for (int i = 0; i < row; ++i) {// 这是一个剪枝
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // 检查45度对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查135度对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    /**
     * 参考Q51. N-Queens
     * 方法2：使用boolean数组表示已经占用的直(斜)线
     */
    int number = 0;
    boolean[] usedCol, usedDiag45, usedDiag135;    // boolean数组中的每个元素代表一条直(斜)线
    public int totalNQueens1(int n) {
        usedCol = new boolean[n];                  // 列方向的直线条数为 n
        usedDiag45 = new boolean[2 * n - 1];       // 45°方向的斜线条数为 2 * n - 1
        usedDiag135 = new boolean[2 * n - 1];      // 135°方向的斜线条数为 2 * n - 1
        //用于收集结果, 元素的index表示棋盘的row，元素的value代表棋盘的column
        int[] board = new int[n];
        backTracking(board, n, 0);
        return number;
    }
    private void backTracking(int[] board, int n, int row) {
        if (row == n) {
            //收集结果
            number++;
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
