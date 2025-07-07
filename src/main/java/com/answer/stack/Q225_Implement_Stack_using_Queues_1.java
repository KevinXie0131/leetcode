package com.answer.stack;

import java.util.*;

public class Q225_Implement_Stack_using_Queues_1 {
    Deque<Integer> que1;
    Deque<Integer> que2;
    /**
     * 使用两个 Queue 实现
     * 队列的特点是先进先出，而栈是先进后出。那如何能让队列变成先进后出呢？其实只要在插入的时候能让插入的数插入到队列头，并且保持相对顺序不变就可以了。
     */
    public Q225_Implement_Stack_using_Queues_1() {
        que1 = new ArrayDeque<>();
        que2 = new ArrayDeque<>();
    }
    // 用que1队列保存栈内的元素，用que2队列作为入栈的辅助队列
    public void push(int x) {
        que2.offer(x);    //先将要插入的元素加入到辅助队列中
        while(!que1.isEmpty()){
            que2.offer(que1.poll());//然后再将que1队列的所有元素加入到que2队列，使得新插入的元素插入到了队列头
        }
        Deque<Integer> temp = que1; //此时交换队列
        que1 = que2;
        que2 = temp;
    }

    public int pop() {
        return que1.poll();
    }

    public int top() {
        return que1.peek();
    }

    public boolean empty() {
        return que1.isEmpty();
    }

}
