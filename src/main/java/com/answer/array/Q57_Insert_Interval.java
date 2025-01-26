package com.answer.array;

import java.util.*;

public class Q57_Insert_Interval {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        int[][] res = insert1(intervals, newInterval);
        System.out.println(Arrays.deepToString(res));
    }
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
    /**
     * 将newInterval加入intervals，再用Q56的解法
     */
    static public int[][] insert1(int[][] intervals, int[] newInterval) {
        int[][] intervalsCopy = new int[intervals.length + 1][2];
        intervalsCopy = Arrays.copyOf(intervals, intervalsCopy.length );
        intervalsCopy[intervalsCopy.length - 1] = newInterval;

        Arrays.sort(intervalsCopy, (v1, v2)->v1[0] - v2[0]);
        int[][] res = new int[intervalsCopy.length][2];
        int index = -1;
        for(int[] interval : intervalsCopy){
            if(index == -1 || res[index][1] < interval[0]){ // 开始时间比结束时间大
                index++;
                res[index] = interval; // 没有overlapping, 直接放入
            }else{ // 开始时间比结束时间小
                res[index][1] = Math.max(res[index][1], interval[1]); // 有overlapping，应该merge
            }
        }
        return Arrays.copyOf(res, index + 1);
    }
}
