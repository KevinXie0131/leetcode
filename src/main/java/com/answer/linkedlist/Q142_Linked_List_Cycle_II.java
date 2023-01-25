package com.answer.linkedlist;

public class Q142_Linked_List_Cycle_II {

    public static void main(String[] args) {

        ListNode head = new ListNode(1, null);
        ListNode result =  detectCycle(head);
        System.out.println(result);
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }
}
