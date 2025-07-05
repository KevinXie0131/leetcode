package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q542_01_Matrix {
    /**
     * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
     * If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.
     * Example 1:
     *  Input: s = "abcdefg", k = 2
     *  Output: "bacdfeg"
     * Example 2:
     *  Input: s = "abcd", k = 2
     *  Output: "bacd"
     * 反转字符串 II
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     */
    /**
     * 广度优先搜索
     * 广度优先搜索可以找到从起点到其余所有点的 最短距离
     */
    public int[][] updateMatrix(int[][] mat) { // 找出每个 1 到最近 0 的距离 -> 多源 BFS, 首先需要把多个源点都入队
        int m = mat.length;
        int n = mat[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(mat[i][j] == 0){ // “多源”，多个起点，多个0
                    queue.offer(new int[]{i, j});  // 首先将所有的 0 都入队
                } else {// 将 1 的位置设置成 MIN_VALUE，表示该位置是 未被访问过的
                    mat[i][j] = Integer.MIN_VALUE;  //标记非零元素为负，和遍历后设定的正数距离加以区分
                }
            }
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 1;
        while(!queue.isEmpty()){
            int size = queue.size();     //对当前队列中所有零元素遍历，所有元素向四周走一步
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();   //获取队列中的元素位置
                for(int k = 0; k < 4; k++){
                    int x = cur[0] + dirs[k][0];   //向四个方向依次走一步
                    int y = cur[1] + dirs[k][1];
                    if(x < 0 || x >= m || y < 0 || y >= n || mat[x][y] != Integer.MIN_VALUE){   //如果超出矩阵范围，或者遇见零元素及设置过距离step的元素则跳过，只对未遍历到的操作
                        continue;
                    }

                    mat[x][y] = step;
                    queue.offer(new int[]{x, y});
                }
            }
            step++;  //下次遍历到的-1元素相比前一次距离step加1
        }
        return mat;
    }
    /**
     * 如果不需要确定当前遍历到了哪一层，BFS 模板如下。
     * while queue 不空：
     *     cur = queue.pop()
     *     for 节点 in cur的所有相邻节点：
     *         if 该节点有效且未访问过：
     *             queue.push(该节点)
     *
     * 如果要确定当前遍历到了哪一层，BFS 模板如下。
     * level = 0
     * while queue 不空：
     *     size = queue.size()
     *     while (size --) {
     *         cur = queue.pop()
     *         for 节点 in cur的所有相邻节点：
     *             if 该节点有效且未被访问过：
     *                 queue.push(该节点)
     *     }
     *     level ++;
     */
}
