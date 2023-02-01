package com.answer.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q253_Meeting_Rooms_II {
    public static void main(String[] args) {
        int[][] intervals =  {{0,30},{5,10},{15,20}};
        int res = minMeetingRooms_1(intervals);
        System.out.println(res);
    }
    /**
     * Approach 1: Priority Queues
     * Use PriorityQueue - Min Heap
     */
    public static  int minMeetingRooms(int[][] intervals) {

        PriorityQueue<int[]> queue = new PriorityQueue<>(intervals.length,
                (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        for(int[] interval : intervals){
            queue.offer(new int[]{interval[0], 1});
            queue.offer(new int[]{interval[1], -1});
        }
        /**
         * 0 -> 1
         * 5 -> 1
         * 10 -> -1
         * 15 -> 1
         * 20 -> -1
         * 30 -> -1
         */
/*        while(!queue.isEmpty()){
            int[] res = queue.poll();
            System.out.println(res[0] + " -> " +res[1]);
        }*/
        int count = 0;
        int max = 0;

        while(!queue.isEmpty()){
            count += queue.poll()[1];
            max = Math.max(max, count);
        }
        return max;
    }
    /**
     * Approach 2: Chronological Ordering
     * Sorting
     */
    public static int minMeetingRooms_1(int[][] intervals) {
        int n = intervals.length;
        int[][] temp = new int[n * 2][2];

        int index = 0;
        for(int[] interval : intervals){
            temp[index++] = new int[]{interval[0], 1};
            temp[index++] = new int[]{interval[1], -1};
        }

        Arrays.sort(temp, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int count = 0;
        int max = 0;
        for(int[] t: temp){
            count += t[1];
            max = Math.max(max, count);
        }
        return max;
    }
}
