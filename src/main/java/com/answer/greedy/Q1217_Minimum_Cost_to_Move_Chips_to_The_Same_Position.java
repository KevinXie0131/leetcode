package com.answer.greedy;

public class Q1217_Minimum_Cost_to_Move_Chips_to_The_Same_Position {

    /**
     * Greedy
     * Approach 1: Moving Chips Cleverly
     * 先理解题意：这里的chips数组里存放的是第i个筹码存放的位置，不是第i个位置存放了多少个筹码
     *
     * 为了保证总开销值最小，对于每一次的移动选择，我们如果能选择开销为0的移动方式（方式一），就绝对不选择开销为1的移动方式（方式二）。
     * 因此我们尽量通过方式一先将尽量多的筹码移动到一起，在剩下的筹码无法再通过方式一来移动到同一位置的时候，尽量使用最少次数的方式二来移动。
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
