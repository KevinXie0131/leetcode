package com.answer.linkedlist;

public class Q19_Remove_Nth_Node_From_End_of_List {

    /**
     * fast-slow pointers
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode (-1, head);
        ListNode slow = dummy;
        ListNode fast = head;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }
}
