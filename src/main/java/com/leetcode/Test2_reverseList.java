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

     //   ListNode current1 = reverseList(node1);
     //   ListNode current1 = reverseListIterate(node1);
        ListNode current1 = reverseListNew(node1, null);
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

    public static ListNode reverseListIterate(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode reverseListNew(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        return reverseListNew(next, cur);
    }
}
