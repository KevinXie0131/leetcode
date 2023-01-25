package com.answer.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Q141_Linked_List_Cycle {

    /**
     * fast-slow pointers
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
    /**
     * Use set
     */
    public boolean hasCycle_1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(!set.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
