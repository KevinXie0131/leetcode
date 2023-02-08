package com.answer.greedy;

import java.util.*;

public class Q406_Queue_Reconstruction_by_Height {
    public static void main(String[] args) {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] res = reconstructQueue(people);
    }
    /**
     * Approach 1: Greedy
     *
     * + Sort people:
     *   - In the descending order by height.
     *   - Among the guys of the same height, in the ascending order by k-values.
     * + Take guys one by one, and place them in the output array at the indexes equal to their k-values.
     * + Return output array.
     */
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        List<int[]> list = new ArrayList<>();
        for(int[] p : people){
            list.add(p[1], p);
        }

        return list.toArray(new int[0][]);
    }
}
