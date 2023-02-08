package com.answer.math.carry;

import  com.answer.linkedlist.ListNode;

public class Q369_Plus_One_Linked_List {

    public static void main(String[] args) {
        //head = [1,2,3]
/*        ListNode node3 = new ListNode(9, null);
        ListNode node2 = new ListNode(9, node3);
        ListNode node1= new ListNode(1,node2);*/

     /*   ListNode node1= new ListNode(9,null);*/

        ListNode node4 = new ListNode(9, null);
        ListNode node3 = new ListNode(9, node4);
        ListNode node2 = new ListNode(9, node3);
        ListNode node1= new ListNode(8,node2);

        ListNode res = plusOne_1(node1);
        res.print();
    }

    /**
     * it doesn't work for [9]
     */
    public static ListNode plusOne(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode newNode = plusOne(head.next);

        if(newNode == null || newNode.val == 10){
            head.val +=  1;
            head.val %= 10;
            if(head.val == 0){
                return new ListNode(10, head.next);
            } else {
                return head;
            }
        }
        return head;
    }
    /**
     * it works well
     */
    static int carryOver = 0;
    public static ListNode plusOne_1(ListNode head) {
        helper(head);
        if(carryOver > 0){
            ListNode newNode = new ListNode(1, head);
            return newNode;
        }
        return head;
    }
    public static ListNode helper(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode newNode = helper(head.next);

        if(newNode == null){
            carryOver = (head.val + 1) / 10;
            head.val = (head.val + 1) % 10;
        }else{
            int sum = head.val + carryOver;
            head.val = sum % 10;
            carryOver = sum / 10;
        }
        return head;
    }
    /**
     *
     */
    int carry = 0;
    public ListNode plusOne_2(ListNode head) {
        help(head);
        if(carry > 0){
            ListNode newNode = new ListNode(1, head);
            return newNode;
        }
        return head;
    }

    public void help(ListNode head){
        if(head == null){
            carry = 1;
            return;
        }
        help(head.next);
        if(carry == 1){
            carry = (head.val + 1 ) / 10;
            head.val = (head.val + 1 ) % 10;
        }

        return;
    }
}
