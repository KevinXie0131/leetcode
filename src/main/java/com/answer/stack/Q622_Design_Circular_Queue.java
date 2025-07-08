package com.answer.stack;

import com.leetcode.ListNode;

import java.util.*;

public class Q622_Design_Circular_Queue {
    /**
     * 设计循环队列
     * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
     * The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
     * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
     * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue.
     * But using the circular queue, we can use the space to store new values.
     *
     * 你的实现应该支持如下操作：
     *  MyCircularQueue(k): 构造器，设置队列长度为 k 。
     *  Front: 从队首获取元素。如果队列为空，返回 -1 。
     *  Rear: 获取队尾元素。如果队列为空，返回 -1 。
     *  enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
     *  deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
     *  isEmpty(): 检查循环队列是否为空。
     *  isFull(): 检查循环队列是否已满。
     * 示例：
     *  MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
     *  circularQueue.enQueue(1);  // 返回 true
     *  circularQueue.enQueue(2);  // 返回 true
     *  circularQueue.enQueue(3);  // 返回 true
     *  circularQueue.enQueue(4);  // 返回 false，队列已满
     *  circularQueue.Rear();  // 返回 3
     *  circularQueue.isFull();  // 返回 true
     *  circularQueue.deQueue();  // 返回 true
     *  circularQueue.enQueue(4);  // 返回 true
     *  circularQueue.Rear();  // 返回 4
     *
     */
    int front;
    int rear;
    int capacity;
    int[] elements;
    /**
     * 通过一个数组进行模拟
     */
    public Q622_Design_Circular_Queue(int k) {
        // 不要让循环队列装满，于是判断(rear + 1) % len == head (即空出来一个元素)，但是这样就会导致少一个元素，所以把整个循环队列的长度在实现时隐式 + 1
        capacity = k + 1; // 数组的空间初始化大小为 k+1 (不要求我们实现动态扩容与动态缩容)
        front = rear = 0;
        elements = new int[capacity];
    }

    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        elements[rear] = value;
        rear = (rear + 1) % capacity; // 指向队列尾部（即最后 1 个有效数据）的下一个位置，即下一个从队尾入队元素的位置。
        return true;
    }

    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        front = (front + 1) % capacity;
        return true;
    }

    public int Front() {
        if(isEmpty()){
            return -1;
        }
        return elements[front];
    }

    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        return elements[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return rear == front;
    }
    // 在循环队列中，当队列为空，可知 front=rear；而当所有队列空间全占满时，也有 front=rear。为了区别这两种情况，
    // 假设队列使用的数组有 capacity 个存储空间，则此时规定循环队列最多只能有capacity−1 个队列元素，
    // 当循环队列中只剩下一个空存储单元时，则表示队列已满
    // 队列当前的长度：(rear − front + capacity) mod capacity
    public boolean isFull() {
        return ((rear + 1) % capacity) == front; // 为了避免「队列为空」和「队列为满」的判别条件冲突，我们有意浪费了一个位置。
    }
    /**
     * 多定义一个size变量
     */
    int[] queue;
    int l, r, size1, limit;

    public void MyCircularQueue2(int k) {
        queue = new int[k];
        l = r = size1 = 0;
        limit = k;
    }

    public boolean enQueue2(int value) {
        if(isFull2()){
            return false;
        }
        queue[r] = value;
        r = r == limit - 1 ? 0 : r + 1;
        size1++;
        return true;
    }

    public boolean deQueue2() {
        if(isEmpty2()){
            return false;
        }
        l = l == limit - 1 ? 0 : l + 1; // l = (l + 1) % limit;
        size1--;
        return true;
    }

    public int Front2() {
        if(isEmpty2()){
            return -1;
        }
        return queue[l];
    }

    public int Rear2() {
        if(isEmpty2()){
            return -1;
        }
        int last = r == 0 ? limit - 1 : r - 1; // int last =  (r-1 + limit) % limit;
        return queue[last];
    }

    public boolean isEmpty2() {
        return size1 == 0;
    }

    public boolean isFull2() {
        return size1 == limit;
    }
    /**
     * 可以用链表实现队列，用链表实现队列则较为简单, 因为链表可以在 O(1) 时间复杂度完成插入与删除。
     */
    private ListNode head; // 链表的头节点，队列的头节点。
    private ListNode tail; // 链表的尾节点，队列的尾节点。
    private int capacity1;  // 队列的容量，即队列可以存储的最大元素数量。
    private int size;      // 队列当前的元素的数量。

    public void MyCircularQueue(int k) {
        capacity1 = k;
        size = 0;
    }

    public boolean enQueue1(int value) {
        if (isFull1()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    public boolean deQueue1() {
        if (isEmpty1()) {
            return false;
        }
        ListNode node = head;
        head = head.next;
        size--;
        return true;
    }

    public int Front1() {
        if (isEmpty1()) {
            return -1;
        }
        return head.value;
    }

    public int Rear1() {
        if (isEmpty1()) {
            return -1;
        }
        return tail.value;
    }

    public boolean isEmpty1() {
        return size == 0;
    }

    public boolean isFull1() {
        return size == capacity1;
    }
    /**
     * 双端队列模拟
     */
    private Deque<Integer> q;
    private int n;

    public void MyCircularQueue3(int k) {
        n = k;
        q = new ArrayDeque<>(); // 初始化队列
    }
    public boolean enQueue3(int value) {
        if (q.size() == n) {
            return false; // 如果队列满了
        }
        q.addLast(value); // 入队，添加到队尾
        return true;
    }

    public boolean deQueue3() {
        if (isEmpty3()) {
            return false; // 队列为空，无法出队
        }
        q.pollFirst(); // 出队，从队首移除
        return true;
    }
    public int Front3() {
        if (isEmpty3()) {
            return -1; // 队列为空，返回 -1
        }
        return q.peekFirst(); // 获取队首元素
    }

    public int Rear3() {
        if (isEmpty3()) {
            return -1; // 队列为空，返回 -1
        }
        return q.peekLast(); // 获取队尾元素
    }

    public boolean isEmpty3() {
        return q.isEmpty(); // 正确方法
    }

    public boolean isFull3() {
        return q.size() == n; // 判断是否满了
    }
}
