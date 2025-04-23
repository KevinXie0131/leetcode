package com.answer.array;

import java.util.*;

public class Q119_Pascal_Triangle_II {
    public static void main(String[] args) {
        System.out.println(getRow1(0));
        System.out.println(getRow1(1));
        System.out.println(getRow1(2));
        System.out.println(getRow_5(3));
        System.out.println(getRow1(4));
        System.out.println(getRow1(5));
    }
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
     * Based on Q118
     */
    public List<Integer> getRow_4(int rowIndex) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(row);
        }
        return res.get(rowIndex);
    }
    /**
     * 优化: 注意到对第 i+1 行的计算仅用到了第 i 行的数据，因此可以使用滚动数组的思想优化空间复杂度。
     */
    static public List<Integer> getRow_5(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; ++i) {
            List<Integer> cur = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(res.get(j - 1) + res.get(j));
                }
            }
            res = cur;
        }
        return res;
    }
    /**
     * 由 Q118. 杨辉三角 可知，每个数字只和前一行的数字有关，所以可以通过滚动数组来对结果迭代更新，这样可以把空间复杂度优化到O(n)
     */
    static public List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        for(int k = 0; k <= rowIndex; k++){
            list1.add(1);
        }
        for(int k = 0; k <= rowIndex; k++){
            list2.add(1);
        }
        ans.add(list1);
        ans.add(list2);

        for (int i = 0; i <= rowIndex; i++) {
            for (int j = 1; j < i; j++) {
                int newValue = ans.get((i - 1) % 2).get(j - 1) + ans.get((i - 1) % 2).get(j);
                ans.get(i % 2).set(j, newValue);
            }
        }
        return ans.get(rowIndex % 2);
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
