package com.answer.linkedlist;

import javax.imageio.stream.ImageInputStream;

public class Q203_Remove_Linked_List_Elements {

    public static void main(String[] args) {
        ListNode node7 = new ListNode(6, null);
        ListNode node6 = new ListNode(5, node7);
        ListNode node5 = new ListNode(4, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(6, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
      //  [1,2,6,3,4,5,6]
        int val = 6;

        ListNode node = removeElements(node1, val);
        node.print();
    }
    /**
     * Iterative
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1 , head);
        ListNode pre = dummy;
        ListNode cur = head;

        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * Recursive
     */
    public ListNode removeElements_1(ListNode head, int val) {
        if(head == null){
            return null;
        }
        head.next = removeElements(head.next, val);
        if(head.val == val){
            return head.next;
        }else{
            return head;
        }
    }
}
