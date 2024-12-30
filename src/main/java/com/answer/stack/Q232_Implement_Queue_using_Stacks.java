package com.answer.stack;

import java.util.*;

public class Q232_Implement_Queue_using_Stacks {
    /**
     * 时间复杂度: push和empty为O(1), pop和peek为O(n)
     * 空间复杂度: O(n)
     */
    Deque<Integer> stackIn;
    Deque<Integer> stackOut;
    /** Initialize your data structure here. */
    public Q232_Implement_Queue_using_Stacks() {
        stackIn = new ArrayDeque<>();  // 负责进栈
        stackOut = new ArrayDeque<>(); // 负责出栈
    }
    /** Push element x to the back of queue. */
    public void push(int x) {
        stackIn.push(x);
    }
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        // 只有当stOut为空的时候，再从stIn里导入数据（导入stIn全部数据）
        if(stackOut.isEmpty()){
            // 从stIn导入数据直到stIn为空
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }
    /** Get the front element. */
    public int peek() {
        if(stackOut.isEmpty()){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }
    // 可以看出peek()的实现，直接复用了pop()， 要不然，对stOut判空的逻辑又要重写一遍。
/*    public int peek() {
        int result = this.pop(); // 直接使用已有的pop函数
        this.stackOut.push(result);  // 因为pop函数弹出了元素res，所以再添加回去
        return result;
    }*/
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackOut.isEmpty() && stackIn.isEmpty();
    }

    // 如果stackOut为空，那么将stackIn中的元素全部放到stackOut中
/*    private void dumpstackIn(){
        if (!stackOut.isEmpty()) {
            return;
        }
        while (!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }
    }*/
}
