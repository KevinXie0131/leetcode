package com.answer.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Q142_Linked_List_Cycle_II {

    public static void main(String[] args) {

        ListNode head = new ListNode(1, null);
        ListNode result =  detectCycle(head);
        System.out.println(result);
    }

    /**
     * fast-slow pointers
     */
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
    /**
     * Use set
     */
    public ListNode detectCycle_1(ListNode head) {
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }else{
                set.add(cur);
            }
            cur = cur.next;
        }
        return null;
    }
}
