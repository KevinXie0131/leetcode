package com.answer.dynamic_programming;

import java.util.List;

public class Q120_Triangle {
    /**
     * 解法一：递归 Time Limit Exceeded
     * 暴力解法: DFS
     *
     * Greedy可能在有些情况下不能到达最优解
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return  dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        //将任一点到底边的最小路径和，转化成了与该点相邻两点到底边的最小路径和中的较小值，再加上该点本身的值
        return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }
    /**
     * 解法二：递归 + 记忆化
     * 在解法一的基础上，定义了二维数组进行记忆化。
     * 时间复杂度：O(N2)，N 为三角形的行数。
     * 空间复杂度：O(N2)，N 为三角形的行数。
     */
    Integer[][] memo;
    public int minimumTotal_1(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return  dfs1(triangle, 0, 0);
    }

    private int dfs1(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(dfs1(triangle, i + 1, j), dfs1(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }
    /**
     * 解法三：动态规划
     * 定义二维 dp 数组，将解法二中「自顶向下的递归」改为「自底向上的递推」。
     * DP[i, j]是从底部走到(i, j)最小路径和
     *
     * 时间复杂度：O(N2)，N 为三角形的行数。
     * 空间复杂度：O(N2)，N 为三角形的行数。
     */
    public int minimumTotal_2(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size()];
        for(int i = 0; i < triangle.size(); i++){
            dp[triangle.size() - 1][i] = triangle.get(triangle.size() - 1).get(i);
        }
        // 从三角形的最后一行开始递推。
        for(int i = triangle.size() - 2; i >= 0; i--){
            for(int j = 0; j <= i; j++){ // 注意： j <= i 或者  j <=  triangle.get(i).size() - 1
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
    /**
     * 另一种形式
     */
    public int minimumTotal_4(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
    /**
     * 空间优化: 在实际递推中我们发现，计算 dp[i][j] 时，只用到了下一行的 dp[i+1][j] 和 dp[i+1][j+1]。因此 dp 数组不需要定义 N 行，只要定义 1 行就可以啦。
     * 空间复杂度：O(N)
     */
    public int minimumTotal_5(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1]; // 需要n + 1
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <=  triangle.get(i).size() - 1; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
