package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q64_Minimum_Path_Sum {
    public static void main(String[] args) {
        int[][] grid = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum_3(grid));
    }
    /**
     *  方法一：最小路径和 暴力搜索 Time Limit Exceeded
     */
    public int minPathSum_0(int[][] grid) {
        return minPathSumDFS(grid, grid.length - 1, grid[0].length - 1);
    }
    public int minPathSumDFS(int[][] grid, int i, int j) {
        // 若为左上角单元格，则终止搜索
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        // 若行列索引越界，则返回 +∞ 代价
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        // 计算从左上角到 (i-1, j) 和 (i, j-1) 的最小路径代价
        int up = minPathSumDFS(grid, i - 1, j);
        int left = minPathSumDFS(grid, i, j - 1);
        // 返回从左上角到 (i, j) 的最小路径代价
        return Math.min(left, up) + grid[i][j];
    }
    /**
     * 方法二：最小路径和：记忆化搜索
     */
    static public int minPathSum_1(int[][] grid) {
        int[][] mem = new int[grid.length][grid[0].length];
        for(int [] m : mem){
            Arrays.fill(m, -1);
        }
        return minPathSumDFSMem(grid, mem, grid.length - 1, grid[0].length - 1);
    }
    static int minPathSumDFSMem(int[][] grid, int[][] mem, int i, int j) {
        // 若为左上角单元格，则终止搜索
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        // 若行列索引越界，则返回 +∞ 代价
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        // 若已有记录，则直接返回
        if (mem[i][j] != -1) {
            return mem[i][j];
        }
        // 左边和上边单元格的最小路径代价
        int up = minPathSumDFSMem(grid, mem, i - 1, j);
        int left = minPathSumDFSMem(grid, mem, i, j - 1);
        // 记录并返回左上角到 (i, j) 的最小路径代价
        mem[i][j] = Math.min(left, up) + grid[i][j];
        return mem[i][j];
    }
    /**
     * Approach 2: Dynamic Programming 2D 动态规划
     *
     * 创建二维数组 dp，与原始网格的大小相同，dp[i][j] i,j) 位置的最小路径和。
     * 显然 dp[0][0]=grid[0][0]。对于 dp 中的其余元素，通过以下状态转移方程计算元素值。
     *
     * i > 0 && j = 0: dp[i][0]=dp[i−1][0]+grid[i][0]
     * i = 0 && j > 0: dp[0][j]=dp[0][j−1]+grid[0][j]
     * i > 0 && j > 0: dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]
     * 最后得到 dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和
     */
    public static int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0]; // 初始化

        for(int i = 1; i < r; i++){ // 初始化最左边的列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int j = 1; j < c; j++){ // 初始化最上边的行
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for(int i = 1; i < r; i++){ // 推导出 dp[m-1][n-1]
            for(int j = 1; j < c; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];  // 通过状态转移方程
            }
        }
        return dp[r - 1][c - 1];
    }
    /**
     * 以直接使用原来的数组，节省一点空间
     */
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i ==0 && j == 0) {
                    continue;
                }
                // 累加距离
                if(i == 0){
                    grid[i][j] = grid[i][j] + grid[i][j-1]; // 第一行，只能从左边过来。
                } else if(j == 0) {
                    grid[i][j] = grid[i][j] + grid[i-1][j]; // 第一列，只能从上面过来
                } else {
                    grid[i][j] = grid[i][j] + Math.min(grid[i-1][j], grid[i][j-1]);    // 通过状态转移方程
                }
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * Approach 3: Dynamic Programming 1D 空间优化
     * dp(j)=grid(i,j)+min(dp(j),dp(j+1))
     */
    public int minPathSum_2(int[][] grid) {
        int[] dp = new int[grid[0].length];

        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {

                if(i == grid.length - 1 && j != grid[0].length - 1){
                    dp[j] = grid[i][j] +  dp[j + 1];
                }
                else if(j == grid[0].length - 1 && i != grid.length - 1) {
                    dp[j] = grid[i][j] + dp[j];
                }
                else if(j != grid[0].length - 1 && i != grid.length - 1) {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                }
                else {
                    dp[j] = grid[i][j];
                }
            }
        }
        return dp[0];
    }
    /**
     * 空间优化后的动态规划
     * 由于每个格子只与其左边和上边的格子有关，因此我们可以只用一个单行数组来实现dp表
     * 请注意，因为数组 dp 只能表示一行的状态，所以我们无法提前初始化首列状态，而是在遍历每行时更新它
     */
    static int minPathSum_3(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        // 初始化 dp 表
        int[] dp = new int[m];
        // 状态转移：首行
        dp[0] = grid[0][0];
        for (int j = 1; j < m; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
            System.out.println(Arrays.toString(dp));
        }
        // 状态转移：其余行
        for (int i = 1; i < n; i++) {
            // 状态转移：首列
            dp[0] = dp[0] + grid[i][0];
            // 状态转移：其余列
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                System.out.println(Arrays.toString(dp));
            }
        }
        return dp[m - 1];
    }
}
