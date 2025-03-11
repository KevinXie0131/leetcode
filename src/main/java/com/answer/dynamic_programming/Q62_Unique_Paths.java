package com.answer.dynamic_programming;


import java.util.Arrays;

public class Q62_Unique_Paths {

    public static void main(String[] args) {
        int result = uniquePaths2(3, 5);
        System.out.println(result);
    }
    /**
     * Approach 1: Dynamic Programming 动态规划
     * 时间复杂度：O(m × n)
     * 空间复杂度：O(m × n)
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n]; // dp[i][j] ：表示从（0 ，0）出发，到(i, j) 有dp[i][j]条不同的路径。

        for(int i = 0; i < m; i++){
            dp[i][0] = 1; // 初始状态
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1; // 初始状态
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1]; // 状态转移方程
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m-1][n-1]; // 终止状态
    }
    /**
     * 动态规划 另一种形式
     */
    public static int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1; // 初始状态

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i >= 1 && i < m){
                    dp[i][j] = dp[i][j] + dp[i - 1][j]; // 状态转移方程
                }
                if(j >= 1 && j < n){
                    dp[i][j] = dp[i][j] + dp[i][j - 1]; // 状态转移方程
                }

            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m-1][n-1]; // 终止状态
    }
    /**
     * 动态规划 另一种形式
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    public int uniquePaths3(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // 这种其实是不符合条件的，不过我们设定初始值 dp[0][0] = 1
                if(i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        // 返回所有可能的结果
        return dp[m-1][n-1];
    }
    /**
     * 一维数组（也可以理解是滚动数组）
     * 时间复杂度：O(m × n)
     * 空间复杂度：O(n)
     */
    public int uniquePaths_5(int m, int n) {
        int[] dp = new int[n]; // 在二维dp数组中，当前值的计算只依赖正上方和正左方，因此可以压缩成一维数组。
        Arrays.fill(dp, 1); // 初始化，第一行只能从正左方跳过来，所以只有一条路径。
        for (int j = 1; j < m; j++) {
            // 第一列也只有一条路，不用迭代，所以从第二列开始
            for (int i = 1; i < n; i++) {
                dp[i] += dp[i - 1]; // dp[j] = dp[j] (正上方)+ dp[j - 1] (正左方)
            }
        }
        return dp[n - 1];
    }
    /**
     * 深搜: 走过的路径可以抽象为一棵二叉树，而叶子节点就是终点 Time Limit Exceeded
     * 此时问题就可以转化为求二叉树叶子节点的个数
     * 时间复杂度为O(2^(m + n - 1) - 1)，可以看出，这是指数级别的时间复杂度，是非常大
     */
    public int uniquePaths_6(int m, int n) {
        return dfs(1, 1, m, n);
    }
    int dfs(int i, int j, int m, int n) {
        if (i > m || j > n) return 0; // 越界了
        if (i == m && j == n) return 1; // 找到一种方法，相当于找到了叶子节点
        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }
}
