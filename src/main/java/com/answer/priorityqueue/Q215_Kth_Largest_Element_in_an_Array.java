package com.answer.priorityqueue;

import java.util.PriorityQueue;

public class Q215_Kth_Largest_Element_in_an_Array {

    public static void main(String[] args) {
    //    int[] nums = {3,2,1,5,6,4};
        int[] nums = {-1,2,0};
        int k = 2;

        System.out.println(findKthLargest_2(nums, k));
    }

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
     *
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
     *
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
}
