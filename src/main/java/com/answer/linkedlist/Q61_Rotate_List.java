package com.answer.linkedlist;

import java.util.*;

public class Q61_Rotate_List {
    /**
     * 旋转链表
     * 一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * Given the head of a linked list, rotate the list to the right by k places.
     * 示例 1：
     *  输入：head = [1,2,3,4,5], k = 2
     *  输出：[4,5,1,2,3]
     * 示例 2：
     *  输入：head = [0,1,2], k = 4
     *  输出：[2,0,1]
     */
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode result = rotateRight1(node1, 2);
        result.print();
    }
    /**
     * 每移动一次,把头节点连接到尾节点
     */
    static public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;

        ListNode last = head;
        int length = 1;
        while(last.next != null){
            length++; // 统计节点个数
            last = last.next;
        }

        k = k % length;  // 计算实际需要旋转的步数
        // 节点向右移动 k 个位置  ===>  相当于 向左移动 (len-k) 个位置, 节点向左移动 就比较好写
        // 2 -> 3 -> 4 -> 5 -> 1 ->
        // 3 -> 4 -> 5 -> 1 -> 2 ->
        // 4 -> 5 -> 1 -> 2 -> 3 ->
        while(k < length){
            last.next = head; // 尾节点 接 头节点
            head = head.next; // 头节点 到下一个 节点
            last = last.next; // 更新尾节点
            last.next = null; // 设为null
            k++;
        }
        return head;
    }
    /**
     * 将要旋转的拆分下来 新链表的尾节点的next 节点为原来链表的 head节点 然后断开原来链表的尾节点和新节点的连接。
     */
    static public ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {   // 处理链表为空或者 k 为 0 的情况
            return head;
        }
        // 计算链表的长度
        int size = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        }

        k = k % size;    // 计算实际需要旋转的步数
        if (k == 0) {
            return head;
        }

        ListNode newTail = head; // 找到新的链表尾
        for (int i = 0; i < size - k - 1; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;     // 找到新的链表头
        newTail.next = null;  // 断开链表并重新连接
        tail.next = head; // 原来的尾结点指向原来的头结点，形成环
        return newHead;
    }
    /**
     * another from
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        if(k == 0) return head;
        ListNode tail = head;

        int len = 1; // 链表长度
        while (tail.next != null){ // 找到链表的尾结点 之后指向头节点构成环
            tail = tail.next;
            len++;
        }
        tail.next = head;
        // 之后从尾结点出发 移动 n-k%n 找到新的尾结点newtail(k%n避免无效旋转)
        for(int i = 0;i < (len - k % len); i++){
            tail = tail.next;
        }
        ListNode newtail = tail;
        ListNode newhead = tail.next;
        newtail.next = null; // 断开环形链表，让新尾节点的 next 指向 null
        return newhead; // 返回新的头节点
    }
    /**
     * 解题思路
     * 1.先走到链表尾，尾连头形成一个环。
     * 2.根据k计算位置，去到相应的位置断开即可。
     * 3.返回头指针。
     */
    public ListNode rotateRight5(ListNode head, int k) {
        if(head == null){
            return head;
        }
        ListNode temp = head;
        int len = 1;
        while (temp.next != null){
            len += 1;
            temp = temp.next;
        }
        temp.next = head;

        k = k % len;
        for (int i = 0; i < len - k; i++) {
            temp = temp.next;
        }
        head = temp.next;
        temp.next = null;
        return head;

    }
    /**
     * 集合
     */
    public ListNode rotateRight3(ListNode head, int k) {
        if(k == 0 || head == null || head.next == null) return head;
        List<ListNode> list = new ArrayList<>();
        ListNode li = head;
        while (li != null) {
            list.add(li);
            li = li.next;
        }
        int len = list.size();
        k %= len;
        if(k == 0){
            return list.get(0);
        }
        list.get(len - 1).next = list.get(0);
        list.get(len - k - 1).next = null;
        return list.get(len - k);
    }
}
