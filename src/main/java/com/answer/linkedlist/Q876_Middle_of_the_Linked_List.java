package com.answer.linkedlist;

public class Q876_Middle_of_the_Linked_List {

    /**
     * fast-slow pointers
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
