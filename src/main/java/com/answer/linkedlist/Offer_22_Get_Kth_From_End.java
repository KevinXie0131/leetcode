package com.answer.linkedlist;

public class Offer_22_Get_Kth_From_End {

    /**
     * Two pointers
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode cur = head;
        ListNode end = head;
        while(k > 0){
            end = end.next;
            k--;
        }
        while(end != null){
            end = end.next;
            cur = cur.next;
        }
        return cur;
    }
    /**
     * Two pointers, but one loop
     */
    public ListNode getKthFromEnd_1(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        while(fast != null){
            if(count >= k){
                slow = slow.next;
            }
            fast = fast.next;
            count++;
        }
        return slow;
    }
}
