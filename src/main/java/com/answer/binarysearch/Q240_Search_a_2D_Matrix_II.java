package com.answer.binarysearch;

public class Q240_Search_a_2D_Matrix_II {
    /**
     * 搜索二维矩阵 II
     * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
     *  Integers in each row are sorted in ascending from left to right.
     *  Integers in each column are sorted in ascending from top to bottom.
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *  每行的元素从左到右升序排列。
     *  每列的元素从上到下升序排列。
     *
     * 输入：matrix = [[1,4,7,11,15],
     *                [2,5,8,12,19],
     *                [3,6,9,16,22],
     *                [10,13,14,17,24],
     *                [18,21,23,26,30]]
     *  target = 5
     *  输出：true
     *  target = 20
     *  输出：false
     */
    /**
     * Approach 4: Search Space Reduction
     * Optimized Brute force
     */
    public boolean searchMatrix_0(int[][] matrix, int target) {
        int rows = matrix.length - 1, columns = 0;
        while (rows >= 0 && columns < matrix[0].length) {
            int num = matrix[rows][columns];
            if (num == target) {
                return true;
            } else if (num > target) {
                rows--;
            } else {
                columns++;
            }
        }
        return false;
    }
    /**
     * Binary Search
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][matrix[i].length - 1] < target){
                continue;
            }
            int val = binarySearch(matrix[i], target);
            if(val != -1){
                return true;
            }
        }
        return false;
    }
    public int binarySearch(int[] num, int target) {
        int left = 0, right = num.length - 1;

        while(left <= right){
            int mid = (left + right) >>> 1;
            int value = num[mid];

            if(value == target){
                return mid;
            }else if(value < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
}
