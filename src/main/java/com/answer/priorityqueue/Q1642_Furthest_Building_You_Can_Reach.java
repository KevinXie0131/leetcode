package com.answer.priorityqueue;

public class Q1642_Furthest_Building_You_Can_Reach {
    /**
     * 可以到达的最远建筑
     * 一个整数数组 heights ，表示建筑物的高度。另有一些砖块 bricks 和梯子 ladders 。
     * 你从建筑物 0 开始旅程，不断向后面的建筑物移动，期间可能会用到砖块或梯子。
     * 当从建筑物 i 移动到建筑物 i+1（下标 从 0 开始 ）时：
     *  如果当前建筑物的高度 大于或等于 下一建筑物的高度，则不需要梯子或砖块
     *  如果当前建筑的高度 小于 下一个建筑的高度，您可以使用 一架梯子 或 (h[i+1] - h[i]) 个砖块
     * 如果以最佳方式使用给定的梯子和砖块，返回你可以到达的最远建筑物的下标（下标 从 0 开始 ）。
     *
     * given an integer array heights representing the heights of buildings, some bricks, and some ladders.
     * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
     * While moving from building i to building i+1 (0-indexed),
     *  If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
     *  If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
     * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
     *
     * 示例 1：
     *  输入：heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
     *  输出：4
     *  解释：从建筑物 0 出发，你可以按此方案完成旅程：
     *      - 不使用砖块或梯子到达建筑物 1 ，因为 4 >= 2
     *      - 使用 5 个砖块到达建筑物 2 。你必须使用砖块或梯子，因为 2 < 7
     *      - 不使用砖块或梯子到达建筑物 3 ，因为 7 >= 6
     *      - 使用唯一的梯子到达建筑物 4 。你必须使用砖块或梯子，因为 6 < 9
     *      无法越过建筑物 4 ，因为没有更多砖块或梯子。
     * Explanation: Starting at building 0, you can follow these steps:
     *      - Go to building 1 without using ladders nor bricks since 4 >= 2.
     *      - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
     *      - Go to building 3 without using ladders nor bricks since 7 >= 6.
     *      - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
     *      It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
     */
}
