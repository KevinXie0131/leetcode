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
}
