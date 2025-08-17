package com.answer.stack;

import java.util.*;

public class Q155_Min_Stack_2 {
    /**
     * 栈中存储数组实现
     */
    private Stack<int[]> stack = new Stack<>(); // 数组栈, [当前值, 当前最小值]

    public Q155_Min_Stack_2() {

    }
    // 新元素入栈：当栈为空，保存元组 (x, x)；当栈不空，保存元组 (x, min(此前栈内最小值， x)))
    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(new int[]{x, x});
        }else {
            stack.push(new int[]{x, Math.min(x, stack.peek()[1])}); // 每次新元素 x 入栈的时候保存一个元组：（当前值 x，栈内最小值）
        }
    }
    // 出栈：删除栈顶的元组
    public void pop() {
        stack.pop();
    }
    // 获取栈顶的当前值，即栈顶元组的第一个值
    public int top() {
        return stack.peek()[0];
    }
    // 获取栈内最小值，即栈顶元组的第二个值
    public int getMin() {
        return stack.peek()[1];
    }
}


