package com.answer.stack;

public class Q286_Walls_and_Gates {
    /**
     * 墙与门
     * 给定一个 m x n 的二维网格 rooms，初始化如下：
     *  -1 代表墙或障碍物
     *  0 代表一扇门
     *  INF 代表一个空的房间。我们用 2^31 - 1 = 2147483647 代表 INF（即房间里没有门，也没有墙，是一个空房间）
     * 请你填充每个空房间到最近门的距离。如果无法到达门，则填充为 INF。
     *
     * You are given a m x n 2D grid initialized with these three possible values.
     *  -1 - A wall or an obstacle.
     *  0 - A gate.
     *  INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     *
     * Example:
     * Given the 2D grid:
     * INF  -1  0  INF
     * INF INF INF  -1
     * INF  -1 INF  -1
     *   0  -1 INF INF
     * After running your function, the 2D grid should be:
     *   3  -1   0   1
     *   2   2   1  -1
     *   1  -1   2  -1
     *   0  -1   3   4
     */
}
