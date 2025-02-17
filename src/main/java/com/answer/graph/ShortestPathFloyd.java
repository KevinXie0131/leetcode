package com.answer.graph;

public class ShortestPathFloyd {
    /**
     * 本题是经典的多源最短路问题, 即 求多个起点到多个终点的多条最短路径。
     * 在这之前我们讲解过的都是单源最短路，即只能有一个起点。
     *
     * 新的最短路算法Floyd 算法,对边的权值正负没有要求，都可以处理。
     * Floyd算法核心思想是动态规划。
     * floyd算法的时间复杂度相对较高，适合 稠密图且源点较多的情况。 如果 源点少，其实可以 多次dijsktra 求源点到终点。
     * 时间复杂度依然是 O(n^3)
     */
    public static void main(String[] args) {
        int[][] input = {{2, 3, 4},
                        {3, 6, 6},
                        {4, 7, 8}};
        floyd(7, 3, input );
    }
    // public static int MAX_VAL = Integer.MAX_VALUE;
    public static int MAX_VAL = 10005; // 边的最大距离是10^4(不选用Integer.MAX_VALUE是为了避免相加导致数值溢出)

    // 基于三维数组的Floyd算法
    public static void floyd (int num , int row,  int[][] input) {
        // 输入控制
        System.out.println("1.输入N M");
        int n = num;
        int m = row;

        System.out.println("2.输入M条边");

        // 1. dp定义（grid[i][j][k] 节点i到节点j 可能经过节点K（k∈[1,n]））的最短路径
        int[][][] grid = new int[n + 1][n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    grid[i][j][k] = grid[j][i][k] = MAX_VAL; // 其余设置为最大值
                }
            }
        }
        /**
         * 2、确定递推公式
         * 在上面的分析中我们已经初步感受到了递推的关系。
         * 我们分两种情况：
         *          节点i 到 节点j 的最短路径经过节点k
         *          节点i 到 节点j 的最短路径不经过节点k
         * 对于第一种情况，grid[i][j][k] = grid[i][k][k - 1] + grid[k][j][k - 1]
         *      节点i 到 节点k 的最短距离 是不经过节点k，中间节点集合为[1...k-1]，所以 表示为grid[i][k][k - 1]
         *      节点k 到 节点j 的最短距离 也是不经过节点k，中间节点集合为[1...k-1]，所以表示为 grid[k][j][k - 1]
         * 第二种情况，grid[i][j][k] = grid[i][j][k - 1]
         *      如果节点i 到 节点j的最短距离 不经过节点k，那么 中间节点集合[1...k-1]，表示为 grid[i][j][k - 1]
         *      因为我们是求最短路，对于这两种情况自然是取最小值。
         *          即： grid[i][j][k] = min(grid[i][k][k - 1] + grid[k][j][k - 1]， grid[i][j][k - 1])
         */
        // 2. dp 推导：grid[i][j][k] = min{grid[i][k][k-1] + grid[k][j][k-1], grid[i][j][k-1]}
        while (m-- > 0) {
            int u = input[m][0];
            int v = input[m][1];
            int weight = input[m][2];
            // 注意这里是双向图
            grid[u][v][0] = grid[v][u][0] = weight; // 初始化（处理k=0的情况） 3. dp初始化
        }
        // 4. dp推导：floyd 推导 - 确定遍历顺序
        // 从递推公式：grid[i][j][k] = min(grid[i][k][k - 1] + grid[k][j][k - 1]， grid[i][j][k - 1]) 可以看出，我们需要三个for循环，分别遍历i，j 和k
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i][j][k] = Math.min(grid[i][k][k - 1] + grid[k][j][k - 1], grid[i][j][k - 1]);
                }
            }
        }
        System.out.println("3.输入[起点-终点]计划个数");
        int x = 2;
        int[] srcInput = new int[]{2, 3};
        int[] dstInput = new int[]{3, 4};
        int index = 0;
        System.out.println("4.输入每个起点src 终点dst");

        while (x-- > 0) {
            int src = srcInput[index];
            int dst = dstInput[index];
            index++;
            // 根据floyd推导结果输出计划路径的最小距离
            if (grid[src][dst][n] == MAX_VAL) {
                System.out.println("-1");
            } else {
                System.out.println(grid[src][dst][n]);
            }
        }
    }
}
