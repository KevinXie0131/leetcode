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
    /**
     * https://leetcode.cn/problems/non-overlapping-intervals/solutions/297887/chuan-shang-yi-fu-wo-jiu-bu-ren-shi-ni-liao-lai-2/
     * 【多人运动变种】穿上衣服我就不认识你了？来聊聊最长上升子序列
     */
    public static void main(String[] args) {
    //    int[][] intervals = {{1,2},{1,2},{1,2}};
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};

        System.out.println(eraseOverlapIntervals_4(intervals));
    }
    /**
     * Approach #5 Using Greedy Approach based on end points
     * 贪心算法：按照右边界排序，从左向右记录非交叉区间的个数。最后用区间总数减去非交叉区间的个数就是需要移除的区间个数了。、
     * 本题其实和Q452.用最少数量的箭引爆气球 非常像 (弓箭的数量就相当于是非交叉区间的数量，只要把弓箭那道题目代码里射爆气球的判断条件加个等号（认为[0，1][1，2]不是相邻区间），然后用总区间数减去弓箭数量 就是要移除的区间数量了。)
     *
     * 关于为什么是按照区间右端点排序？
     * 官解里对这个描述的非常清楚了，这个题其实是预定会议的一个问题，给你若干时间的会议，然后去预定会议，那么能够预定的最大的会议数量是多少？
     * 核心在于我们要找到最大不重叠区间的个数。 如果我们把本题的区间看成是会议，那么按照右端点排序，我们一定能够找到一个最先结束的会议，
     * 而这个会议一定是我们需要添加到最终结果的的首个会议
     *
     * 关于为什么是不能按照区间左端点排序？
     * 把本题的区间看成是会议，如果“按照左端点排序，我们一定能够找到一个最先开始的会议”，但是最先开始的会议，不一定最先结束。
     * |_________|                  区间a
     *   |___|                      区间b
     *        |__|                  区间c
     *             |______|         区间d
     * 区间a是最先开始的，如果我们采用区间a作为放入最大不重叠区间的首个区间，那么后面我们只能采用区间d作为第二个放入最大不重叠区间的区间，
     * 但这样的话，最大不重叠区间的数量为2。但是如果我们采用区间b作为放入最大不重叠区间的首个区间，那么最大不重叠区间的数量为3，因为区间b是最先结束的。
     */
    public int eraseOverlapIntervals_8(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> {
            return Integer.compare(a[1],b[1]); // 右边界排序
        });
     /*   Arrays.sort(intervals, (a, b) ->  a[1] - b[1]);*/
        int count = 1; // 记录非交叉区间的个数 / 互不重叠的区间
        int end = intervals[0][1]; // 记录区间分割点
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
    /**
     * 左边界排序可不可以呢？
     * 也是可以的，只不过 左边界排序我们就是直接求 重叠的区间，count为记录重叠区间数。
     */
    public int eraseOverlapIntervals_9(int[][] intervals) {
        Arrays.sort(intervals, (a, b) ->  a[0] - b[0]);
        int count = 0; // 注意这里从0开始，因为是记录重叠区间
        int end = intervals[0][1]; // 记录区间分割点 //记录区间尾部的位置

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end)  {// 无重叠的情况
                end = intervals[i][1];      //如果没有重叠，就不需要移除，只需要更新尾部的位置即可
            } else { // 重叠情况
                end = Math.min(end, intervals[i][1]); //如果重叠了，必须要移除一个，所以count要加1，然后更新尾部的位置，我们取尾部比较小的
                count++;
            }
        }
        return count;
    }
    /**
     * 其实代码还可以精简一下， 用 intervals[i][1] 替代 end变量，只判断 重叠情况就好
     */
    public int eraseOverlapIntervals_10(int[][] intervals) {
        Arrays.sort(intervals, (a, b) ->  a[0] - b[0]);
        int count = 0; // 注意这里从0开始，因为是记录重叠区间

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) { //重叠情况
                intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
                count++;
            }
        }
        return count;
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
            if(intervals[i][0] < intervals[i - 1][1]){  //重叠情况
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
        int count = 0;   // 移除次数
        Arrays.sort(intervals, (a, b)-> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // 根据区间终点进行升序排序

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < intervals[i - 1][1]){ // 当前区间和之前的区间重叠了
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
    /**
     * 动态规划 Time Limit Exceeded
     * 将 前面区间的结束 和 后面区间的开始 结合起来看，其就是一个非严格递增序列。而我们的目标就是删除若干区间，从而剩下最长的非严格递增子序列
     * 可以看成是删除了若干数字，然后剩下剩下最长的严格递增子序列
     */
    public int eraseOverlapIntervals_11(int[][] intervals) {
        Arrays.sort(intervals, (a, b) ->  a[0] - b[0]);

        int n = intervals.length;
        int[] dp = new int[n]; // dp[i] 表示前 i+1 个区间里最多能选多少个不重叠的区间。
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (intervals[j][1] <= intervals[i][0]) { // 如果第j个区间的结束时间小于等于第i个区间的开始时间，说明不重叠
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return n - Arrays.stream(dp).max().getAsInt(); // 找到所有dp中的最大值，即最多能选的非重叠区间数
    }
}

class myComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
        return a[1] - b[1];
    }
}