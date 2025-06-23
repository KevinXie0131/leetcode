package com.answer.array;

import java.util.Arrays;

public class Q252_Meeting_Rooms {
    /**
     * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束时间 intervals[i] = [starti, endi] ，请你判断一个人是否能够参加这里面的全部会议。
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * determine if a person could attend all meetings.
     *  Example 1:
     *      Input: [[0,30],[5,10],[15,20]]
     *      Output: false
     *      解释: 存在重叠的会议，所以一个人无法参加所有会议。
     *  Example 2:
     *      Input: [[7,10],[2,4]]
     *      Output: true
     */
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
