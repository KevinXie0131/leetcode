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
     * Approach 1: Linear Search 模拟
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> merged = new LinkedList<>();
        int index = 0;
        int len = intervals.length;
        // 步骤一：找到需要合并的区间
        while(index < len && intervals[index][1] < newInterval[0]){ //  左边的，不重叠的区间
            merged.add(intervals[index]);
            index++;
        }
        // 当前遍历是有重叠的区间
        //  步骤二： 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        while(index < len && intervals[index][0] <= newInterval[1]){ // 将所有相交区间连带上区间newInterval合并成一个大区间；
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]); //左端取较小者
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]); //右端取较大者
            index++;
        }
        merged.add(newInterval);
        // 步骤三：处理合并区间之后的区间
        while(index < len){  // 右边，没重叠的区间
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
        intervalsCopy = Arrays.copyOf(intervals, intervalsCopy.length);
        intervalsCopy[intervalsCopy.length - 1] = newInterval;
       /* int[][] newIntervals = new int[intervals.length + 1][2];
        for (int i = 0; i < intervals.length; ++i) {
            newIntervals[i] = intervals[i];
        }
        newIntervals[intervals.length] = newInterval; */
        // Q56的解法
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
