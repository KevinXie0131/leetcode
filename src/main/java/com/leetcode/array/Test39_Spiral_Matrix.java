package com.leetcode.array;

import java.util.*;

public class Test39_Spiral_Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> result = spiralOrder(matrix);
        System.out.println(result);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();

        int row = matrix.length;
        int col = matrix[0].length;
        int top = 0, bottom = row -1, left = 0, right = col -1;

        while(top <= bottom && left <= right){
            for(int i = left; i<= right && top <= bottom; i++){
                result.add(matrix[top][i]);
            }
            top++;
            for(int i = top; i<= bottom && left <= right; i++){
                result.add(matrix[i][right]);
            }
            right--;
            for(int i = right; i >= left && top <= bottom; i--){
                result.add(matrix[bottom][i]);
            }
            bottom--;
            for(int i = bottom; i >= top && left <= right; i--){
                result.add(matrix[i][left]);
            }
            left++;
        }

        return result;
    }
}
