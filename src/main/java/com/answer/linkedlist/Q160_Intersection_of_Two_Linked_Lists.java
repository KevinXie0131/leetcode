package com.answer.linkedlist;

import java.util.HashSet;
import java.util.Set;

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
    /**
     * Use set
     */
    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode cur = headA;
        while(cur != null){
            visited.add(cur);
            cur = cur.next;
        }

        cur = headB;
        while(cur != null){
            if(visited.contains(cur)){
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }
}
