package com.answer.stack;

import java.util.*;

public class Q232_Implement_Queue_using_Stacks_2 {
    public static void main(String[] args) {
        Q232_Implement_Queue_using_Stacks_2 queue = new Q232_Implement_Queue_using_Stacks_2();
        // Test 1: Push and Pop
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.pop()); // Expected: 1
        System.out.println(queue.peek()); // Expected: 2
    }
    /**
     * 使用一个栈实现
     */
    Deque<Integer> stack;

    public Q232_Implement_Queue_using_Stacks_2() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(x);
        }
        else {
            int pop = stack.pop();
            push(x); // 递归
            stack.push(pop);
        }
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }

}
