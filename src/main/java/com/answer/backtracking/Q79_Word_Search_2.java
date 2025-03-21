package com.answer.backtracking;

public class Q79_Word_Search_2 {

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (check(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    public boolean check(char[][] board, int i, int j, String s, int k) {
        if (board[i][j] != s.charAt(k)) {
            return false;
        } else if (k == s.length() - 1) {
            return true;
        }

        char t = board[i][j];
        board[i][j] = '.'; // not use visited[][] to reduce space complexity

        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (board[newi][newj] != '.') {
                    if (check(board, newi, newj, s, k + 1)) return true;
                }
            }
        }
        board[i][j] = t; // backtracking
        return false;
    }
}
