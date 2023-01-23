package com.answer.stack;

import java.util.*;

public class Q225_Implement_Stack_using_Queues {

    Deque<Integer> que1;
    Deque<Integer> que2;
    public Q225_Implement_Stack_using_Queues() {
        que1 = new ArrayDeque<>();
        que2 = new ArrayDeque<>();
    }

    public void push(int x) {
        que1.offer(x);
    }

    public int pop() {
        int size = que1.size();
        size--;
        while(size > 0){
            que2.offer(que1.poll());
            size--;
        }
        int result = que1.poll();

        que1 = que2;
        que2 = new ArrayDeque<>();
        return result;
    }

    public int top() {
        return que1.peekLast();
    }

    public boolean empty() {
        return que1.isEmpty();
    }

}
