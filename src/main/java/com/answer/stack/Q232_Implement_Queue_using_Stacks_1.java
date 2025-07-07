package com.answer.stack;

import java.util.*;

public class Q232_Implement_Queue_using_Stacks_1 {
    Deque<Integer> stack;
    Deque<Integer> stack2;
    /**
     * 栈2充当队列，push操作将元素反转到栈1，加入元素后，再将元素反转到栈2
     */
    public Q232_Implement_Queue_using_Stacks_1() {
        stack = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }
    // 一个栈保存倒序后的数，一个栈作为一个中转站，暂时储存上一个栈的数据，然后将要push进的数据加入到上一个栈之后，
    // 将暂时保存元素的那个栈中的元素全部放回到当前栈中
    public void push(int x) {
        while(!stack2.isEmpty()) {
            stack.push(stack2.pop());
        }
        stack.push(x);
        while(!stack.isEmpty()) {
            stack2.push(stack.pop());
        }
    }

    public int pop() {
        return stack2.pop();
    }

    public int peek() {
        return stack2.peek();
    }

    public boolean empty() {
        return stack2.isEmpty();
    }

}
