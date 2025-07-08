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
    int front; // 队列首元素对应的数组的索引。
    int rear;  // 队列尾元素对应的索引的下一个索引。
    int capacity;
    int[] elements;
    /**
     * 数组实现: 利用循环队列实现双端队列
     */
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
        rear = (rear + 1) % capacity; // 指向队列尾部（即最后 1 个有效数据）的 下一个位置，即下一个从队尾入队元素的位置
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()){
            return false;
        }
        front = (front + 1) % capacity;  // front 被设计在数组的开头，所以是 +1
        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()){
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;   // rear 被设计在数组的末尾，所以是 -1
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
        return elements[(rear - 1 + capacity) % capacity];  // 当 rear 为 0 时防止数组越界
    }

    public boolean isEmpty() {
        return rear == front;//队列判空的条件是 front = rear
    }

    public boolean isFull() { // 注意：这个设计是非常经典的做法
        return ((rear + 1) % capacity) == front; // 队列判满的条件是 front = (rear + 1) mod capacity
    }
    /**
     * 多定义一个size变量
     */
    int[] queue;
    int l, r, size1, limit;

    public void MyCircularDeque2(int k) {
        queue = new int[k];
        l = r = size1 = 0;
        limit = k;
    }

    public boolean insertFront2(int value) {
        if(isFull2()){
            return false;
        }
        l = (l - 1 + limit) % limit;
        queue[l] = value;
        size1++;
        return true;
    }

    public boolean insertLast2(int value) {
        if(isFull2()){
            return false;
        }
        queue[r] = value;
        r = (r + 1) % limit;
        size1++;
        return true;
    }

    public boolean deleteFront2() {
        if(isEmpty2()){
            return false;
        }
        l = (l + 1) % limit;
        size1--;
        return true;
    }

    public boolean deleteLast2() {
        if(isEmpty2()){
            return false;
        }
        r = (r - 1 + limit) % limit;
        size1--;
        return true;
    }

    public int getFront2() {
        if(isEmpty2()){
            return -1;
        }
        return queue[l];
    }

    public int getRear2() {
        if(isEmpty2()){
            return -1;
        }
        int last =  (r - 1 + limit) % limit;
        return queue[last];
    }

    public boolean isEmpty2() {
        return size1 == 0;
    }

    public boolean isFull2() {
        return size1 == limit;
    }
    /**
     * 链表实现: 使用双向链表来模拟双端队列
     */
    private class DLinkListNode {
        int val;
        DLinkListNode prev, next;

        DLinkListNode(int val) {
            this.val = val;
        }
    }

    private DLinkListNode head, tail;
    private int capacity1;
    private int size;

    public void MyCircularDeque3(int k) {
        capacity1 = k;
        size = 0;
    }

    public boolean insertFront3(int value) {
        if (size == capacity1) {
            return false;
        }
        DLinkListNode node = new DLinkListNode(value);
        if (size == 0) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
        return true;
    }

    public boolean insertLast3(int value) {
        if (size == capacity1) {
            return false;
        }
        DLinkListNode node = new DLinkListNode(value);
        if (size == 0) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
        return true;
    }

    public boolean deleteFront3() {
        if (size == 0) {
            return false;
        }
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
        return true;
    }

    public boolean deleteLast3() {
        if (size == 0) {
            return false;
        }
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        size--;
        return true;
    }
    /**
     * 方便垃圾回收
     */
    public boolean deleteFront3_() {
        //判断是否为空
        if (isEmpty()) {
            return false;
        }
        size--;
        DLinkListNode next = head.next;
        //让删除节点指向null 方便垃圾回收
        head.next = null;
        if (next != null) {
            next.prev = null;
        }
        head = next;
        return true;
    }

    public boolean deleteLast3_a() {
        //判断是否为空
        if (isEmpty()) {
            return false;
        }
        size--;
        //让删除节点指向null 方便垃圾回收
        DLinkListNode pre = tail.prev;
        if (pre != null) {
            pre.next = null;
        }
        tail.prev = null;
        tail = pre;
        return true;
    }
    /**
     * end
     */
    public int getFront3() {
        if (size == 0) {
            return -1;
        }
        return head.val;
    }

    public int getRear3() {
        if (size == 0) {
            return -1;
        }
        return tail.val;
    }

    public boolean isEmpty3() {
        return size == 0;
    }

    public boolean isFull3() {
        return size == capacity1;
    }
    /**
     * 双向链表: 可以声明一个虚拟节点，这样可以极大地减少我们的操作。
     */
    private int capacity4;
    private int size4;
    Node head4, tail4;

    public void MyCircularDeque4(int k) {
        this.capacity4 = k;
        this.size4 = 0;
        // 虚拟节点
        Node dummy = new Node();
        dummy.next = dummy;
        dummy.prev = dummy;
        this.head4 = this.tail4 = dummy;
    }

    public boolean insertFront4(int value) {
        if (isFull4()) {
            return false;
        }
        Node node = new Node();
        node.value = value;

        insert(head4, head4.next, node);
        return true;
    }

    public boolean insertLast4(int value) {
        if (isFull4()) {
            return false;
        }
        Node node = new Node();
        node.value = value;

        insert(tail4.prev, tail4, node);
        return true;
    }

    public boolean deleteFront4() {
        if (isEmpty4()) {
            return false;
        }

        delete(head4.next);
        return true;
    }

    public boolean deleteLast4() {
        if (isEmpty4()) {
            return false;
        }

        delete(tail4.prev);
        return true;
    }

    public int getFront4() {
        if (isEmpty4()) {
            return -1;
        }
        return head4.next.value;
    }

    public int getRear4() {
        if (isEmpty4()) {
            return -1;
        }
        return tail4.prev.value;
    }

    public boolean isEmpty4() {
        return size4 == 0;
    }

    public boolean isFull4() {
        return size4 == capacity4;
    }

    private void insert(Node prev, Node next, Node node) {
        prev.next = node;
        node.prev = prev;
        node.next = next;
        next.prev = node;

        size++;
    }

    private void delete(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;

        size--;
    }

    class Node {
        int value;
        Node prev, next;
    }
}
