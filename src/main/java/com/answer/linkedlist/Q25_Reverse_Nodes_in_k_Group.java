package com.answer.linkedlist;

import java.util.*;

public class Q25_Reverse_Nodes_in_k_Group {
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
