package com.answer.greedy;

public class Q1217_Minimum_Cost_to_Move_Chips_to_The_Same_Position {
    /**
     * 玩筹码
     * 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
     * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
     *  position[i] + 2 或 position[i] - 2 ，此时 cost = 0
     *  position[i] + 1 或 position[i] - 1 ，此时 cost = 1
     * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
     */
    /**
     * Greedy
     * Approach 1: Moving Chips Cleverly
     * 先理解题意：这里的chips数组里存放的是第i个筹码存放的位置，不是第i个位置存放了多少个筹码
     *
     * 为了保证总开销值最小，对于每一次的移动选择，我们如果能选择开销为0的移动方式（方式一），就绝对不选择开销为1的移动方式（方式二）。
     * 因此我们尽量通过方式一先将尽量多的筹码移动到一起，在剩下的筹码无法再通过方式一来移动到同一位置的时候，尽量使用最少次数的方式二来移动。
     *
     * 题目说明了往左移 2 格和往右移 2 格花费的代价为 0
     * 那把所有索引偶数位置上的元素都移到 0 索引位置上花费的代价为 0
     * 同理把所有索引奇数位置上的元素都移到 1 索引位置上花费的代价为 0
     * 那这个题目就转换成 0 位置上元素的个数和 1 位置上元素的个数哪个小就往另外一个位置上移动
     * 所以结果就是奇数堆小就返回奇数堆的数量，偶数堆小就返回偶数堆的数量
     */
    public int minCostToMoveChips(int[] position) {
        int count1 = 0;
        int count2 = 0;
        //奇数一堆 偶数一堆
        for (int i : position) {
            if ((i % 2) == 0) {  // if ((i & 1) == 0) {
                count1++;
            } else {
                count2++;
            }
        }
        return Math.min(count1, count2);
    }
}
