package com.leetcode;

public class Test2_reverseList {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        ListNode current = node1;
       while (current != null)
       {
           System.out.println(current.value);
           current = current.next;
       }

        ListNode current1 = reverseList(node1);
        while (current1 != null)
        {
            System.out.println(current1.value);
            current1 = current1.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
