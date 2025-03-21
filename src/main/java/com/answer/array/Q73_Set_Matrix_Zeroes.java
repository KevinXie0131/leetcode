package com.answer.array;

import java.util.ArrayList;
import java.util.List;

public class Q73_Set_Matrix_Zeroes {
    /**
     * in place:  operates directly on the input data structure without requiring extra space proportional to the input size.
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
                    list.add(new int[]{i ,j});
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
     * 我们首先预处理出两个标记变量，接着使用其他行与列去处理第一行与第一列，然后反过来使用第一行与第一列去更新其他行与列，
     * 最后使用两个标记变量更新第一行与第一列即可。
     */
    public void setZeroes_1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean colZero = false, rowZero = false;
        // 预处理出两个标记变量
        for (int i = 0; i < m; i++) {
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
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 然后反过来使用第一行与第一列去更新其他行与列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 使用两个标记变量更新第一行与第一列
        if (colZero) {
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
     */
    public void setZeroes_2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean colZero = false, rowZero = false;
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
        // 然后反过来使用第一行与第一列去更新其他行与列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 使用两个标记变量更新第一行与第一列
        for (int i = 0; colZero && i < m; i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0;  rowZero && j < n; j++) {
            matrix[0][j] = 0;
        }
    }
}
