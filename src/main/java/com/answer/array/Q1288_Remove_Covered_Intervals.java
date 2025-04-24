package com.answer.array;

import java.util.*;

public class Q1288_Remove_Covered_Intervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{1,4},{3,4}};
       // int[][] intervals = {{1,4},{3,6},{2,8}};
        System.out.println(removeCoveredIntervals(intervals));
    }
    /**
     * Brute force
     * 枚举: 对于列表中的每个区间 p，我们可以枚举其余的所有区间，并依次判断区间 p 是否被某个区间所覆盖。
     */
    public static int removeCoveredIntervals_0(int[][] intervals) {
        int n = intervals.length;
        int ans = n;
        for (int i = 0; i < intervals.length; ++i) {
            for (int j = 0; j < intervals.length; ++j) {
                if (i != j && intervals[j][0] <= intervals[i][0] && intervals[i][1] <= intervals[j][1]) {
                    --ans;
                    break;
                }
            }
        }
        return ans;
    }
    /**
     * 排序 + 遍历
     */
    public static int removeCoveredIntervals(int[][] intervals) {
        // 将区间按照左端点为第一关键字且递增、右端点为第二关键字且递减进行排序, 这样我们就很好地处理了左端点相同的情况。
        // 那么对于左端点相同的呢？ 我们应该尽量让更长的在前面，因为更长的会合并掉左端点相同的更短的。
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int len = intervals.length;
        int count = len;
        int right = intervals[0][1];

        for(int i = 1; i < len; i++){
            if(intervals[i][1] <= right){ // 会被覆盖
                count--;
            }else{
                right = Math.max(intervals[i][1], right);
                // right = intervals[i][1]; // can work too
            }
        }
        return count;
    }
    /**
     * Approach 1: Greedy Algorithm
     * The idea of greedy algorithm is to pick the locally optimal move at each step, which would lead to the globally optimal solution.
     */
    public static int removeCoveredIntervals_1(int[][] intervals) {
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
