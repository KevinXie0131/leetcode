package com.answer.array;

import java.util.*;

public class Q118_Pascal_Triangle {
    /**
     * 杨辉三角
     */
    public static void main(String[] args) {
        System.out.println(generate(4));
    }

    static public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) { // 首位默认为1
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }

            ret.add(row);
        }
        return ret;
    }
    /**
     * Approach 1: Dynamic Programming
     */
    public static List<List<Integer>> generate_2(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        int[][] dp = new int[numRows][numRows];
        // Base case; first row is always [1].
        dp[0][0] = 1;

        for(int i = 1; i < numRows; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0) {
                    dp[i][j] = 1;  // The first row element is always 1.
                } else {
                    // Each triangle element (other than the first and last of each row)
                    // is equal to the sum of the elements above-and-to-the-left and above-and-to-the-right.
                    // The last row element is always 1.
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }
        for(int[] l : dp){
            System.out.println(Arrays.toString(l));
        }

        for (int i = 0; i < dp.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < dp.length; j++) {
                if(dp[i][j] == 0) break;
                tmp.add(dp[i][j]);
            }
            result.add(new ArrayList<>(tmp));
        }

        return result;
    }
}
