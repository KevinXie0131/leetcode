package com.answer.backtracking;

public class Q79_Word_Search_2 {

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        exist(board, word);
    }
    /**
     * DFS + 回溯
     * another form
     */
    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static public boolean exist(char[][] board, String word) {
        int h = board.length, w = board[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (check(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    static public boolean check(char[][] board, int i, int j, String s, int index) {
        if (board[i][j] != s.charAt(index)) { // 剪枝
            return false;
        } else if (index == s.length() - 1) { // board[i][j] == s.charAt(k) && k is equal to s.length() - 1
            return true;
        }

        char t = board[i][j];
        board[i][j] = '.'; // not use visited[][] to reduce space complexity

        for (int[] dir : directions) {
            int newi = i + dir[0], newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (board[newi][newj] != '.') {
                    if (check(board, newi, newj, s, index + 1)) {
                        return true;
                    }
                }
            }
        }
        board[i][j] = t; // backtracking
        return false;
    }
    /**
     * another form
     */
    static public boolean check_1(char[][] board, int i, int j, String s, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] != s.charAt(k)) { // 剪枝
            return false; // 匹配失败
        } else if (k == s.length() - 1) { // board[i][j] == s.charAt(k) && k is equal to s.length() - 1
            return true;  // 匹配成功
        }

        char t = board[i][j];
        board[i][j] = '.'; // not use visited[][] to reduce space complexity 直接修改, 将其置为空

        boolean res = check_1(board, i - 1, j, s, k + 1)
                   || check_1(board, i, j - 1, s, k + 1)
                   || check_1(board, i + 1, j, s, k + 1)
                   || check_1(board, i,  j + 1, s, k + 1);

        board[i][j] = t; // backtracking 恢复成原来的值（恢复现场）
        return res;
    }
}
