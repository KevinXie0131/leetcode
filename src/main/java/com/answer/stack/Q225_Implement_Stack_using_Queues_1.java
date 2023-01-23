package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q225_Implement_Stack_using_Queues_1 {

    Deque<Integer> que1;
    Deque<Integer> que2;

    public Q225_Implement_Stack_using_Queues_1() {
        que1 = new ArrayDeque<>();
        que2 = new ArrayDeque<>();
    }

    public void push(int x) {
        que2.offer(x);
        while(!que1.isEmpty()){
            que2.offer(que1.poll());
        }
        Deque<Integer> temp = que1;
        que1 = que2;
        que2 = temp;
    }

    public int pop() {
        return que1.poll();
    }

    public int top() {
        return que1.peek();
    }

    public boolean empty() {
        return que1.isEmpty();
    }

}
