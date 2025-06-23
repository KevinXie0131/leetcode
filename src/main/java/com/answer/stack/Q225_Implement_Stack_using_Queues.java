package com.answer.stack;

import java.util.*;

public class Q225_Implement_Stack_using_Queues {
    /**
     * 用队列实现栈
     * 仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
     * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
     * 实现 MyStack 类：
     *  void push(int x) 将元素 x 压入栈顶。
     *  int pop() 移除并返回栈顶元素。
     *  int top() 返回栈顶元素。
     *  boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
     * 注意：
     *  你只能使用队列的标准操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
     *  You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
     *  你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
     *  Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
     * 进阶：你能否仅用一个队列来实现栈。 Follow-up: Can you implement the stack using only one queue?
     *
     * 示例：
     *  输入：["MyStack", "push", "push", "top", "pop", "empty"]
     *        [[], [1], [2], [], [], []]
     *  输出：[null, null, null, 2, 2, false]
     *  解释：
     *  MyStack myStack = new MyStack();
     *  myStack.push(1);
     *  myStack.push(2);
     *  myStack.top(); // 返回 2
     *  myStack.pop(); // 返回 2
     *  myStack.empty(); // 返回 False
     */
    /**
     * 使用两个 Deque 实现
     */
    Deque<Integer> que1; // Deque 接口继承了 Queue 接口
    Deque<Integer> que2; // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
    /** Initialize your data structure here. */
    public Q225_Implement_Stack_using_Queues() {
        que1 = new ArrayDeque<>(); // 和栈中保持一样元素的队列
        que2 = new ArrayDeque<>(); // 辅助队列
    }
    /** Push element x onto stack. */
    public void push(int x) {
        que1.offer(x);
    }
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = que1.size();
        size--;
        while(size > 0){ // 将 que1 导入 que2 ，但留下最后一个值
            que2.offer(que1.poll());
            size--;
        }
        int result = que1.poll();
        // 将 que2 对象的引用赋给了 que1 ，此时 que1，que2 指向同一个队列
        que1 = que2;
        // 如果直接操作 que2，que1 也会受到影响，所以为 que2 分配一个新的空间
        que2 = new ArrayDeque<>();
        return result;
    }
    /** Get the top element. */
    public int top() {
        return que1.peekLast();
    }
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return que1.isEmpty();
    }
    /**
     * 使用两个 Queue 实现方法3
     * 一个队列在模拟栈弹出元素的时候只要将队列头部的元素（除了最后一个元素外） 重新添加到队列尾部，此时再去弹出元素就是栈的顺序了。
     * 时间复杂度: pop为O(n)，top为O(n)，其他为O(1)
     * 空间复杂度: O(n)
     */
    class MyStack0 {
        Deque<Integer> myQue1 = null;
        Deque<Integer> myQue2 = null;

        public MyStack0() {
            myQue1 = new ArrayDeque<>();
            myQue2 = new ArrayDeque<>();
        }

        public void push1(int x) {
            myQue1.offer(x);
        }

        public int pop1() {
            int size = myQue1.size(); // 将队列头部的元素（除了最后一个元素外） 重新添加到队列尾部
            while (size - 1 > 0) {
                myQue2.offer(myQue1.poll());
                size--;
            }
            int result = myQue1.poll();  // 此时弹出的元素顺序就是栈的顺序了
            while (!myQue2.isEmpty()) {
                myQue1.offer(myQue2.poll());
            }
            return result;
        }

        public int top1() {
            int size = myQue1.size();
            while (size - 1 > 0) {  // 将队列头部的元素（除了最后一个元素外） 重新添加到队列尾部
                myQue2.offer(myQue1.poll());
                size--;
            }
            int result = myQue1.poll(); // 此时获得的元素就是栈顶的元素了
            while (!myQue2.isEmpty()) {
                myQue1.offer(myQue2.poll());
            }
            myQue1.offer(result);// 将获取完的元素也重新添加到队列尾部，保证数据结构没有变化
            return result;
        }

        public boolean empty1() {
            return myQue1.isEmpty();
        }
    }
    /**
     * 使用两个 Queue 实现方法1
     */
    class MyStack1 {

        Queue<Integer> queue1; // 和栈中保持一样元素的队列
        Queue<Integer> queue2; // 辅助队列

        /** Initialize your data structure here. */
        public MyStack1() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue2.offer(x); // 先放在辅助队列中
            while (!queue1.isEmpty()){
                queue2.offer(queue1.poll());
            }
            Queue<Integer> queueTemp;
            queueTemp = queue1;
            queue1 = queue2;
            queue2 = queueTemp; // 最后交换queue1和queue2，将元素都放到queue1中
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll(); // 因为queue1中的元素和栈中的保持一致，所以这个和下面两个的操作只看queue1即可
        }

        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }
    /**
     * 使用两个 Queue 实现方法2
     */
    class MyStack2 {
        //q1作为主要的队列，其元素排列顺序和出栈顺序相同
        Queue<Integer> q1 = new ArrayDeque<>();
        //q2仅作为临时放置
        Queue<Integer> q2 = new ArrayDeque<>();

        public MyStack2() {

        }
        //在加入元素时先将q1中的元素依次出栈压入q2，然后将新加入的元素压入q1，再将q2中的元素依次出栈压入q1
        public void push(int x) {
            while (q1.size() > 0) {
                q2.add(q1.poll());
            }
            q1.add(x);
            while (q2.size() > 0) {
                q1.add(q2.poll());
            }
        }

        public int pop() {
            return q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }
}
