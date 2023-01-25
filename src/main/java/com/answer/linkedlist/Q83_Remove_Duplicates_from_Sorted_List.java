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
     * Use two nodes
     */
    public ListNode deleteDuplicates_1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                int val = cur.val;

                while(cur != null && cur.val == val){
                    cur = cur.next;
                }
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
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
    /**
     * Recursive - from haed to tail
     */
    public ListNode deleteDuplicates_Recursive(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        if(head.val == head.next.val){
            return deleteDuplicates(head.next);
        }else{
            head.next = deleteDuplicates(head.next);
        }

        return head;
    }
}
