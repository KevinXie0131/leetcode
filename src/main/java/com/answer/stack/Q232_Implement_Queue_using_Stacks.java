package com.answer.stack;

import java.util.*;

public class Q232_Implement_Queue_using_Stacks {

    Deque<Integer> stackIn;
    Deque<Integer> stackOut;

    public Q232_Implement_Queue_using_Stacks() {
        stackIn = new ArrayDeque<>();
        stackOut = new ArrayDeque<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        if(stackOut.isEmpty()){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    public int peek() {
        if(stackOut.isEmpty()){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    public boolean empty() {
        return stackOut.isEmpty() && stackIn.isEmpty();
    }
}
