package com.answer.dfs_bfs;

import java.util.*;

public class Q417_Pacific_Atlanti_Water_Flow {
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
}
