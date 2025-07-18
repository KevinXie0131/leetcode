package com.answer.array;

import java.util.*;

public class Q118_Pascal_Triangle {
    /**
     * 杨辉三角: 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     * 输入: numRows = 5
     * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     */
    /**
     * 杨辉三角
     * 把杨辉三角的每一排左对齐：
     * [1]
     * [1,1]
     * [1,2,1]
     * [1,3,3,1]
     * [1,4,6,4,1]
     * 每个数字由上一行的当前列数字和上一行的左侧数字相加得到，模拟这个过程就可以得到每一行的结果了。
     */
    public static void main(String[] args) {
        System.out.println(generate_2(4));
    }

    static public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) { // 首位默认为1
                    row.add(1);
                } else {
                    // 其余数字，等于左上方的数，加上正上方的数，即 c[i][j]=c[i−1][j−1]+c[i−1][j]
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }
    // Another form
    public List<List<Integer>> generate_4(int numRows) {
        List<List<Integer>> c = new ArrayList<>(numRows); // 预分配空间
        c.add(Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1); // 预分配空间
            row.add(1);
            for (int j = 1; j < i; j++) {
                // 左上方的数 + 正上方的数
                row.add(c.get(i - 1).get(j - 1) + c.get(i - 1).get(j));
            }
            row.add(1);
            c.add(row);
        }
        return c;
    }
    /**
     * Approach 1: Dynamic Programming 动态规划
     * 第 i 行共有 i + 1 个格子，并且第 0 和第 i 个格子都是 1。从第 2 行开始，第 1 到第 i - 1 个格子的值都可由上一行求出
     * 确定递归函数：dp[i][j] = dp[i-1][j-1] + dp[i-1][j]；
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
    /**
     * another form
     */
    public static List<List<Integer>> generate_3(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        int[][] dp = new int[numRows][numRows];
        dp[0][0] = 1;

        for(int i = 1; i < numRows; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0 || i == j) { // 第 i 行共有 i + 1 个格子，并且第 0 和第 i 个格子都是 1。
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                tmp.add(dp[i][j]);
            }
            result.add(new ArrayList<>(tmp));
        }

        return result;
    }
}
