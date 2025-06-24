package com.answer.linkedlist;

import java.util.*;

public class Q25_Reverse_Nodes_in_k_Group { // Hard 困难
    /**
     * K 个一组翻转链表
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
     * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
     * You may not alter the values in the list's nodes, only nodes themselves may be changed.
     *
     * 示例 1：
     *  输入：head = [1,2,3,4,5], k = 2
     *  输出：[2,1,4,3,5]
     * 示例 2：
     *  输入：head = [1,2,3,4,5], k = 3
     *  输出：[3,2,1,4,5]
     *
     */
    public static void main(String[] args) {
        ListNode node7 = new ListNode(7, null);
        ListNode node6 = new ListNode(6, node7);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        //[1,2,3,4,5]
        ListNode node = reverseKGroup_1(node1, 3);
        node.print();
    }
    /**
     * Iterative
     * 把整个链表按照 k 个一组截断，然后把每组链表翻转后再拼接。
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre =dummy;
        ListNode cur = dummy;
        while(cur.next != null){
            for(int i = 0; i < k && cur != null; i++){ // 先走k步 cur不为空
                cur = cur.next;
            }
            if(cur == null) break;

            ListNode start = pre.next; // 确定边界
            ListNode end = cur.next;
            cur.next = null; // 断开链表
            pre.next = reverseList(start); // 反转链表
            start.next = end; // 拼接链表

            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    public static ListNode reverseList(ListNode head) { // 反转链表
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    /**
     * Use stack
     */
    public static ListNode reverseKGroup_1(ListNode head, int k) {
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (true) {
            int count = 0;
            ListNode tmp = head;
            while (tmp != null && count < k) {
                stack.add(tmp);
                tmp = tmp.next;
                count++;
            }
            if (count != k) {
                p.next = head;
                break;
            }
            while (!stack.isEmpty()){
                p.next = stack.pollLast();
                p = p.next;
            }
            p.next = tmp;
            head = tmp;
        }
        return dummy.next;
    }
}
