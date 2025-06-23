package com.answer.stack;

import java.util.*;

public class Q346_Moving_Average_from_Data_Stream {
    /**
     * 数据流中的移动平均值
     * 给定一个整数数据流和一个滑动窗口的大小，请计算滑动窗口里所有数字的平均值。实现 MovingAverage 类：
     *  MovingAverage(int size) 用窗口大小 size 初始化对象。
     *  double next(int val) 成员函数 next，每次调用会向数据流添加一个整数 val，并返回滑动窗口里所有数字的平均值。
     *
     * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
     * Example:
     *  MovingAverage m = new MovingAverage(3);
     *  m.next(1) = 1
     *  m.next(10) = (1 + 10) / 2
     *  m.next(3) = (1 + 10 + 3) / 3
     *  m.next(5) = (10 + 3 + 5) / 3
     */
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
