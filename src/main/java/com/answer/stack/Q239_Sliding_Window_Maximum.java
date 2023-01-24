package com.answer.stack;

import java.util.*;

public class Q239_Sliding_Window_Maximum {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] result = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;
        int[] result = new int[len];
        int num = 0;

        MyMonoTonicQueue myQueue = new MyMonoTonicQueue();
        for(int i = 0; i < k; i++){
            myQueue.offer(nums[i]);
        }
        result[num++] = myQueue.peek();
        for(int i = k; i < nums.length; i++){
            myQueue.poll(nums[i - k]);
            myQueue.offer(nums[i]);
            result[num++] = myQueue.peek();
        }
        return result;
    }
}

class MyMonoTonicQueue {
    Deque<Integer> queue = new ArrayDeque<>();

    void poll(int val){
        if(!queue.isEmpty() && val == queue.peek()){
            queue.poll();
        }
    }

    void offer(int val){
        while(!queue.isEmpty() && val > queue.getLast()){
            queue.removeLast();
        }
        queue.offer(val);
    }

    int peek(){
        return queue.peek();
    }
}