package com.answer.array;

public class Q48_Rotate_Image {
    /**
     * 通过两次翻转，完成顺时针旋转，分别是按主对角线翻转，然后再左右翻转即可。
     */
    public void rotate_0(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) { // 主对角线翻转
            for (int j = 0; j < i; j++) {
                //    swap(matrix[i][j], matrix[j][i]);
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) { // 左右翻转
            for (int j = 0; j < n / 2; j++) {
                //     swap(matrix[i][j], matrix[i][n - j - 1]);
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    /**
     * Approach 1: Rotate Groups of Four Cells
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
    /**
     * use new matrix
     */
    public void rotate_1(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }
}
