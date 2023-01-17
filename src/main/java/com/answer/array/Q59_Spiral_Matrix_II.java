package com.answer.array;

public class Q59_Spiral_Matrix_II {

    public int[][] generateMatrix(int n) {
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
