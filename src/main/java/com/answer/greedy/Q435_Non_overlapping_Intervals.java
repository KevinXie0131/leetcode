package com.answer.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Q435_Non_overlapping_Intervals {
    public static void main(String[] args) {
    //    int[][] intervals = {{1,2},{1,2},{1,2}};
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};

        System.out.println(eraseOverlapIntervals_4(intervals));
    }
    /**
     * Greedy
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, (a, b)-> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < intervals[i - 1][1]){
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
                count++;
            }
        }
        return count;
    }
    /**
     * Approach #1 Brute Force [Time Limit Exceeded]
     */
    public static int eraseOverlapIntervals_1(int[][] intervals) {
        Arrays.sort(intervals, new myComparator());
        return erase_Overlap_Intervals(-1, 0, intervals);
    }
    public static int erase_Overlap_Intervals(int prev, int curr, int[][] intervals) {
        if (curr == intervals.length) {
            return 0;
        }
        int taken = Integer.MAX_VALUE, nottaken;
        if (prev == -1 || intervals[prev][1] <= intervals[curr][0]) {
            taken = erase_Overlap_Intervals(curr, curr + 1, intervals);
        }
        nottaken = erase_Overlap_Intervals(prev, curr + 1, intervals) + 1;
        return Math.min(taken, nottaken);
    }
    /**
     * Approach #5 Using Greedy Approach based on end points
     */
    public static int eraseOverlapIntervals_2(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) ->  a[1] - b[1]);
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
    /**
     * Approach #4 Using Greedy Approach based on starting points
     */
    public static int eraseOverlapIntervals_4(int[][] intervals) {
        Arrays.sort(intervals, (a, b) ->  a[1] - b[1]);
        int prev = 0, count = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[prev][1] > intervals[i][0]) {
                if (intervals[prev][1] > intervals[i][1]) {
                    prev = i;
                }
                count++;
            } else {
                prev = i;
            }
        }
        return count;
    }
}

class myComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return a[1] - b[1];
    }
}