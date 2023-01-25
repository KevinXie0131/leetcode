package com.answer.linkedlist;

public class Q160_Intersection_of_Two_Linked_Lists {

    /**
     * two pointers
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        while(curA != curB){
            if(curA != null){
                curA = curA.next;
            } else {
                curA = headB;
            }

            if(curB != null){
                curB = curB.next;
            } else {
                curB = headA;
            }
        }

        return curA;
    }
}
