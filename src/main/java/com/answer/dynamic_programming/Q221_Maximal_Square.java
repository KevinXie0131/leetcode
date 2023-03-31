package com.answer.dynamic_programming;

public class Q221_Maximal_Square {
    /**
     * Approach 2: Dynamic Programming
     *
     * 若形成正方形（非单 1），以当前为右下角的视角看，则需要：当前格、上、左、左上都是 1
     */
    public int maximalSquare(char[][] matrix) {
        int max = 0;

        int r = matrix.length;
        int c = matrix[0].length;
        int[][] dp = new int[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(matrix[i][j] == '1'){
                    if(i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        // // 最小的边长 + 1，就是 [i, j] 位置的最大正方形的边长
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }

                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
