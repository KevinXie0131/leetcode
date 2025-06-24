package com.answer.hashmap;

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
}
