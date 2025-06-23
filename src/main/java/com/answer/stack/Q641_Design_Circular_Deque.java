package com.answer.stack;

public class Q641_Design_Circular_Deque {
    /**
     * 设计循环双端队列
     * 设计实现双端队列。Design your implementation of the circular double-ended queue (deque).
     * 实现 MyCircularDeque 类:
     *  MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
     *  boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
     *  boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
     *  boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
     *  boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
     *  int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
     *  int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
     *  boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
     *  boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
     *
     * 示例 1：
     * 输入 ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
     *      [[3], [1], [2], [3], [4], [], [], [], [4], []]
     * 输出 [null, true, true, true, false, 2, true, true, true, 4]
     *
     * 解释
     *  MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
     *  circularDeque.insertLast(1);			        // 返回 true
     *  circularDeque.insertLast(2);			        // 返回 true
     *  circularDeque.insertFront(3);			        // 返回 true
     *  circularDeque.insertFront(4);			        // 已经满了，返回 false
     *  circularDeque.getRear();  				// 返回 2
     *  circularDeque.isFull();				        // 返回 true
     *  circularDeque.deleteLast();			        // 返回 true
     *  circularDeque.insertFront(4);			        // 返回 true
     *  circularDeque.getFront();				// 返回 4
     */
    int front;
    int rear;
    int capacity;
    int[] elements;

    public Q641_Design_Circular_Deque(int k) {
        capacity = k + 1;
        front = rear = 0;
        elements = new int[capacity];
    }

    public boolean insertFront(int value) {
        if(isFull()){
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        elements[front] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()){
            return false;
        }
        elements[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()){
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()){
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        return true;
    }

    public int getFront() {
        if(isEmpty()){
            return -1;
        }
        return elements[front];
    }

    public int getRear() {
        if(isEmpty()){
            return -1;
        }
        return elements[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return ((rear + 1) % capacity) == front;
    }
}
