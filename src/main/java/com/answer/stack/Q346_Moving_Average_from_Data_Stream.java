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
}
