package com.answer.array;

public class Q48_Rotate_Image {
    /**
     * 旋转图像: 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     */
    /**
     * 通过两次翻转，完成顺时针旋转，分别是按主对角线翻转，然后再左右翻转即可。
     * 必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
     *
     * 转置：把矩阵按照主对角线翻转，位于 (i,j) 的元素去到 (j,i)。
     * 行翻转：把每一行的内部元素翻转，位于 (j,i) 的元素去到 (j,n−1−i)。
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
                //     swap(matrix[i][j], matrix[i][n - 1 - j]);
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
    /**
     * 水平翻转 + 主对角线翻转
     */
    public void rotate_4(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    /**
     * Approach 1: Rotate Groups of Four Cells
     * 可以把矩形切成4块，我们只要旋转其中的一块就行了，为了方便我们就旋转(0,0)到(n/2,(n+1)/2)为矩形的所有点就行，用临时变量t保存该区域的点
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                // 由于第 1 步 D→A 已经将 A 覆盖（导致 A 丢失），此丢失导致最后第 4 步 A→B 无法赋值。为解决此问题，考虑借助一个「辅助变量 tmp 」预先存储 A
                int temp = matrix[i][j];                //先保存该区域的点
                matrix[i][j] = matrix[n - j - 1][i];    //把该区域下面的点旋转到该区域
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];  //把该区域右下角的点旋转到该区域下面
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];  //把该区域右边的点旋转到该区域右下角
                matrix[j][n - i - 1] = temp;            //将该区域的点旋转到该区域右边
            }
        }
    }
    /**
     * use new matrix
     * 位于 i 行 j 列的元素，去到 j 行 n−1−i 列，即 (i,j)→(j,n−1−i)。
     */
    public void rotate_1(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - 1 - i] = matrix[i][j]; // 由于矩阵中的行列从 0 开始计数，因此对于矩阵中的元素 matrix[row][col]，在旋转后，它的新位置为 matrixnew[col][n−row−1]。
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }
}
