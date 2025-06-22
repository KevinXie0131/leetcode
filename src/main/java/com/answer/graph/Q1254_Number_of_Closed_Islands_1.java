package com.answer.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Q1254_Number_of_Closed_Islands_1 {
    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}};
        System.out.println(closedIsland(grid));
    }
    /**
     * 并查集
     * 遍历矩阵，如果当前位置是在边界上，我们将其与虚拟节点 m×n 连接。如果当前位置是陆地，我们将其与下方和右方的陆地连接。
     * 接着，我们再次遍历矩阵，对于每一块陆地，如果其根节点就是本身，那么答案加一。
     */
    static int[] connected;

    static public int closedIsland(int[][] grid) { // cannot pass all test cases
        int m = grid.length;
        int n = grid[0].length;

        connected = new int[m * n + 1];
        for(int i = 0; i < m * n + 1; i++) {
            connected[i] = i;
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    union(connected, i * n + j, m * n);
                }
                if (grid[i][j] == 0) {
                    if (i + 1 < m && grid[i + 1][j] == 0) {
                        union(connected, i * n + j, (i + 1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j + 1] == 0) {
                        union(connected, i * n + j, i * n + j + 1);
                    }
                    if (i - 1 > 0 && grid[i - 1][j] == 0) {
                        union(connected, i * n + j, (i - 1) * n + j);
                    }
                    if (j - 1 > 0 && grid[i][j - 1] == 0) {
                        union(connected, i * n + j, i * n + j - 1);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < m - 1; ++i) {
            for (int j = 1; j < n - 1; ++j) {
                find(connected, i * n + j);
                if (grid[i][j] == 0 && connected[i * n + j] == i * n + j) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    static public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    static public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
