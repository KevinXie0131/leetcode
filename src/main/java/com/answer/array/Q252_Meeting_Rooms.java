package com.answer.array;

import java.util.Arrays;

public class Q252_Meeting_Rooms {
    /**
     * Brute force
     */
    public boolean canAttendMeetings_1(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (overlap(intervals[i], intervals[j]))
                    return false;
            }
        }
        return true;
    }
    public static boolean overlap(int[] i1, int[] i2) {
        return ((i1[0] >= i2[0] && i1[0] < i2[1]) || (i2[0] >= i1[0] && i2[0] < i1[1]));
    }
    public static boolean overlap_1(int[] i1, int[] i2) {
        return (Math.min(i1[1], i2[1]) >
                Math.max(i1[0], i2[0]));
    }
    /**
     * Approach 2: Sorting
     */
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < intervals[i - 1][1]){
                return false;
            }
        }

        return true;
    }
}
