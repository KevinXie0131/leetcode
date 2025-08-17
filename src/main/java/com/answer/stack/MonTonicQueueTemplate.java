package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonTonicQueueTemplate {
    //自定义 单调队列（从大到小）
    class MyMonoTonicQueue {
        Deque<Integer> queue = new ArrayDeque<>();// 使用deque来实现单调队列

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
    /**
     * 单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
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
    /**
     * 利用双端队列手动实现单调队列
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int len = nums.length - k + 1;
        int[] result = new int[len];
        int index = 0;

        for(int i = 0; i < k; i++){
            while(!queue.isEmpty() && nums[i] > queue.getLast()){
                queue.removeLast();
            }
            queue.offer(nums[i]);
        }

        result[index++] = queue.peek();

        for(int i = k; i < nums.length; i++){
            if(!queue.isEmpty() && nums[i - k] == queue.peek()){
                queue.poll();
            }
            while(!queue.isEmpty() && nums[i] > queue.getLast()){
                queue.removeLast();
            }
            queue.offer(nums[i]);

            result[index++] = queue.peek();
        }
        return result;
    }
}
