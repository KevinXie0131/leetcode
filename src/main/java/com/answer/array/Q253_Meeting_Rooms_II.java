package com.answer.array;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q253_Meeting_Rooms_II {
    /**
     * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束时间 intervals[i] = [starti, endi] 。请你计算至少需要多少间会议室，才能满足这些会议安排。
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * find the minimum number of conference rooms required.
     *  Example 1:
     *      Input: [[0, 30],[5, 10],[15, 20]]
     *      Output: 2
     *      解释: 需要两间会议室：[0,30] 和 [5,10],[15,20] 分别在另一间。
     *  Example 2:
     *      Input: [[7,10],[2,4]]
     *      Output: 1
     */
    public static void main(String[] args) {
        int[][] intervals =  {{0,30},{5,10},{15,20}};
        int res = minMeetingRooms_1(intervals);
        System.out.println(res);
        int[][] intervals1 =  {{7,10},{2,4}};
        int res1 = minMeetingRooms_1(intervals1);
        System.out.println(res1);
    }
    /**
     * https://github.com/Seanforfun/Algorithm-and-Leetcode/blob/master/leetcode/253.%20Meeting%20Rooms%20II.md
     *
     * 1. Sort the intervals according to the starting time.
     * 2. Use a minHeap(pq) to save the endTime for all intervals according to the order of start time.
     *   i. Add end time to the pq.
     *   ii. if cur start time < pq.peek() => means current start time is before first ending time, which means we must have a new room.
     *   iii. if cur start time >= pq.peek() => means we can use this room for the meeting, we poll out the the original period and add current period to the pq (Means we update the room with the new meeting).
     */
    static public int minMeetingRooms_0(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int room = 0;

        for(int i = 0; i < intervals.length; i++){
            pq.offer(intervals[i][1]);
            if(intervals[i][0] < pq.peek()) {
                room++;
            } else{
                pq.poll();
            }
        }
        return room;
    }
    /**
     * Approach 1: Priority Queues
     * Use PriorityQueue - Min Heap
     */
    public static  int minMeetingRooms(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(intervals.length,
                (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        for(int[] interval : intervals){
            queue.offer(new int[]{interval[0], 1}); // 1 stands for start
            queue.offer(new int[]{interval[1], -1}); // 0 stands for end
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
    /**
     * PriorityQueue + Sort
     */
    static public int minMeetingRooms_5(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
            return a[1] - b[1];
        });
        Arrays.sort(intervals, (a, b)->{
            return a[0] != b[0] ? a[0] - b[0]: a[1] - b[1];
        });

        int result = 1;
        pq.offer(intervals[0]);
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= pq.peek()[1]){
                pq.poll();
            }
            pq.offer(intervals[i]);
            result = Math.max(result, pq.size());
        }
        return result;
    }
    /**
     * Two pointer + sort
     * we sort start time and end time.
     * initialize the start and end index as 0.
     * if start time < end time, means we have a meeting in active, active++.
     * else active--.
     * We need to record the max number of the active room.
     */
    static public int minMeetingRooms_6(int[][] intervals) {
        int len = intervals.length;
        int[] startTime = new int[len];
        int[] endTime = new int[len];
        int index = 0;
        for(int[] interval: intervals){
            startTime[index] = interval[0];
            endTime[index++] = interval[1];
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        int i = 0, j = 0;
        int room = 0, max = 0;
        while(i < len && j < len){
            if(startTime[i] < endTime[j]){
                room++;
                i++;
            }else{
                room--;
                j++;
            }
            max = Math.max(max, room);
        }
        return max;
    }
}
