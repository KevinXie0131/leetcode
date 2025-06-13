package com.answer.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Q435_Non_overlapping_Intervals {
    /**
     * 无重叠区间
     * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
     * 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
     * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * 输出: 1
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     */
    public static void main(String[] args) {
    //    int[][] intervals = {{1,2},{1,2},{1,2}};
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};

        System.out.println(eraseOverlapIntervals_4(intervals));
    }
    /**
     * 贪心算法：按照右边界排序，从左向右记录非交叉区间的个数。最后用区间总数减去非交叉区间的个数就是需要移除的区间个数了。、
     * 本题其实和Q452.用最少数量的箭引爆气球 非常像 (弓箭的数量就相当于是非交叉区间的数量，只要把弓箭那道题目代码里射爆气球的判断条件加个等号（认为[0，1][1，2]不是相邻区间），然后用总区间数减去弓箭数量 就是要移除的区间数量了。)
     */
    public int eraseOverlapIntervals_8(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> {
            return Integer.compare(a[1],b[1]); // 右边界排序
        });
        int count = 1; // 记录非交叉区间的个数
        int end = intervals[0][1]; // 记录区间分割点
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
    /**
     * 另一种形式: 左边界排序, 可以精简一下， 用 intervals[i][1] 替代 pre变量，只判断 重叠情况就好
     */
    public int eraseOverlapIntervals_6(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> {
            return Integer.compare(a[0],b[0]);
        });
        int count = 1;  // 记录非交叉区间的个数
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < intervals[i-1][1]){  //重叠情况
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]); // 记录区间分割点
                continue;
            }else{
                count++;
            }
        }
        return intervals.length - count;
    }
    /**
     * 另一种形式: 按左边排序，不管右边顺序。相交的时候取最小的右边。
     */
    public int eraseOverlapIntervals_7(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> {
            return Integer.compare(a[0],b[0]); // 改为左边界排序
        });
        int count  = 0; // 注意这里从0开始，因为是记录重叠区间
        int pre = intervals[0][1]; // 记录区间分割点
        for(int i = 1; i < intervals.length; i++) {
            if(pre > intervals[i][0]) { // 重叠情况
                count++;
                pre = Math.min(pre, intervals[i][1]);
            } else {  // 无重叠的情况
                pre = intervals[i][1];
            }
        }
        return count;
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