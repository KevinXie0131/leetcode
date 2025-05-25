package com.answer.array;

import com.template.Print2dArray;

import java.util.Arrays;

public class Q59_Spiral_Matrix_II {
    /**
     * 螺旋矩阵 II: 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     */
    public static void main(String[] args) {
        int[][] result = generateMatrix_0(5);
    //    System.out.println(Arrays.deepToString(result));
        Print2dArray.print2D(result);
    }
    // 这个方法类似 Q54 Spiral Matrix
    // 左闭右闭
    // 本题与 Q54. 螺旋矩阵 解法相同，只需将读取操作变为赋值操作即可。
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int row = n;
        int col = n;
        int top = 0, bottom = row -1, left = 0, right = col -1;
        int count = 1;

        while(top <= bottom && left <= right){
            for(int i = left; i <= right && top <= bottom; i++){
                result[top][i] = count++;
            }
            top++;
            for(int i = top; i <= bottom && left <= right; i++){
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
    // Another form
    public static int[][] generateMatrix_3(int n) {
        int[][] result = new int[n][n];
        int row = n;
        int col = n;
        int top = 0, bottom = row -1, left = 0, right = col -1;
        int count = 1;
        int num = n * n; // 迭代终止值
        // 从左到右 从上到下 从右到左 从下到上 填入顺序循环
        while(count <= num){  // 使用count <= num而不是left < right || top < bottom作为迭代条件，是为了解决当n为奇数时，矩阵中心数字无法在迭代过程中被填充的问题。
            for(int i = left; i <= right; i++){
                result[top][i] = count++;
            }
            top++;
            for(int i = top; i <= bottom; i++){
                result[i][right] = count++;
            }
            right--;
            for(int i = right; i >= left; i--){
                result[bottom][i] = count++;
            }
            bottom--;
            for(int i = bottom; i >= top; i--){
                result[i][left] = count++;
            }
            left++;
        }

        return result;
    }
    // 左闭右开
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

            for(int i = top; i < bottom && left <= right; i++){
                result[i][right] = count++;
            }

            for(int i = right; i > left && top <= bottom; i--){
                result[bottom][i] = count++;
            }

            for(int i = bottom; i > top && left <= right; i--){
                result[i][left] = count++;
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        if (n % 2 == 1) { // n 为奇数时，单独处理矩阵中心的值
            result[n/2][n/2] = count;
        }
        if(n == 1){
            result[0][0] = 1;
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
