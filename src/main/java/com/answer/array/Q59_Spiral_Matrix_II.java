package com.answer.array;

import com.template.Print2dArray;

import java.util.Arrays;

public class Q59_Spiral_Matrix_II {
    public static void main(String[] args) {
        int[][] result = generateMatrix(5);
    //    System.out.println(Arrays.deepToString(result));
        Print2dArray.print2D(result);
    }

    public static int[][] generateMatrix(int n) {
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

    // This method has problem. It doesn't work.
    public static int[][] generateMatrix_0(int n) {
        int[][] result = new int[n][n];
        int row = n;
        int col = n;
        int offset = 1;
        int top = 0, bottom = row - offset, left = 0, right = col - offset;
        int count = 1;

        while(top <= bottom && left <= right){
            for(int i = left; i < right && top <= bottom; i++){
                result[top][i] = count++;
            }
           // top++;
          //  left++;
            for(int i = top; i < bottom && left <= right; i++){
                result[i][right] = count++;
            }
            top++;
         // right--;
            for(int i = right; i > left && top <= bottom; i--){
                result[bottom][i] = count++;
            }
            right--;
         //   bottom--;
            for(int i = bottom; i > top && left <= right; i--){
                result[i][left] = count++;
            }
            bottom--;
        //    left++;
        }
        if (n % 2 == 1) { // n 为奇数时，单独处理矩阵中心的值
            result[n/2][n/2] = count;
        }
        return result;
    }

    public int[][] generateMatrix_1(int n) {
        int[][] nums = new int[n][n];
        int startX = 0, startY = 0;  // 每一圈的起始点
        int offset = 1;
        int count = 1;  // 矩阵中需要填写的数字
        int loop = 1; // 记录当前的圈数
        int i, j; // j 代表列, i 代表行;

        while (loop <= n / 2) {

            // 顶部
            // 左闭右开，所以判断循环结束时， j 不能等于 n - offset
            for (j = startY; j < n - offset; j++) {
                nums[startX][j] = count++;
            }

            // 右列
            // 左闭右开，所以判断循环结束时， i 不能等于 n - offset
            for (i = startX; i < n - offset; i++) {
                nums[i][j] = count++;
            }

            // 底部
            // 左闭右开，所以判断循环结束时， j != startY
            for (; j > startY; j--) {
                nums[i][j] = count++;
            }

            // 左列
            // 左闭右开，所以判断循环结束时， i != startX
            for (; i > startX; i--) {
                nums[i][j] = count++;
            }
            startX++;
            startY++;
            offset++;
            loop++;
        }
        if (n % 2 == 1) { // n 为奇数时，单独处理矩阵中心的值
            nums[startX][startY] = count;
        }
        return nums;
    }

}
