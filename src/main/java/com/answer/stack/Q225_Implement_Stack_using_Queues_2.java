package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q225_Implement_Stack_using_Queues_2 {

    Deque<Integer> que;

    public Q225_Implement_Stack_using_Queues_2() {
        que = new ArrayDeque<>();
    }

    public void push(int x) {
        int size = que.size();
        que.offer(x);
        for(int i= 0; i < size; i++){
            que.offer(que.poll());
        }
    }

    public int pop() {
        return que.poll();
    }

    public int top() {
        return que.peek();
    }

    public boolean empty() {
        return que.isEmpty();
    }

}
