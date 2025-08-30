package com.answer.dfs_bfs;

public class Q200_Number_of_Islands_1 {
    public static void main(String[] args) {
        /**
         * 1 1 0 0 0
         * 1 1 0 0 0
         * 0 0 1 0 0
         * 0 0 0 1 1
         */
        char[][] grid1 = {{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}}; // 输出：3
        System.out.println(numIslands_3(grid1));
    }
    /**
     * 简单实现并查集
     */
    static int[] connected;

    static public int numIslands_3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;

        connected = new int[row * col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    connected[i * col + j] = i * col + j;
                } else {
                    connected[i * col + j] = -1;
                }
            }
        }

        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';

                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        union(r * col + c, (r - 1) * col + c);
                    }
                    if (r + 1 < row && grid[r + 1][c] == '1') {
                        union(r * col + c, (r + 1) * col + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        union(r * col + c, r * col + c - 1);
                    }
                    if (c + 1 < col && grid[r][c + 1] == '1') {
                        union(r * col + c, r * col + c + 1);
                    }
                }
            }
        }
        int count = 0;
        for(int i = 0; i < connected.length; i++){
            if(i == connected[i]){
                count++;
            }
        }
        return count;
    }

    static public void union(int index1, int index2) {
        connected[find(index2)] = find(index1);
    }

    static public int find(int index) {
        if (connected[index] != index) {
            connected[index] = find(connected[index]);
        }
        return connected[index];
    }
}
