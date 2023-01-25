package com.answer.linkedlist;

public class Q83_Remove_Duplicates_from_Sorted_List {

    /**
     * Iterative
     * Approach 1: Straight-Forward Approach
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }

        return head;
    }
}
