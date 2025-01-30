package com.answer.stack;

import java.util.*;

public class Q933_Number_of_Recent_Calls {
    public static void main(String[] args) {
        // [1], [100], [3001], [3002]]
        Q933_Number_of_Recent_Calls call = new Q933_Number_of_Recent_Calls();
        System.out.println(call.ping(1));
        System.out.println(call.ping(100));
        System.out.println(call.ping(3001));
        System.out.println(call.ping(3002));
    }
    /**
     * 使用Queue
     */
    Deque<Integer> queue;

    public Q933_Number_of_Recent_Calls() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while(queue.size() > 0 && t - queue.peek() > 3000){
            queue.poll();
        }
        return queue.size();
    }
    /**
     * Use array as queue
     */
    int left, right;
    int []times;

    public void RecentCounter_1() {
        left = 0;
        right = 0;
        times = new int[10005]; // At most 10^4 calls will be made to ping.
    }

    public int ping_1(int t) {
        times[right++] = t;
        while (times[left] < t - 3000) {
            left++;
        }
        return right - left;
    }
}
