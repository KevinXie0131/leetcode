package com.answer.dynamic_programming;

public class Q931_Minimum_Falling_Path_Sum {
    /**
     * Approach 3: Bottom-Up Dynamic Programming (Tabulation)
     *
     * dp[row][col] = min(dp[row + 1][col - 1],
     *                    dp[row + 1][col],
     *                    dp[row + 1][col + 1]) + value of current (row, col) in matrix
     *
     * 先第一行，最小路劲和就等于自己本身
     * 其他行坐标，只需要分三种情况讨论即可
     * 首先，每行第一个坐标（i,0），路径来源只有两处（i-1,0）和（i-1,1）选择较小的来源
     * 其次，每行最后一个坐标（i,col - 1），路径来源也只有两处（i-1,col - 1）和(i - 1, col -2)选择较小的来源
     * 其余情况每个坐标(i,j)路劲来源都有三处(i-1,j-1),(i-1,j),(i - 1,j + 1)选择较小的来源
     * 答案即各种情况的总和，最后遍历最后一行 得到最小值
     */
    public int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;
        int[][] dp = new int[len][len];

        for(int i = 0; i < len; i++){
            for (int j = 0; j < len; j++){
                //首先第一行，最小路劲和就等于自己本身
                if(i == 0){
                    dp[0][j] = matrix[0][j];
                }
                //每行第一个坐标（i,0），路径来源只有两处（i-1,0）和（i-1,1）选择较小的来源
                else if(j == 0){
                    dp[i][j] = Math.min(dp[i-1][0], dp[i-1][1]) + matrix[i][j];
                }
                //行最后一个坐标（i,col - 1），路径来源也只有两处（i-1,col - 1）和(i - 1, col -2)选择较小的来源
                else if(j == len - 1){
                    dp[i][j] = Math.min(dp[i-1][len - 2], dp[i-1][len - 1]) + matrix[i][j];
                }
                else {
                    //其余情况每个坐标(i,j)路劲来源都有三处(i-1,j-1),(i-1,j),(i - 1,j + 1)选择较小的来源
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1],dp[i - 1][j]), dp[i - 1][j + 1]) + matrix[i][j];
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for(int j = 0; j < len; j++){
            result = Math.min(result, dp[len - 1][j]);
        }
        return result;
    }
}
