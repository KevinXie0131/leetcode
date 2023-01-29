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

    Deque<Integer> queue;

    public Q933_Number_of_Recent_Calls() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while(queue.peek() < t - 3000){
            queue.poll();
        }
        return queue.size();
    }
}
