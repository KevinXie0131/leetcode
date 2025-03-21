package com.answer.binarysearch;

public class Q74_Search_a_2D_Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 13;
        System.out.println(searchMatrix_2(matrix ,target));
    }
    /**
     * Brute force
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == target){
                    return true;
                }
                if(matrix[i][j] > target){
                    break;
                }
            }
        }
        return false;
    }
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
     * 看成升序一维数组
     */
    public static boolean searchMatrix_1(int[][] matrix, int target) {
        int m = matrix.length;    // row
        int n = matrix[0].length; // column
        int left = 0;
        int right = m * n - 1;

        while(left <= right){
            int mid = (left + right) >>> 1;
            // n is column length
            // i = index / n
            // j = index % n
            int value = matrix[mid / n][mid % n]; // 二维索引 => 一维索引 (x, y) -> x * col + y

            if(value == target){
                return true;
            }else if(value < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return false;
    }
    /**
     * Binary Search
     */
    public static boolean searchMatrix_2(int[][] matrix, int target) {
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) {
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target);
    }

    public static int binarySearchFirstColumn(int[][] matrix, int target) {
        int low = 0, high = matrix.length - 1;
        while(low <= high){
            int mid = (low + high) >>> 1;
            int value = matrix[mid][0];
            if(value == target){
                return mid;
            }else if(value > target){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return low - 1;
    }

    public static boolean binarySearchRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}
