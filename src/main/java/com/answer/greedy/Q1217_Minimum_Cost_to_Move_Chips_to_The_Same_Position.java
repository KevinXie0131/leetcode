package com.answer.greedy;

public class Q1217_Minimum_Cost_to_Move_Chips_to_The_Same_Position {

    /**
     * Simulation
     * Approach 1: Moving Chips Cleverly
     */
    public int minCostToMoveChips(int[] position) {
        int count1 = 0;
        int count2 = 0;
        //奇数一堆 偶数一堆
        for (int i : position) {
            if ((i % 2) == 0) {
                count1++;
            } else {
                count2++;
            }
        }
        return Math.min(count1, count2);
    }
}
