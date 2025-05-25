package com.answer.array;

import java.util.*;

public class Q57_Insert_Interval {
    /**
     * 插入区间
     * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，其中 intervals[i] = [starti, endi] 表示第 i 个区间的
     * 开始和结束，并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。
     * 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
     * 返回插入之后的 intervals。
     * 注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。
     * 示例 1：
     *  输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     *  输出：[[1,5],[6,9]]
     * 示例 2：
     *  输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     *  输出：[[1,2],[3,10],[12,16]]
     *  解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     */
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        int[][] res = insert1(intervals, newInterval);
        System.out.println(Arrays.deepToString(res));
    }
    /**
     * Approach 1: Linear Search 模拟
     * 由于原区间列表是无重叠的，因此只要找到与插入区间重叠的区间进行合并即可。
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> merged = new LinkedList<>();
        int index = 0;
        int len = intervals.length;
        // 步骤一：找到需要合并的区间
        while (index < len && intervals[index][1] < newInterval[0]) { //  左边的，不重叠的区间
            merged.add(intervals[index]);
            index++;
        }
        // 当前遍历是有重叠的区间
        // 步骤二： 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        while (index < len && intervals[index][0] <= newInterval[1]) { // 将所有相交区间连带上区间newInterval合并成一个大区间；
            newInterval[0] = Math.min(newInterval[0], intervals[index][0]); //左端取较小者
            newInterval[1] = Math.max(newInterval[1], intervals[index][1]); //右端取较大者
            index++;
        }
        merged.add(newInterval);
        // 步骤三：处理合并区间之后的区间
        while (index < len) {  // 右边，没重叠的区间
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
            } else { // 开始时间比结束时间小
                res[index][1] = Math.max(res[index][1], interval[1]); // 有overlapping，应该merge
            }
        }
        return Arrays.copyOf(res, index + 1);
    }
    /**
     *  同上 但使用ArrayList
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> result = new ArrayList<>();
        ArrayList<int[]> temp = new ArrayList<>();

        for(int[] i : intervals){
            temp.add(i);
        }
        temp.add(newInterval);
        // Q56的解法
        temp.sort((v1, v2)->v1[0] - v2[0]);
        int index = -1;
        for(int[] interval : temp){
            int[] last = result.size() == 0 ? new int[]{-1, -1} : result.get(result.size() - 1);
            if (result.isEmpty() || last[1] < interval[0]) {
                result.add(interval);
            } else { // 开始时间比结束时间小
                last[1] = Math.max(last[1], interval[1]);
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
}
