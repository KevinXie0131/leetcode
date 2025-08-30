package com.answer.dfs_bfs;

import java.util.*;

public class Q417_Pacific_Atlanti_Water_Flow_1 {
    public static void main(String[] args) {
       int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
    /* pacific
        [true, true, true, true, true]
        [true, true, true, true, true]
        [true, true, true, false, false]
        [true, true, false, false, false]
        [true, false, false, false, false]*/
    /* atlantic
        [false, false, false, false, true]
        [false, false, false, true, true]
        [false, false, true, true, true]
        [true, true, true, true, true]
        [true, true, true, true, true]*/
       System.out.println(pacificAtlantic(heights));
    }
    /**
     * 多源 BFS
     */
   static public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        // 初始化两个二位boolean数组，代表两个边界
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        // 从左右边界出发进行DFS
        for (int i = 0; i < m; i++) {
           bfs(heights, i, 0, pacific); // 从最左和最右列的节点出发，向高处遍历
            bfs(heights, i, n - 1, atlantic);
        }
        // 从上下边界出发进行DFS
        for (int j = 0; j < n; j++) {
            bfs(heights, 0, j, pacific); // 从最上和最下行的节点出发，向高处遍历
            bfs(heights, m - 1, j, atlantic);
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
    // 采用 BFS 进行搜索
    static int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    static public void bfs(int[][] heights, int x, int y, boolean[][] visited) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for(int k = 0; k < 4; k++){
                int newX = cur[0] + dirs[k][0];
                int newY = cur[1] + dirs[k][1];
                if (newX < 0 || newX >= heights.length || newY < 0 || newY >= heights[0].length || visited[newX][newY]) {
                    continue;
                }
                if(heights[newX][newY] < heights[cur[0]][cur[1]]){
                    continue;
                }
                queue.offer(new int[]{newX, newY});
                visited[newX][newY] = true;
            }
        }
    }
}
