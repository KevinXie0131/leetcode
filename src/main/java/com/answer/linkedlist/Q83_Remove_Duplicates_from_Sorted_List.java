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

    /**
     * Recursive - from tail to head
     */
    public static ListNode deleteDuplicates_Recursive_1(ListNode head) {
        return deleteDuplicates_Recursive_func(head, Integer.MAX_VALUE);
    }

    public static ListNode deleteDuplicates_Recursive_func(ListNode head, int value) {
        if(head == null){
            return head;
        }

        head.next = deleteDuplicates_Recursive_func(head.next, head.val);

        if(head.val == value){
            return head.next;
        }else{
            return head;
        }

    }
}
