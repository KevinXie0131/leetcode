package com.answer.stack;

import java.util.*;

public class Q346_Moving_Average_from_Data_Stream {

    /**
     * Approach 2: Double-ended Queue
     */
    Deque<Integer> queue;
    int size;
    double sum;

    public Q346_Moving_Average_from_Data_Stream(int size) {
        this.queue = new ArrayDeque<Integer>();
        this.size = size;
        this.sum = 0;
    }

    public double next(int val) {
        if(queue.size() == size){
            sum -= queue.poll();
        }
        queue.offer(val);
        sum += val;
        return sum / queue.size();
    }
    /**
     * Approach 3: Circular Queue with Array
     * tail = (head + 1) mod size
     */
    int size1, head = 0, windowSum = 0, count = 0;
    int[] queue1;
    public void MovingAverage_1(int size) {
        this.size1 = size;
        queue1 = new int[size];
    }

    public double next_1(int val) {
        ++count;
        // calculate the new sum by shifting the window
        int tail = (head + 1) % size1;
        windowSum = windowSum - queue1[tail] + val;
        // move on to the next head
        head = (head + 1) % size1;
        queue1[head] = val;
        return windowSum * 1.0 / Math.min(size1, count);
    }
    /**
     * Approach 1: Array or List
     */
    int size2;
    List queue2 = new ArrayList<Integer>();
    public void MovingAverage_2(int size) {
        this.size2 = size;
    }
    public double next_2(int val) {
        queue2.add(val);
        // calculate the sum of the moving window
        int windowSum = 0;
        for(int i = Math.max(0, queue2.size() - size2); i < queue2.size(); ++i)
            windowSum += (int)queue2.get(i);

        return windowSum * 1.0 / Math.min(queue2.size(), size2);
    }
}
