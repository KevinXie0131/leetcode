package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test39_Spiral_Matrix_1 {
    public static void main(String[] args) {
        int n = 3;
        int[][] result = new int[n][n];
        result = spiralOrderII(n);
        for(int[] res : result){
            System.out.println(Arrays.toString(res));
        }
    }

    public static int[][] spiralOrderII(int n) {
        int[][] result = new int[n][n];
        int row = n;
        int col = n;
        int top = 0, bottom = row -1, left = 0, right = col -1;
        int count = 1;

        while(top <= bottom && left <= right){
            for(int i = left; i<= right && top <= bottom; i++){
                result[top][i] = count++;
            }
            top++;
            for(int i = top; i<= bottom && left <= right; i++){
                result[i][right] = count++;
            }
            right--;
            for(int i = right; i >= left && top <= bottom; i--){
                result[bottom][i] = count++;
            }
            bottom--;
            for(int i = bottom; i >= top && left <= right; i--){
                result[i][left] = count++;
            }
            left++;
        }

        return result;
    }
}
