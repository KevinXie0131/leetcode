package com.answer.stack;

import java.util.*;

public class Q155_Min_Stack {
    /**
     * 最小栈
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * 实现 MinStack 类:
     *  MinStack() 初始化堆栈对象。
     *  void push(int val) 将元素val推入堆栈。
     *  void pop() 删除堆栈顶部的元素。
     *  int top() 获取堆栈顶部的元素。
     *  int getMin() 获取堆栈中的最小元素。
     * You must implement a solution with O(1) time complexity for each function.
     *
     * 示例 1:
     *  输入：["MinStack","push","push","push","getMin","pop","top","getMin"]
     *       [[],[-2],[0],[-3],[],[],[],[]]
     *  输出：[null,null,null,null,-3,null,0,-2]
     *  解释：
     *  MinStack minStack = new MinStack();
     *  minStack.push(-2);
     *  minStack.push(0);
     *  minStack.push(-3);
     *  minStack.getMin();   --> 返回 -3.
     *  minStack.pop();
     *  minStack.top();      --> 返回 0.
     *  minStack.getMin();   --> 返回 -2.
     *
     */
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
        minStack.push(Math.min(top, val)); // 保存最小值在minStack
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
