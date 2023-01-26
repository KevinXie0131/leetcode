package com.answer.linkedlist;

public class Q2_Add_Two_Numbers {
    public static void main(String[] args) {
        // l1 = [2,4,3], l2 = [5,6,4]
        ListNode node3 = new ListNode(4, null);
        ListNode node2 = new ListNode(6, node3);
        ListNode node1= new ListNode(5,node2);

        ListNode node3a = new ListNode(3, null);
        ListNode node2a = new ListNode(4, node3a);
        ListNode node1a = new ListNode(2,node2a);

        ListNode result = addTwoNumbers_1(node1, node1a);
        result.print();
    }
    /**
     * Iterative
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, null);
        ListNode cur = dummy;
        int carry = 0;
        while(l1 != null || l2 != null){
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            cur.next = new ListNode(sum, null);
            cur = cur.next;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(carry > 0){
            cur.next = new ListNode(carry, null);
        }
        return dummy.next;
    }
    /**
     * Recursive
     */
    public static ListNode addTwoNumbers_1(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        int sum = l1.val + l2.val;
        ListNode head = new ListNode(sum % 10);
        head.next = addTwoNumbers_1(l1.next, l2.next);

        if(sum > 9) {
            head.next = addTwoNumbers_1(head.next, new ListNode(1));
        }
        return head;
    }
}
