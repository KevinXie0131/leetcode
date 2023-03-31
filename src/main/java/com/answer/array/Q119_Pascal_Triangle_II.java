package com.answer.array;

import java.util.*;

public class Q119_Pascal_Triangle_II {
    /**
     * Approach 1: Brute Force Recursion
     */
    private int getNum(int row, int col) {
        if (row == 0 || col == 0 || row == col) {
            return 1;
        }
        return getNum(row - 1, col - 1) + getNum(row - 1, col);
    }
    public List<Integer> getRow_0(int rowIndex) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            ans.add(getNum(rowIndex, i));
        }
        return ans;
    }
    /**
     * Dynamic Programming
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();

        int[][] dp = new int[rowIndex + 1][rowIndex + 1];
        dp[0][0] = 1;

        for(int i = 1; i < rowIndex + 1; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0) {
                    dp[i][j] = 1;
                } else {

                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        for (int j = 0; j < dp.length; j++) {
            if(dp[rowIndex][j] == 0) break;
            result.add(dp[rowIndex][j]);
        }

        return result;
    }
    /**
     * Approach 3: Memory-efficient Dynamic Programming
     * pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j]
     */
    public List<Integer> getRow_3(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>(rowIndex + 1) {
                    {
                        add(1);
                    }
                };

        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
        }
        return row;
    }
}
