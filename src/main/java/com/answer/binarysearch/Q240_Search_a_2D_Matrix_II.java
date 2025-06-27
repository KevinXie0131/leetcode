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
     * 矩阵的每行从左到右是升序， 每列从上到下也是升序，在矩阵中查找某个数。
     * Z 字形查找
     * 若当前矩阵的元素值 > target，则向上移动一行列不变，即 matrix[i][j] 变为 matrix[i - 1][j]，继续进行比较。
     * 若当前矩阵的元素值 < target，则向右移动一列行不变，即 matrix[i][j] 变为 matrix[i][j + 1]，继续进行比较。
     *
     * 时间复杂度：O(m+n)
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1; // 从右上角开始
        while (x < m && y >= 0) { // 还有剩余元素
            if (matrix[x][y] == target) { // 找到 target
                return true;
            }
            if (matrix[x][y] > target) {// 这一行剩余元素全部大于 target，排除
                --y; // 如果 matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 y 列的元素都是严格大于 target 的，因此我们可以将它们全部忽略，即将 y 减少 1；
            } else {// 这一列剩余元素全部小于 target，排除
                ++x; // 如果 matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 x 行的元素都是严格小于 target 的，因此我们可以将它们全部忽略，即将 x 增加 1。
            }
        }
        return false;
    }
    /**
     * Binary Search
     * 时间复杂度：O(mlogn)
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
