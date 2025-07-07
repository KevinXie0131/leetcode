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
     */
    private Stack<Integer> stack;
    private Stack<Integer> minStack; // 用于存获取 stack 中最小值
    /**
     * 辅助栈
     * 可以在每个元素 a 入栈时把当前栈的最小值 m 存储起来。在这之后无论何时，如果栈顶元素是 a，我们就可以直接返回存储的最小值 m。
     * 只需要设计一个数据结构，使得每个元素 a 与其相应的最小值 m 时刻保持一一对应。因此我们可以使用一个辅助栈，与元素栈同步插入与删除，用于存储与每个元素对应的最小值。
     */
    public Q155_Min_Stack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack.push(val); // 当一个元素要入栈时，我们取当前辅助栈的栈顶存储的最小值，与当前元素比较得出最小值，将这个最小值插入辅助栈中；

        int top = minStack.peek();
        minStack.push(Math.min(top, val)); // 保存最小值在minStack
    }

/*    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }*/

    public void pop() {
        stack.pop(); // 当一个元素要出栈时，我们把辅助栈的栈顶元素也一并弹出；
        minStack.pop();
    }

/*    public void pop() {
        if(stack.pop().equals(minStack.peek()))
            minStack.pop();
    }*/

    public int top() {
        return stack.peek();
    }
    /**
     * 题目要求在常数时间内获得栈中的最小值
     */
    public int getMin() {
        return minStack.peek(); // 在任意一个时刻，栈内元素的最小值就存储在辅助栈的栈顶元素中。
    }
}
