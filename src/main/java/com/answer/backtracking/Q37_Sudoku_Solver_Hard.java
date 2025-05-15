package com.answer.backtracking;

public class Q37_Sudoku_Solver_Hard {
    // 解数独可以说是非常难的题目(二维递归)
    // 本题中棋盘的每一个位置都要放一个数字（而N皇后是一行只放一个皇后），并检查数字是否合法，解数独的树形结构要比N皇后更宽更深
    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }

    private boolean solveSudokuHelper(char[][] board){
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
        for (int i = 0; i < 9; i++){ // 遍历行
            for (int j = 0; j < 9; j++){ // 遍历列
                if (board[i][j] != '.'){ // 跳过原始数字
                    continue;
                }
                for (char k = '1'; k <= '9'; k++){ // (i, j) 这个位置放k是否合适
                    if (isValidSudoku(i, j, k, board)){
                        board[i][j] = k;   // 放置k
                        if (solveSudokuHelper(board)){ // backtracking 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = '.'; // 回溯，撤销k
                    }
                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }
    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    private boolean isValidSudoku(int row, int col, char val, char[][] board){
        // 同行是否重复
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++){
            if (board[j][col] == val){
                return false;
            }
        }
        // 9宫格里是否重复
        // 判断一个九宫内是否有重复的值
        int startRow = (row / 3) * 3;  // 1/3 = 0
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 解法二(bitmap标记)
     * 使用数组记录行、列、3x3 宫格内的数字是否存在，每次尝试对应位置所有可能的数字，遍历到最后一个位置即可得到正确答案。
     */
    int[] rowBit = new int[9];
    int[] colBit = new int[9];
    int[] square9Bit = new int[9];

    public void solveSudoku1(char[][] board) {
        // 1 10 11
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                int numBit = 1 << (board[y][x] - '1');
                rowBit[y] ^= numBit;
                colBit[x] ^= numBit;
                square9Bit[(y / 3) * 3 + x / 3] ^= numBit;
            }
        }
        backtrack(board, 0);
    }

    public boolean backtrack(char[][] board, int n) {
        if (n >= 81) {
            return true;
        }
        // 快速算出行列编号 n/9 n%9
        int row = n / 9;
        int col = n % 9;

        if (board[row][col] != '.') {
            return backtrack(board, n + 1);
        }

        for (char c = '1'; c <= '9'; c++) {
            int numBit = 1 << (c - '1');
            if (!isValid(numBit, row, col)) continue;
            {
                board[row][col] = c;    // 当前的数字放入到数组之中，
                rowBit[row] ^= numBit; // 第一行rowBit[0],第一个元素eg: 1 , 0^1=1,第一个元素:4, 100^1=101,...
                colBit[col] ^= numBit;
                square9Bit[(row / 3) * 3 + col / 3] ^= numBit;
            }
            if (backtrack(board, n + 1)) return true;
            {
                board[row][col] = '.';    // 不满足条件，回退成'.'
                rowBit[row] &= ~numBit; // 第一行rowBit[0],第一个元素eg: 1 , 101&=~1==>101&111111110==>100
                colBit[col] &= ~numBit;
                square9Bit[(row / 3) * 3 + col / 3] &= ~numBit;
            }
        }
        return false;
    }

    boolean isValid(int numBit, int row, int col) {
        // 左右
        if ((rowBit[row] & numBit) > 0) return false;
        // 上下
        if ((colBit[col] & numBit) > 0) return false;
        // 9宫格: 快速算出第n个九宫格,编号[0,8] , 编号=(row / 3) * 3 + col / 3
        if ((square9Bit[(row / 3) * 3 + col / 3] & numBit) > 0) return false;
        return true;
    }
}
