package com.answer.array;

public class Q498_Diagonal_Traverse {
    /**
     * 对角线遍历: 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
     * 输入：mat = [[1,2,3],
     *             [4,5,6],
     *             [7,8,9]]
     * 输出：[1,2,4,7,5,3,6,8,9]
     */
    /**
     * 以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素
     * Approach 2: Simulation
     * 假设矩阵的行数为n，列数为m，那么对角线的总数为: n + m - 1
     */
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat.length == 0 || mat[0].length == 0) return new int[0];
        int n = mat.length, m = mat[0].length;
        int[] res = new int[n * m];

        for (int i = 0, idx = 0; i < n + m - 1; i++) {
            if (i % 2 == 0) {//偶数对角线
                // 如果是偶数对角线 ，确定其横坐标x，从下往上遍历，将mat[x][i - x]加入res中
                for (int x = Math.min(i, n - 1); x >= Math.max(0, i - m + 1); x--) //从下往上遍历
                    res[idx++] = mat[x][i - x];
            }
            else {            //奇数对角线
               // 如果是奇数对角线 ，确定其横坐标x，从上往下遍历，将mat[x][i - x]加入 res中
                for (int x = Math.max(0, i - m + 1); x <= Math.min(i, n - 1); x++)//从上往下遍历
                    res[idx++] = mat[x][i - x];
            }
        }
        return res;
    }
    /**
     * 根据题目有两种遍历方向开始为右上，然后方向改为左下，依次循环
     */
    public static int[] findDiagonalOrder_1(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;

        int[] answer = new int[rowLength * columnLength];
        int count = rowLength + columnLength - 1;  // 对于m*n矩阵，所有方向应该有m+n-1次
        int m = 0;
        int n = 0;
        int answerIndex = 0;

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) { // 右上方向
                while (m >= 0 && n < columnLength) {
                    answer[answerIndex] = matrix[m][n];
                    answerIndex++;
                    m--; // m，n代表横纵坐标，右上方向的移动为 m--，n++
                    n++; // 根据这两个条件的话，在正常范围内的条件为m >= 0 && n < columnLength，
                }
                if (n < columnLength) { // 越界处理 / 在右上方向的尽头有这么两种情况
                    m++;
                } else {
                    m = m + 2;
                    n--;
                }
            } else { // 左下方向
                while (m < rowLength && n >= 0) {
                    answer[answerIndex] = matrix[m][n];
                    answerIndex++;
                    m++;
                    n--;
                }
                if (m < rowLength) {
                    n++;
                }else{
                    m--;
                    n=n+2;
                }
            }
        }
        return answer;
    }
}
