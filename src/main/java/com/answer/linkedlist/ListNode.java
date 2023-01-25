package com.answer.linkedlist;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    void print(){
        ListNode cur = this;
        while(cur != null){
            System.out.print(cur.val + " -> " );
            cur = cur.next;
        }
        System.out.println();
    }
}