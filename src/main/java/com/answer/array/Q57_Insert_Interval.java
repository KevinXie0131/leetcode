package com.answer.array;

import java.util.*;

public class Q57_Insert_Interval {
    /**
     * Approach 1: Linear Search
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> merged = new LinkedList<>();
        int index = 0;
        int len = intervals.length;

        while(index < len && intervals[index][1] < newInterval[0]){
            merged.add(intervals[index]);
            index++;
        }
        while(index < len && intervals[index][0] <= newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            index++;
        }
        merged.add(newInterval);
        while(index < len){
            merged.add(intervals[index]);
            index++;
        }
        return merged.toArray(new int[0][]);
    }
}
