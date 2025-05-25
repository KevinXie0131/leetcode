package com.answer.array;

import java.util.ArrayList;
import java.util.List;

public class Q73_Set_Matrix_Zeroes {
    /**
     * 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：[[1,0,1],[0,0,0],[1,0,1]]
     */
    /**
     * 原地in place:  operates directly on the input data structure without requiring extra space proportional to the input size.
     * 使用 原地 算法 / must do it in place.
     */
    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
        setZeroes(matrix);
        System.out.println(matrix);
    }
    /**
     * Approach 1: Additional Memory Approach
     */
    public static void setZeroes(int[][] matrix) {
        List<int[]> list = new ArrayList<>();

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    list.add(new int[]{i ,j}); // 使用标记
                }
            }
        }
        for(int[] index : list){
            for(int x = 0; x < matrix.length; x++){
                matrix[x][index[1]] = 0;
            }
            for(int y = 0; y < matrix[0].length; y++){
                matrix[index[0]][y] = 0;
            }
        }
    }
    /**
     * Approach 2: O(1) Space, Efficient Solution
     * 我们可以用矩阵的第一行和第一列代替方法一中的两个标记数组，以达到 O(1) 的额外空间。但这样会导致原数组的第一行和第一列被修改，
     * 无法记录它们是否原本包含 0。因此我们需要额外使用两个标记变量分别记录第一行和第一列是否原本包含 0。
     *
     * 我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，然后反过来使用第一行与第一列去更新其他行与列，
     * 最后使用两个标记变量更新第一行与第一列即可。
     */
    public void setZeroes_1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean colZero = false, rowZero = false;
        // 预处理出两个标记变量
        for (int i = 0; i < m; i++) { // 1. 扫描「首行」和「首列」记录「首行」和「首列」是否该被置零
            if (matrix[i][0] == 0) {
                colZero = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                rowZero = true;
            }
        }
        // 使用其他行与列去处理第一行与第一列，
        for (int i = 1; i < m; i++) { // 2.1 扫描「非首行首列」的位置，如果发现零，将需要置零的信息存储到该行的「最左方」和「最上方」的格子内
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 然后反过来使用第一行与第一列去更新其他行与列
        for (int i = 1; i < m; i++) { // 2.2 根据刚刚记录在「最左方」和「最上方」格子内的置零信息，进行「非首行首列」置零
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 使用两个标记变量更新第一行与第一列
        if (colZero) { // 3. 根据最开始记录的「首行」和「首列」信息，进行「首行首列」置零
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (rowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
    /**
     * 另一种形式
     * 使用矩阵的第一行和第一列标记对应行列是否置零。
     */
    public void setZeroes_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean colZero = false, rowZero = false;
        // 需要置零的位置记录在首行、首列
        // 预处理出两个标记变量
        // 使用其他行与列去处理第一行与第一列，
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                    if(j == 0) colZero = true;
                    if(i == 0) rowZero = true;
                }
            }
        }
        // 对非首行、首列元素置零
        // 然后反过来使用第一行与第一列去更新其他行与列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 处理首行、首列
        // 使用两个标记变量更新第一行与第一列
        for (int i = 0; colZero && i < m; i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0;  rowZero && j < n; j++) {
            matrix[0][j] = 0;
        }
    }
}
