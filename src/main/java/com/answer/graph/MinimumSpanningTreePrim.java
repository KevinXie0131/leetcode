package com.answer.graph;

import java.util.Arrays;

public class MinimumSpanningTreePrim {
    /**
     * 最小生成树- 对于一个带权连通图，生成树不同，树中各边上权值总和也不同，权值总和最小的生成树则称为图的最小生成树。
     *            即：以最小的成本（边的权值）将图中所有节点链接到一起
     *    Example: 在各大城市中建设通信网络，如下图所示，每个圆圈代表一座城市，而边上的数字代表了建立通信连接的价格。
     *              那么，请问怎样才能以最小的价格使各大城市能直接或者间接地连接起来呢？)
     *    prim算法
     *    kruskal算法
     *
     * 最小生成树是所有节点的最小连通子图，即：以最小的成本（边的权值）将图中所有节点链接到一起。
     * 图中有n个节点，那么一定可以用n-1条边将所有节点连接到一起。那么如何选择这n-1条边就是最小生成树算法的任务所在。
     *
     * prim算法是从节点的角度采用贪心的策略每次寻找距离最小生成树最近的节点并加入到最小生成树中。
     * prim算法核心就是三步，我称为prim三部曲，大家一定要熟悉这三步，代码相对会好些很多：
     *      第一步，选距离生成树最近节点
     *      第二步，最近节点加入生成树
     *      第三步，更新非生成树节点到生成树的距离（即更新minDist数组）- minDist数组用来记录每一个节点距离最小生成树的最近距离
     *                                                             minDist数组也就是记录的是最小生成树所有边的权值。
     *
     * （示例中节点编号是从1开始，所以为了让大家看的不晕，minDist数组下标我也从1开始计数，下标0就不使用了，这样下标和节点标号就可以对应上了，避免大家搞混）
     * 时间复杂度为O(n^2)，其中n为节点数量
     */
    public static void main(String[] args) {
        int[][] input = {{1, 2, 1},
                        {1, 3, 1},
                        {1, 5, 2},
                        {2, 6, 1},
                        {2, 4, 2},
                        {2, 3, 2},
                        {3, 4, 1},
                        {4, 5, 1},
                        {5, 6, 2},
                        {5, 7, 1},
                        {6, 7, 1}};
        prim(7, 11, input );
    }

    public static void prim(int vertex , int edge,  int[][] input) {
        int v = vertex ;
        int e = edge;
        // 初始化邻接矩阵，所有值初始化为一个大值，表示无穷大
        int[][] grid = new int[v + 1][v + 1];
        for (int i = 0; i <= v; i++) {
            Arrays.fill(grid[i], 10001); // 填一个默认最大值，题目描述val最大为10000
        }
        // 读取边的信息并填充邻接矩阵
        for (int i = 0; i < e; i++) {
            int x = input[i][0];
            int y = input[i][1];
            int k = input[i][2];
            // 因为是双向图，所以两个方向都要填上
            grid[x][y] = k;
            grid[y][x] = k;
        }
        // 所有节点到最小生成树的最小距离
        int[] minDist = new int[v + 1];
        Arrays.fill(minDist, 10001);
        // 记录节点是否在树里
        boolean[] isInTree = new boolean[v + 1];
        // 使用一维数组就可以记录。parent[节点编号] = 节点编号，这样就把一条边记录下来了。
        int[] parent = new int[v + 1];
        Arrays.fill(parent, -1);

        // Prim算法主循环
        for (int i = 1; i < v; i++) { // 我们只需要循环 n-1次，建立 n - 1条边，就可以把n个节点的图连在一起
            // 1、prim三部曲，第一步：选距离生成树最近节点
            int cur = -1; // 选中哪个节点 加入最小生成树
            int minVal = Integer.MAX_VALUE;
            // 选择距离生成树最近的节点
            for (int j = 1; j <= v; j++) { // 1 - v，顶点编号，这里下标从1开始
                //  选取最小生成树节点的条件：
                //  （1）不在最小生成树里
                //  （2）距离最小生成树最近的节点
                if (!isInTree[j] && minDist[j] < minVal) {
                    minVal = minDist[j];
                    cur = j;
                }
            }
            System.out.println("最近节点cur加入生成树:" + cur + " 最小距离:" + minVal);
            // 2、prim三部曲，第二步：最近节点（cur）加入生成树
            isInTree[cur] = true; // 将最近的节点加入生成树
            // 3、prim三部曲，第三步：更新非生成树节点到生成树的距离（即更新minDist数组）
            // cur节点加入之后， 最小生成树加入了新的节点，那么所有节点到 最小生成树的距离（即minDist数组）需要更新一下
            // 由于cur节点是新加入到最小生成树，那么只需要关心与 cur 相连的 非生成树节点 的距离 是否比 原来 非生成树节点到生成树节点的距离更小了呢
            for (int j = 1; j <= v; j++) {// 更新非生成树节点到生成树的距离
                // 更新的条件：
                // （1）节点是 非生成树里的节点
                // （2）与cur相连的某节点的权值 比 该某节点距离最小生成树的距离小
                // 很多录友看到自己 就想不明白什么意思，其实就是 cur 是新加入 最小生成树的节点，那么 所有非生成树的节点距离生成树节点的最近距离 由于 cur的新加入，需要更新一下数据了
                if (!isInTree[j] && grid[cur][j] < minDist[j]) {
                    minDist[j] = grid[cur][j];
                    parent[j] = cur; // 记录最小生成树的边 （注意数组指向的顺序很重要）
                }
            }
            System.out.println("所有最小距离:" + Arrays.toString(minDist));
        }
        // 统计结果
        int result = 0;
        for (int i = 2; i <= v; i++) {  // 不计第一个顶点，因为统计的是边的权值，v个节点有 v-1条边
            result += minDist[i];
        }
        System.out.println(result);
        // 输出 最小生成树边的链接情况
        System.out.println("最小生成树边的链接");
        for (int i = 1; i <= v; i++) {
            System.out.println(i + " -> " + parent[i]);
        }
    }
}
