package com.answer.backtracking;

import java.util.Arrays;

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
}
