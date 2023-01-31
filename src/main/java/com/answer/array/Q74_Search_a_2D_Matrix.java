package com.answer.array;

public class Q74_Search_a_2D_Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1}};
        int target = 2;
        System.out.println(searchMatrix_1(matrix ,target));
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
            }
        }
        return false;
    }
    /**
     * Binary Search
     */
    public static boolean searchMatrix_1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while(left <= right){
            int mid = (left + right) >>> 1;
            int value = matrix[mid / n][mid % n];

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
}
