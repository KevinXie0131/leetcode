package com.answer.stack;

import java.util.*;

public class Q239_Sliding_Window_Maximum_2 {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
/*        int[] nums = {1,-1};
        int k = 1;*/
        int[] result = maxSlidingWindow_1(nums, k);
        System.out.println(Arrays.toString(result));
    }
    /**
     * Use PriorityQueue 大顶堆
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
    /**
     * 大顶堆
     */
    public static int[] maxSlidingWindow_1(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->b[1]-a[1]);
        int n = nums.length;
        int len = nums.length - k + 1;
        int index = 0;
        int[] result = new int[len];

        for(int i = 0 ; i < n; i++){
            pq.offer(new int[]{i, nums[i]});
            if(i >= k - 1 ){
                while(!pq.isEmpty() && pq.peek()[0] < i - k + 1){
                    pq.poll();
                }

                result[index++] = pq.peek()[1];
            }
        }
        return result;
    }
}