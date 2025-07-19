package com.answer.linkedlist;

public class Q707_Design_Linked_List_5 {
    /**
     * 方法一：带虚拟头结点的单链表
     * 感觉尾节点存在的意义不大，而且维护还麻烦
     */
    static class Node {
        private int val; //值
        private Node next;    //下一个结点
        public Node(int val) {
            this.val = val;
        }
        public Node() {
        }
    }

    private Node head; //指向虚拟头结点
    private Node tail;  //指向尾结点
    private int size;//当前链表的结点数量

    public Q707_Design_Linked_List_5() {
        this.head = new Node();//创建一个带头结点的
        this.tail = this.head;
        this.size = 0;
    }
    //头插
    public void addAtHead(int val) {
        this.size++;
        Node node = new Node(val);
        node.next = head.next;
        head.next = node;
        if (size == 1) {
            tail = node;
        }
    }
    //尾插
    public void addAtTail(int val) {
        this.size++;
        Node node = new Node(val);
        tail.next = node;
        tail = node;
    }
    //任意位置
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index < 0) {
            addAtHead(val);
            return;
        }
        Node cur = head;
        int i = 0;
        while (i < index) {    //找到index的前一个元素
            cur = cur.next;
            i++;
        }
        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
        this.size++;
    }
    //查找
    public int get(int index) {
        if (index > size - 1) { //合法性校验
            return -1;
        }
        Node cur = head.next;
        int i = 0;

        while (i < index) {  //找到位置
            cur = cur.next;
            i++;
        }
        return cur.val;
    }
    //删除
    public void deleteAtIndex(int index) {
        if (index > size - 1 || index < 0) {
            return;
        }
        Node cur = head;
        int i = 0;
        while (i < index) {  //找到前一个结点
            cur = cur.next;
            i++;
        }
        if (cur.next == tail) {//维护tail指针
            tail = cur;
        }
        if (cur.next != null) {//如果存在，指向待删除结点的下一个结点
            cur.next = cur.next.next;
        }
        this.size--;
    }
}
