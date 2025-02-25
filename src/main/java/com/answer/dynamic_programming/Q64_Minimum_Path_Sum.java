package com.answer.dynamic_programming;

public class Q64_Minimum_Path_Sum {
    public static void main(String[] args) {
        int[][] grid = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum(grid));
    }
    /**
     * Approach 2: Dynamic Programming 2D
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
                if(i == 0) {
                    // 第一行，只能从左边过来。
                    grid[i][j] = grid[i][j] + grid[i][j-1];
                } else if(j == 0) {
                    // 第一列，只能从上面过来
                    grid[i][j] = grid[i][j] + grid[i-1][j];
                } else {
                    // 通过状态转移方程
                    grid[i][j] = grid[i][j] + Math.min(grid[i-1][j], grid[i][j-1]);
                }
            }
        }
        return grid[m-1][n-1];
    }

    /**
     * Approach 3: Dynamic Programming 1D
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
}
