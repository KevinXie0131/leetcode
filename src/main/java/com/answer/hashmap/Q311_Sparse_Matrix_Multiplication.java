package com.answer.hashmap;

import java.util.*;

public class Q311_Sparse_Matrix_Multiplication {
    /**
     * Given two sparse matrices A and B, return the result of AB.
     * You may assume that A's column number is equal to B's row number.
     * Example:
     * Input:
     * A = [
     *   [ 1, 0, 0],
     *   [-1, 0, 3]
     * ]
     * B = [
     *   [ 7, 0, 0 ],
     *   [ 0, 0, 0 ],
     *   [ 0, 0, 1 ]
     * ]
     * Output:
     *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
     * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
     *                   | 0 0 1 |
     * 稀疏矩阵的乘法
     * 给你两个稀疏矩阵 mat1 和 mat2，返回它们的乘积（矩阵乘法）。
     * 你可以假设 mat1 的列数等于 mat2 的行数。
     * 示例 1：
     * 输入：mat1 = [[1,0,0],[−1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
     * 输出：[[7,0,0],[-7,0,3]]
     * 解释：
     *  第一个矩阵：
     *  1 0 0
     *  -1 0 3
     *  第二个矩阵：
     *  7 0 0
     *  0 0 0
     *  0 0 1
     *  乘积结果：
     *  7 0 0
     *  -7 0 3
     */
    public static void main(String[] args) {
        int[][] mat1 = {{1, 0, 0},{-1,0,3}};
        int[][] mat2 = {{7,0,0},{0,0,0},{0,0,1}};
        System.out.println(Arrays.deepToString(multiply2(mat1, mat2)));
    }
    /**
     * 暴力求解: 3层 for 循环
     */
    static public int[][] multiply(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][B[0].length];
        int i, j, k, sum;
        for(i = 0; i < A.length; ++i) {
            for (j = 0; j < B[0].length; ++j) {
                sum = 0;
                for (k = 0; k < A[0].length; ++k) {
                    sum += A[i][k] * B[k][j];
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
    /**
     * 因为是稀疏矩阵，如果A[i][k]为0，直接跳过最内层的计算，不影响最后结果。
     */
    static public int[][] multiply1(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][B[0].length];
        int i, j, k;
        for (i = 0; i < A.length; ++i) {
            for (k = 0; k < A[0].length; ++k) {
                if (A[i][k] != 0) {
                    for (j = 0; j < B[0].length; ++j) {
                        if (B[k][j] != 0) {
                            ans[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return ans;
    }
    /**
     * use a Map (HashMap) to store only non-zero entries for each matrix, further optimizing for very sparse inputs.
     * mat1Map 用于存储 mat1 的非零元素，按行索引存储每一行的非零列及对应值。
     * mat2Map 用于存储 mat2 的非零元素，按行索引存储每一行的非零列及对应值（即同原矩阵的列，乘法时用的是 mat2 的行）。
     * 最终只遍历所有非零元素进行乘法和累加，适合大规模稀疏矩阵。
     */
    static public int[][] multiply2(int[][] mat1, int[][] mat2) {
        int m = mat1.length, n = mat1[0].length, p = mat2[0].length;
        int[][] result = new int[m][p];
        // Store non-zero entries of mat1 in a map: row -> (col -> value)
        Map<Integer, Map<Integer, Integer>> mat1Map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < n; k++) {
                if (mat1[i][k] != 0) {
                    mat1Map.computeIfAbsent(i, x -> new HashMap<>()).put(k, mat1[i][k]);
                }
            }
        }
        // Store non-zero entries of mat2 in a map: col -> (row -> value)
        Map<Integer, Map<Integer, Integer>> mat2Map = new HashMap<>();
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < p; j++) {
                if (mat2[k][j] != 0) {
                    mat2Map.computeIfAbsent(j, x -> new HashMap<>()).put(k, mat2[k][j]);
                }
            }
        }
        // Multiply only non-zero entries
        for (int i : mat1Map.keySet()) {
            Map<Integer, Integer> row = mat1Map.get(i);
            for (int j : mat2Map.keySet()) {
                Map<Integer, Integer> col = mat2Map.get(j);
                int sum = 0;
                for (int k : row.keySet()) {
                    if (col.containsKey(k)) {
                        sum += row.get(k) * col.get(k);
                    }
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}
