package com.answer.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class Q452_Minimum_Number_of_Arrows_to_Burst_Balloons {
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
     * Official answer
     */
    public static int findMinArrowShots_1(int[][] points) {
        if (points.length == 0) return 0;

        // sort by x_end
        Arrays.sort(points, (o1, o2) -> {
            // We can't simply use the o1[1] - o2[1] trick, as this will cause an
            // integer overflow for very large or small values.
            if (o1[1] == o2[1]) return 0;
            if (o1[1] < o2[1]) return -1;
            return 1;
        });

        int arrows = 1;
        int xStart, xEnd, firstEnd = points[0][1];
        for (int[] p: points) {
            xStart = p[0];
            xEnd = p[1];
            // if the current balloon starts after the end of another one,
            // one needs one more arrow
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
            // if the current balloon starts after the end of another one,
            // one needs one more arrow
            if (firstEnd < xStart) {
                arrows++;
                firstEnd = xEnd;
            }
        }

        return arrows;
    }
}
