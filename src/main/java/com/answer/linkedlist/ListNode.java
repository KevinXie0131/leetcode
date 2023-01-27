package com.answer.linkedlist;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void print(){
        ListNode cur = this;
        while(cur != null){
            System.out.print(cur.val + " -> " );
            cur = cur.next;
        }
        System.out.println();
    }
}