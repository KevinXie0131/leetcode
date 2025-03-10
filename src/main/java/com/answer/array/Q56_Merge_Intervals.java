package com.answer.array;

import java.util.*;

public class Q56_Merge_Intervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(intervals);
        System.out.println(Arrays.deepToString(res));
    }
    /**
     * 贪心：和452. 用最少数量的箭引爆气球，435. 无重叠区间 类似
     * 先排序，让所有的相邻区间尽可能的重叠在一起，按左边界，或者右边界排序都可以, 本题是判断区间重贴后要进行区间合并
     * 按照左边界从小到大排序之后，如果 intervals[i][0] <= intervals[i - 1][1] 即intervals[i]的左边界 <= intervals[i - 1]的右边界，则一定有重叠。（本题相邻区间也算重贴，所以是<=）
     * 合并区间后左边界和右边界，作为一个新的区间，加入到result数组里就可以了。如果没有合并就把原区间加入到result数组。
     */
    public int[][] merge_5(int[][] intervals) {
        List<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));  //按照左边界排序

        int start = intervals[0][0]; //initial start 是最小左边界
        int rightmostRightBound = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > rightmostRightBound) { //如果左边界大于最大右边界
                res.add(new int[]{start, rightmostRightBound});   //加入区间 并且更新start
                start = intervals[i][0];
                rightmostRightBound = intervals[i][1];
            } else {
                rightmostRightBound = Math.max(rightmostRightBound, intervals[i][1]); //更新最大右边界
            }
        }
        res.add(new int[]{start, rightmostRightBound});
        return res.toArray(new int[res.size()][]);
    }
    /**
     * 另一种形式
     */
    public int[][] merge_6(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        res.add(intervals[0]);     // 第一个区间就可以放进结果集里，后面如果重叠，在result上直接合并

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= res.getLast()[1]) {  // 发现重叠区间
                int start = res.getLast()[0];  // 合并区间，只更新右边界就好，因为result.back()的左边界一定是最小值，因为我们按照左边界排序的
                int end = Math.max(intervals[i][1], res.getLast()[1]);
                res.removeLast();
                res.add(new int[]{start, end});
            } else {
                res.add(intervals[i]);  // 区间不重叠
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    /**
     * Approach 2: Sorting 排序法(按照开始时间)
     * Time complexity: O(logn) Space complexity: O(n)
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2)->v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int index = -1;
        for(int[] interval : intervals){
            if(index == -1 || res[index][1] < interval[0]){ // 开始时间比结束时间大
                index++;
                res[index] = interval; // 没有overlapping, 直接放入
            }else{ // 开始时间比结束时间小
                res[index][1] = Math.max(res[index][1], interval[1]); // 有overlapping，应该merge
            }
        }
        return Arrays.copyOf(res, index + 1); // 去除空余array
    }
    /**
     * 排序法
     */
    public static int[][] merge_1(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) {
            return res.toArray(new int[0][]);
        }
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return res.toArray(new int[0][]); //toArray方法中的参数只是为了说明返回数组的元素类型，并不需要开辟空间
    }
    /**
     * 排序法, 使用LinkedList
     */
    public static int[][] merge_2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
     //   return merged.toArray(new int[merged.size()][]);
        return merged.toArray(new int[0][]);
    }
    /**
     * 排序法, 使用ArrayList
     */
    public int[][] merge5(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ArrayList<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            }
            else {
                merged.get(merged.size() - 1)[1]  = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        // All the following can work
        // return merged.toArray(new int[0][2]);
        // return merged.toArray(new int[0][]);
        return merged.toArray(new int[merged.size()][2]);
    }
}