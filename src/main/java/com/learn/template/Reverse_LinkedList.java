package com.learn.template;

import com.leetcode.ListNode;

public class Reverse_LinkedList {
    /**
     * 反转链表
     */
    public ListNode fn(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return prev;
    }
}
