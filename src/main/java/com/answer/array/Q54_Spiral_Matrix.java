package com.answer.array;

import java.util.*;

public class Q54_Spiral_Matrix {
    public static void main(String[] args) {
        int [][] matrix = {{1,2,3, 4},{5,6,7, 8},{9,10,11,12}};

        List<Integer> r = spiralOrder(matrix);
        System.out.println(r);
    }
    /**
     * 模拟题
     * 维护未遍历数据的上下左右的边界，每次循环获取最外侧一圈边界上的数据，遍历结束后将边界向中心移动，直至边界相交结束循环。
     */
   static public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();

        int row = matrix.length;
        int col = matrix[0].length;
        int top = 0;
        int bottom = row -1;
        int left = 0;
        int right = col -1;

        while(top <= bottom && left <= right){
            for(int i = left; i <= right && top <= bottom; i++){
                result.add(matrix[top][i]);
            }
            top++; // 上边界下移
            for(int i = top; i <= bottom && left <= right; i++){
                result.add(matrix[i][right]);
            }
            right--;
            for(int i = right; i >= left && top <= bottom; i--){
                result.add(matrix[bottom][i]);
            }
            bottom--; // 下边界上移
            for(int i = bottom; i >= top && left <= right; i--){
                result.add(matrix[i][left]);
            }
            left++; // 左边界右移
        }
        return result;
    }
    /**
     * Anther form
     * 顺时针打印矩阵的顺序是 “从左向右、从上向下、从右向左、从下向上” 循环
     */
    public List<Integer> spiralOrder_1(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();

        int row = matrix.length;
        int col = matrix[0].length;
        int top = 0;
        int bottom = row -1;
        int left = 0;
        int right = col -1;

        while(top <= bottom && left <= right){
            for(int i = left; i <= right; i++){
                result.add(matrix[top][i]);
            }
            top++; // 上边界下移
            if(top > bottom) break;
            for(int i = top; i <= bottom; i++){
                result.add(matrix[i][right]);
            }
            right--;
            if(left > right) break;
            for(int i = right; i >= left; i--){
                result.add(matrix[bottom][i]);
            }
            bottom--; // 下边界上移
            if(top > bottom) break;
            for(int i = bottom; i >= top; i--){
                result.add(matrix[i][left]);
            }
            left++; // 左边界右移
            if(left > right) break;
        }
        return result;
    }
}
