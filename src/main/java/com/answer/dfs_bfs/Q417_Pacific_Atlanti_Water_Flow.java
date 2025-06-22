package com.answer.dfs_bfs;

import java.util.*;

public class Q417_Pacific_Atlanti_Water_Flow {
    /**
     * 太平洋大西洋水流问题
     * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
     *  The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
     * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度(height above sea level) 。
     * 岛上雨水较多，如果相邻单元格的高度 小于或等于(less than or equal to)当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
     * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
     *
     * 输入: heights = [ [1,2,2,3,5],
     *                   [3,2,3,4,4],
     *                   [2,4,5,3,1],
     *                   [6,7,1,4,5],
     *                   [5,1,1,2,4]]
     * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
     * Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
     * [0,4]: [0,4] -> Pacific Ocean
     *        [0,4] -> Atlantic Ocean
     * [1,3]: [1,3] -> [0,3] -> Pacific Ocean
     *        [1,3] -> [1,4] -> Atlantic Ocean
     * [1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
     *        [1,4] -> Atlantic Ocean
     * [2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
     *        [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
     * [3,0]: [3,0] -> Pacific Ocean
     *        [3,0] -> [4,0] -> Atlantic Ocean
     * [3,1]: [3,1] -> [3,0] -> Pacific Ocean
     *        [3,1] -> [4,1] -> Atlantic Ocean
     * [4,0]: [4,0] -> Pacific Ocean
     *        [4,0] -> Atlantic Ocean
     * Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
     */
    public static void main(String[] args) {
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(pacificAtlantic1(heights));
    }
    /**
     * DFS优化
     * 那么我们可以 反过来想，从第一组边界上的节点 逆流而上，将遍历过的节点都标记上。
     * 同样从第二组边界的边上节点 逆流而上，将遍历过的节点也标记上。
     * 然后两方都标记过的节点就是既可以流太平洋也可以流大西洋的节点。
     *
     * dfs函数搜索的过程 时间复杂度是 O(n * m)
     * 关键看主函数，那么每次dfs的时候，上面还是有for循环的。
     * 本题整体的时间复杂度其实是： 2 * n * m + n * m ，所以最终时间复杂度为 O(n * m)
     *
     * 空间复杂度为：O(n * m) 这个就不难理解了。开了几个 n * m 的数组
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        // 初始化两个二位boolean数组，代表两个边界
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // 从左右边界出发进行DFS
        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, pacific, Integer.MIN_VALUE); // 从最左和最右列的节点出发，向高处遍历
            dfs(heights, i, n - 1, atlantic, Integer.MIN_VALUE);
        }
        // 从上下边界出发进行DFS
        for (int j = 0; j < n; j++) {
            dfs(heights, 0, j, pacific, Integer.MIN_VALUE); // 从最上和最下行的节点出发，向高处遍历
            dfs(heights, m - 1, j, atlantic, Integer.MIN_VALUE);
        }
        // 当两个边界二维数组在某个位置都为true时，符合题目要求
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {   // 如果这个节点，从第一组边界和第二组边界出发都遍历过，就是结果
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    // 采用 DFS 进行搜索
    public void dfs(int[][] heights, int x, int y, boolean[][] visited, int preH) {
        // 遇到边界或者访问过的点，直接返回
        if (x < 0 || x >= heights.length || y < 0 || y >= heights[0].length || visited[x][y]) {
            return;
        }
        // 不满足水流入条件的直接返回
        if (heights[x][y] < preH) { // 注意：这里是从低向高遍历
            return;
        }
        // 满足条件，设置为true，表示可以从边界到达此位置
        visited[x][y] = true;

        // 向下一层继续搜索
        dfs(heights, x + 1, y, visited, heights[x][y]);
        dfs(heights, x - 1, y, visited, heights[x][y]);
        dfs(heights, x, y + 1, visited, heights[x][y]);
        dfs(heights, x, y - 1, visited, heights[x][y]);
    }
    /**
     * Anther form, not using Integer.MIN_VALUE
     * The folloing if condition can be commented, but it gets slower after if is commented.
     * if(!pacific[i][0])
     * if(!atlantic[i][n - 1])
     * if(!pacific[0][j])
     * if(!atlantic[m - 1][j])
     */
    static public List<List<Integer>> pacificAtlantic1(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n]; // 初始化两个二位boolean数组，代表两个边界
        boolean[][] atlantic = new boolean[m][n];
        // 从左右边界出发进行DFS
        for (int i = 0; i < m; i++) {
           if(!pacific[i][0]) dfs1(heights, i, 0, pacific); // 从最左和最右列的节点出发，向高处遍历
           if(!atlantic[i][n - 1])  dfs1(heights, i, n - 1, atlantic);
        }
        // 从上下边界出发进行DFS
        for (int j = 0; j < n; j++) {
            if(!pacific[0][j]) dfs1(heights, 0, j, pacific); // 从最上和最下行的节点出发，向高处遍历
            if(!atlantic[m - 1][j]) dfs1(heights, m - 1, j, atlantic);
        }
        // 当两个边界二维数组在某个位置都为true时，符合题目要求
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {   // 如果这个节点，从第一组边界和第二组边界出发都遍历过，就是结果
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    // 采用 DFS 进行搜索
    static int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

   static  public void dfs1(int[][] heights, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        // 向下一层继续搜索
        for(int k = 0; k < 4; k++) {
            int newX = x + dirs[k][0];
            int newY = y + dirs[k][1];
            if (newX < 0 || newX >= heights.length || newY < 0 || newY >= heights[0].length || visited[newX][newY] ) {
                continue;
            }
            if(heights[newX][newY] < heights[x][y]){
                continue;
            }
            dfs1(heights, newX, newY, visited);
        }
    }
}
