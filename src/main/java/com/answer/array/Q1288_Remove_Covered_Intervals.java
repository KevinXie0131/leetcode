package com.answer.array;

import java.util.*;

public class Q1288_Remove_Covered_Intervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{1,4},{3,4}};
        System.out.println(removeCoveredIntervals(intervals));
    }
    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1]-a[1]:a[0]-b[0]);
        int len = intervals.length;
        int count = len;
        int right = intervals[0][1];

        for(int i = 1; i < len; i++){
            if(intervals[i][1] <= right){
                count--;
            }else{
                right = Math.max(intervals[i][1], right);
            }
        }

        return count;
    }
    /**
     * Approach 1: Greedy Algorithm
     */
    public int removeCoveredIntervals_1(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // Sort by start point.
                // If two intervals share the same start point,
                // put the longer one to be the first.
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });

        int count = 0;
        int end, prev_end = 0;
        for (int[] curr : intervals) {
            end = curr[1];
            // if current interval is not covered
            // by the previous one
            if (prev_end < end) {
                ++count;
                prev_end = end;
            }
        }
        return count;
    }
}
