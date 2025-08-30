package com.answer.dfs_bfs;

import java.util.*;

public class Q827_Making_A_Large_Island_1 { // Hard 困难
    public static void main(String[] args) {
     /*   int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}
        };*/
        int[][] grid1 = {{0,1},{1,1}};
        System.out.println(new Q827_Making_A_Large_Island_1().largestIsland(grid1));
    }
    /**
     * 并查集 + 枚举
     * similar with Q200_Number_of_Islands
     */
    int[] connected;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean isAllIsland = true;

    public int largestIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        connected = new int[row * col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    isAllIsland = false;
                }
                if (grid[i][j] == 1) {
                    connected[i * col + j] = i * col + j;
                } else {
                    connected[i * col + j] = -1;
                }
            }
        }
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                if (grid[r][c] == 1) {
                    grid[r][c] = 0;

                    if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                        union(r * col + c, (r - 1) * col + c);
                    }
                    if (r + 1 < row && grid[r + 1][c] == 1) {
                        union(r * col + c, (r + 1) * col + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                        union(r * col + c, r * col + c - 1);
                    }
                    if (c + 1 < col && grid[r][c + 1] == 1) {
                        union(r * col + c, r * col + c + 1);
                    }
                }
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < connected.length; i++){
            if(connected[i] >= 0) {
            //    find(i);
            //    map.put(connected[i], map.getOrDefault(connected[i], 0) + 1); // cannot pass test case {{0,1},{1,1}}
                map.put(find(i), map.getOrDefault(find(i), 0) + 1);
            }
        }

        if (isAllIsland) {
            return grid.length * grid[0].length;
        }

        int max = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (connected[i * col + j] == -1) {
                    set.clear(); // 每次使用时，清空
                    int curSize = 1;// 当前水位置变更为岛屿，所以初始化为1

                    for (int[] dir : dirs) {
                        int curRow = i + dir[0];
                        int curCol = j + dir[1];

                        if (curRow < 0 || curRow >= grid.length || curCol < 0 || curCol >= grid[0].length){
                            continue;
                        }
                        int curMark = connected[curRow * col + curCol];
                        // 如果当前相邻的岛屿已经遍历过或者HashMap中不存在这个编号，继续搜索
                        if (set.contains(curMark) || !map.containsKey(curMark)) { // 添加过的岛屿不要重复添加
                            continue; // set 区分一个海洋格子相邻的两个 是不是来自同一个岛屿
                        }
                        set.add(curMark);// 标记该岛屿已经添加过
                        curSize += map.get(curMark);  // 把相邻四面的岛屿数量加起来
                    }
                    max = Math.max(max, curSize);
                }
            }
        }
        return max;
    }


    public void union (int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        }
        connected[m] = n; // 找到根节点后，x根做y根的子树，y根做x根的子树都可以
    }

    public int find(int n) {
        return n == connected[n] ? n : (connected[n] = find(connected[n]));
    }
}
