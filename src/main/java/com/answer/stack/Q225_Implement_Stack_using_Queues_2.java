package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q225_Implement_Stack_using_Queues_2 {
    /**
     * 优化，使用一个 Deque 实现
     */
    Deque<Integer> que;

    public Q225_Implement_Stack_using_Queues_2() {
        que = new ArrayDeque<>();
    }

    public void push(int x) {
        int size = que.size();
        que.offer(x);
        for(int i= 0; i < size; i++){
            que.offer(que.poll());
        }
    }
    //每 offer 一个数（A）进来，都重新排列，把这个数（A）放到队列的队首
/*    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        //移动除了 A 的其它数
        while (size-- > 1)
            queue.offer(queue.poll());
    }*/

    public int pop() {
        return que.poll();
    }

    public int top() {
        return que.peek();
    }

    public boolean empty() {
        return que.isEmpty();
    }

    class MyStack {
        Deque<Integer> myQue1 = null;

        public MyStack() {
            myQue1 = new ArrayDeque<>();
        }

        public void push(int x) {
            myQue1.offer(x);
        }

        public int pop() {
            int size = myQue1.size(); // 将队列头部的元素（除了最后一个元素外） 重新添加到队列尾部
            while (size - 1 > 0) {
                myQue1.offer(myQue1.poll());
                size--;
            }
            int result = myQue1.poll();  // 此时弹出的元素顺序就是栈的顺序了

            return result;
        }

        public int top() {
            int size = myQue1.size();
            while (size - 1 > 0) {  // 将队列头部的元素（除了最后一个元素外） 重新添加到队列尾部
                myQue1.offer(myQue1.poll());
                size--;
            }
            int result = myQue1.peek(); // 此时获得的元素就是栈顶的元素了
            while (size > 0) {
                myQue1.offer(myQue1.poll());
                size--;
            }
            return result;
        }

        public boolean empty() {
            return myQue1.isEmpty();
        }
    }

}
