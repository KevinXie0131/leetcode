package com.answer.array;

import java.util.Arrays;

public class Q56_Merge_Intervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(intervals);
        for(int[] r : res){
            System.out.println(Arrays.toString(r));
        }
    }
    /**
     * Approach 2: Sorting
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2)->v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int index = -1;
        for(int[] interval : intervals){
            if(index == -1 || res[index][1] < interval[0]){
                index++;
                res[index] = interval;
            }else{
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }
}