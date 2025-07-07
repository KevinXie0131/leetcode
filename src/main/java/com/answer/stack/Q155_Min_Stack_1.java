package com.answer.stack;

import java.util.Stack;

public class Q155_Min_Stack_1 {
    // 元组
    private Stack<MyTuple> stack;

    public Q155_Min_Stack_1() {
        stack = new Stack<MyTuple>();
    }

    public void push(int val) {
        MyTuple myTuple = new MyTuple();
        myTuple.value = val;
        myTuple.minValue = Math.min(val, getMin());
        stack.push(myTuple); // 保存最小值在minStack
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        MyTuple myTuple= stack.peek();
        return myTuple.value;
    }

    public int getMin() {
        if(stack.isEmpty()) return Integer.MAX_VALUE;

        MyTuple myTuple= stack.peek();
        return myTuple.minValue;
    }

    private class MyTuple{
        int value;
        int minValue;
    }
}


