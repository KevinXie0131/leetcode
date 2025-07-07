package com.answer.stack;

import java.util.*;

public class Q232_Implement_Queue_using_Stacks {
    /**
     * 用栈实现队列
     * 仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
     * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
     * 实现 MyQueue 类：
     *  void push(int x) 将元素 x 推到队列的末尾
     *  int pop() 从队列的开头移除并返回元素
     *  int peek() 返回队列开头的元素
     *  boolean empty() 如果队列为空，返回 true ；否则，返回 false
     * 说明：
     *  你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     *  你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.
     * 进阶：你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
     *
     * 示例 1：
     *  输入：["MyQueue", "push", "push", "peek", "pop", "empty"]
     *        [[], [1], [2], [], [], []]
     *  输出：[null, null, null, 1, 1, false]
     *  解释：
     *  MyQueue myQueue = new MyQueue();
     *  myQueue.push(1); // queue is: [1]
     *  myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
     *  myQueue.peek(); // return 1
     *  myQueue.pop(); // return 1, queue is [2]
     *  myQueue.empty(); // return false
     */
    /**
     * 双栈: 将一个栈当作输入栈，用于压入 push 传入的数据；另一个栈当作输出栈，用于 pop 和 peek 操作
     * 每次 pop 或 peek 时，若输出栈为空则将输入栈的全部数据依次弹出并压入输出栈，这样输出栈从栈顶往栈底的顺序就是队列从队首往队尾的顺序。
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
    // 当出队栈存在内容时，出队栈的栈顶，即为第一个出队的元素。
    // 若出队栈无元素，我们的需求又是出队的话，我们就需要将入队栈的内容反序导入出队栈，然后弹出栈顶即可。
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
