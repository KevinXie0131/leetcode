package com.answer.stack;

import java.util.*;

public class Q232_Implement_Queue_using_Stacks_3 {
    public static void main(String[] args) {
        Q232_Implement_Queue_using_Stacks_3 queue = new Q232_Implement_Queue_using_Stacks_3();
        // Test 1: Push and Pop
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop()); // Expected: 1
        System.out.println(queue.peek()); // Expected: 2
    }
    /**
     * 使用一个栈实现
     * To implement a queue using only one stack in Java (Leetcode 232: Implement Queue using Stacks),
     * you must simulate FIFO behavior with a LIFO data structure. Normally, two stacks are used,
     * but with one, you'll need recursion for pop() and peek().
     */
    private Stack<Integer> stack;

    public Q232_Implement_Queue_using_Stacks_3() {
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
    }

    public int pop() {
        return popHelper();
    }
    // Recursively pops elements until the last one (front of queue), then rebuilds the stack on the way back.
    private int popHelper() {
        int x = stack.pop();
        if (stack.isEmpty()) {
            // The bottom element is the queue front
            return x;
        } else {
            int res = popHelper();
            stack.push(x);
            return res;
        }
    }
    // Recursively pops elements until the last one, remembers it, then rebuilds the stack.
    public int peek() {
        int x = stack.pop();
        if (stack.isEmpty()) {
            // The bottom element is the queue front
            stack.push(x);
            return x;
        } else {
            int res = peek();
            stack.push(x);
            return res;
        }
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
