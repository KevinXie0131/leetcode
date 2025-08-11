package com.answer.linkedlist;

public class LinkedListTemplate {
    /**
     * 递归
     * 反转链表
     */
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head; // 把下一个节点指向自己
        head.next = null; // 断开指向下一个节点的连接，保证最终链表的末尾节点的 next 是空节点
        return newHead;
    }
    /**
     * 迭代
     * 反转链表: 视频讲解 https://www.bilibili.com/video/BV1sd4y1x7KN/
      */
    private ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }


}
