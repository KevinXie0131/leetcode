package com.answer.dynamic_programming;

public class Q63_Unique_Paths_II {
    /**
     * 不同路径 II
     * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
     * 返回机器人能够到达右下角的不同路径数量。
     * 示例 1：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     *
     */
    /**
     * Approach 1: Dynamic Programming
     * 有障碍的话，其实就是标记对应的dp table（dp数组）保持初始值(0)就可以了。
     * 时间复杂度：O(n × m)，n、m 分别为obstacleGrid 长度和宽度
     * 空间复杂度：O(n × m)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        // 如果加上障碍物的话，对应的位置，路径数为0
        // 而且要注意，障碍物后面的位置也是无法到达的，路径数也应该为 0。
        for(int i = 0; i < m && obstacleGrid[i][0] == 0; i++){ // 一旦遇到obstacleGrid[i][0] == 1的情况就停止dp[i][0]的赋值1的操作
            dp[i][0] = 1;
        }
        for(int j = 0; j < n && obstacleGrid[0][j] == 0; j++){
            dp[0][j] = 1;
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                // 障碍点，是无法抵达的点，是到达方式数为 0 的点
                // 是无法从它这里走到别的点的点，即无法提供给别的点方式数的点
                if(obstacleGrid[i][j] == 1){ // 因为有了障碍，(i, j)如果就是障碍的话应该就保持初始状态（初始状态为0）
                    continue;
                }
                dp[i][j] = dp[i-1][j] + dp[i][j-1];  // 当(i, j)没有障碍的时候，再推导dp[i][j]
            }
        }
        return dp[m-1][n-1];
    }
    /**
     * 动态规划 另一种形式
     * 时间复杂度 O(nm)  空间复杂度也是 O(nm)
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化
        for(int i = 0; i < m; i++) {
            // 存在障碍物
            if(obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++) {
            // 存在障碍物
            if(obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        // 遍历
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
    /**
     * 滚动数组思想优化
     * 由于这里 f(i, j) 只与 f(i - 1, j) 和 f(i, j - 1)相关，我们可以运用「滚动数组思想」把空间复杂度优化称 O(m)。
     * 当我们定义的状态在动态规划的转移方程中只和某几个状态相关的时候，就可以考虑这种优化方法，目的是给空间复杂度「降维」
     * 空间复杂度：O(m)
     */
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }

        return f[m - 1];
    }
    /**
     * 空间优化版本
     */
    public int uniquePathsWithObstacles_6(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];

        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j != 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
