package com.answer.priorityqueue;

import java.util.*;

public class Q215_Kth_Largest_Element_in_an_Array {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
   //     int[] nums = {-1,2,0};
        int k = 2;

        System.out.println(findKthLargest1(nums, k));
    }
    /**
     * 排序法  时间复杂度是 O(nlogn)
     */
    public int findKthLargest_0(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[ nums.length - k];
    }
    /**
     * 最大堆  时间复杂度是O(NlogN)
     */
    static public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue(Collections.reverseOrder());
        for(int i = 0; i < nums.length; i++){
            queue.offer(nums[i]);
        }
        for(int i = 0; i < k - 1; i++){
            queue.poll();
        }
        return queue.poll();
    }
    /**
     * 最小堆
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(int i = 0; i < nums.length; i++){
            queue.offer(nums[i]);
        }

        for(int i = 0; i < nums.length - k; i++){
            queue.poll();
        }
        return queue.poll();
    }
    /**
     * 最小堆 优化 时间复杂度是O(NlogK)
     */
    public static int findKthLargest_1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(int i = 0; i < k; i++){
            queue.offer(nums[i]);
        }

        for(int i = k; i < nums.length; i++){
            if(nums[i] > queue.peek()){
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
    /**
     * 最小堆 优化 一次遍历
     */
    public static int findKthLargest_2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(int i = 0; i < nums.length; i++){
            if(queue.size() < k){
                queue.offer(nums[i]);
            } else{
                if(nums[i] > queue.peek()){
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }
        return queue.peek();
    }
    /**
     * 最小堆
     */
    public int findKthLargest_4(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n: nums) {
            heap.offer(n);
            if (heap.size() > k)
                heap.poll();
        }
        // output
        return heap.poll();
    }
}
