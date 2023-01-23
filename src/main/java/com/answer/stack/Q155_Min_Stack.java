package com.answer.stack;

import java.util.*;

public class Q155_Min_Stack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public Q155_Min_Stack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val);

        int top = minStack.peek();
        minStack.push(Math.min(top, val));

    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
