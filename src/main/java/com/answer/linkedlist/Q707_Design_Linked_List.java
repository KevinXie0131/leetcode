package com.answer.linkedlist;

public class Q707_Design_Linked_List {

    /**
     * Approach 1: Singly Linked List
     */
    int size;
    ListNode head;

    public Q707_Design_Linked_List() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if(index < 0 || index >= size){
            return -1;
        }
        ListNode cur=  head;
        for(int i = 0; i <= index; i++){
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index > size) return;
        index = Math.max(0, index);
        size++;
        ListNode cur=  head;
        for(int i = 0; i < index; i++){
            cur = cur.next;
        }
        ListNode newNode = new ListNode(val);
        newNode.next = cur.next;
        cur.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
        }
        size--;
        ListNode cur=  head;
        for(int i = 0; i < index; i++){
            cur = cur.next;
        }
        cur.next = cur.next.next;
    }

}
