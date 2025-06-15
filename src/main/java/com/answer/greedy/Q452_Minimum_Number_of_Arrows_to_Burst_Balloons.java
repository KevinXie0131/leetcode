package com.answer.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Q452_Minimum_Number_of_Arrows_to_Burst_Balloons {
    /**
     * 用最少数量的箭引爆气球
     * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
     * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
     * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
     * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
     * 输出：2
     * 解释：气球可以用2支箭来爆破:
     * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
     * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
     */
    public static void main(String[] args) {
       int[][] points = {{10,16},{2,8},{1,6},{7,12}};
     //  int[][] points = {{2147483646,2147483647}, { -2147483646,-2147483645}};
        System.out.println(findMinArrowShots(points));
    }
    /**
     * Approach 1: Greedy
     * The idea of greedy algorithm is to pick the locally optimal move at each step, that will lead to the globally optimal solution.
     * Proof by contradiction
     *
     * 局部最优：当气球出现重叠，一起射，所用弓箭最少。全局最优：把所有气球射爆所用弓箭最少。
     *
     * 为了让气球尽可能的重叠，需要对数组进行排序。
     * 既然按照起始位置排序，那么就从前向后遍历气球数组，靠左尽可能让气球重复。
     * 如果气球重叠了，重叠气球中右边边界的最小值 之前的区间一定需要一个弓箭
     *
     * 时间复杂度 : O(NlogN)  排序需要 O(NlogN) 的复杂度
     * 空间复杂度 : O(logN) java所使用的内置函数用的是快速排序需要 logN 的空间
     */
    public static int findMinArrowShots(int[][] points) {
  //      Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1]- b[1] : a[0] - b[0]);
        int count = 1;  // points 不为空至少需要一支箭
     /*   Arrays.sort(points, (a, b)->{  // not use o1[1] - o2[1] trick to avoid integer overflow for very large or small values.
            if (a[0] > b[0]) return 1;
            else if (a[0] < b[0]) return -1;
            else return 0;
        });*/
        /**
         * The following sorting works too.
         * Arrays.sort(points, (a, b)->a[0] > b[0] ? 1 : -1);
         */
        // 根据气球直径的开始坐标从小到大排序
        // 使用Integer内置比较方法，不会溢出
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        //  Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1])); // 也可以工作

        for(int i = 1; i < points.length; i++){
            // 注意题目中说的是： ：满足 xstart ≤ x ≤ xend，则该气球会被引爆。那么说明两个气球挨在一起不重叠也可以一起射爆，
            // 所以代码中 if (points[i][0] > points[i - 1][1]) 不能是>=
            if(points[i][0] > points[i - 1][1]){ // 气球i和气球i-1不挨着，注意这里不是>=
                count++; // 需要一支箭
            }else{  // 气球i和气球i-1挨着
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);// 更新重叠气球最小右边界
            }
        }
        return count;
    }
    /**
     * 把区间按照右端点从小到大排序。
     * 初始化答案 ans=0，上一个放点的位置 pre=−∞。
     * 遍历区间，如果 start≤pre，那么这个区间已经包含点，跳过。
     * 如果 start>pre，那么必须放一个点，把 ans 加一。根据上面的讨论，当前区间的右端点就是放点的位置，更新 pre=end。
     * 遍历结束后，返回 ans。
     *
     * 如果按右端升序排序，就杜绝了「前面包后面」的情况。
     */
    public int findMinArrowShots4(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[1])); // 按照右端点从小到大排序
        int ans = 0;
        long pre = Long.MIN_VALUE;
        for (int[] p : points) {
            if (p[0] > pre) { // 上一个放的点在区间左边
                ans++;
                pre = p[1]; // 在区间的最右边放一个点
            }
        }
        return ans;
    }
    /**
     * another form
     * 首先将区间按照右端点升序排序，此时位序为1的区间就是我们要找的第一个区间（图1），我们需要记录下第一个区间的右端点right（射出第一支箭），然后继续遍历，此时就会存在两种情况：
     *  对于左端点小于等于right的区间，说明该区间能被前面的箭（right）穿过。
     *  对于接下来左端点大于right的区间，说明前面这支箭无法穿过该区间（即：该区间就是未被箭穿过的区间集合的第一个区间），我们又找到了第一个未被箭穿过的区间，此时我们用一把新的箭穿过该区间的右端点（即更新right：right = points[i][1]），并将使用的箭数+1。如此往复。
     */
    public int findMinArrowShots5(int[][] points) {
        Arrays.sort(points, (a, b) ->  Integer.compare(a[1], b[1]));
        int count = 1;
        int right = points[0][1];  // 第一支箭直接射出

        for(int i = 1; i < points.length; i++){
            if(points[i][0] <= right){
                continue;   // 该区间能被当前箭right穿过
            }
            right = points[i][1];  // 继续射出箭
            count++;  // 箭数加1

/*            if (right < points[i][0]) {  //如果箭射入的位置小于下标为i这个气球的左边位置，说明这支箭不能击爆下标为i的这个气球，需要再拿出一支箭，并且要更新这支箭射入的位置
                right = points[i][1];
                count++;
            }*/
        }
        return count;
    }
    /**
     * 动态规划 Time Limit Exceeded
     * Refer to Q435_Non_overlapping_Intervals
     * 把气球看成区间，几个箭可以全部射爆，意思就是有多少不重叠的区间。注意这里重叠的情况也可以射爆。
     */
    public static int findMinArrowShots_8(int[][] points) {
       /* Arrays.sort(points, (a, b) ->  a[0] - b[0]);*/  // [[-2147483646,-2147483645],[2147483646,2147483647]]
        Arrays.sort(points, (a, b) ->  Integer.compare(a[0], b[0]));
        int n = points.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (points[j][1] < points[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
    /**
     * Official answer
     * 排序 + 贪心
     */
    public static int findMinArrowShots_1(int[][] points) {
        if (points.length == 0) return 0;
        // sort by x_end
        Arrays.sort(points, (o1, o2) -> {
            // We can't simply use the o1[1] - o2[1] trick, as this will cause an integer overflow for very large or small values.
            if (o1[1] == o2[1]) return 0;
            if (o1[1] < o2[1]) return -1;
            return 1;
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p: points) {
            xStart = p[0];
            xEnd = p[1];
            // if the current balloon starts after the end of another one, one needs one more arrow
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }
        return arrows;
    }
    /**
     * different sorting
     */
    public static int findMinArrowShots_2(int[][] points) {
        if (points.length == 0) return 0;
        // sort by x_end
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] <= 0 && o2[1] >= 0){
                    return -1;
                }
                if (o1[1] >= 0 && o2[1] <= 0){
                    return 1;
                }
                return o1[1] - o2[1];
            }
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p: points) {
            xStart = p[0];
            xEnd = p[1];
            // if the current balloon starts after the end of another one, one needs one more arrow
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }

        return arrows;
    }
}
