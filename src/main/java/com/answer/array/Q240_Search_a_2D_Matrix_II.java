package com.answer.array;

public class Q240_Search_a_2D_Matrix_II {

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
