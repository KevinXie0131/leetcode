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
     */
    public static int findMinArrowShots(int[][] points) {
  //      Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1]- b[1] : a[0] - b[0]);
        int count = 1;
        Arrays.sort(points, (a, b)->{  // not use o1[1] - o2[1] trick to avoid integer overflow for very large or small values.
            if (a[0] > b[0]) return 1;
            else if (a[0] < b[0]) return -1;
            else return 0;
        });

        for(int i = 1; i < points.length; i++){
            if(points[i][0] > points[i -1][1]){
                count++;
            }else{
                points[i][1] = Math.min(points[i][1], points[i - 1][1]);
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
