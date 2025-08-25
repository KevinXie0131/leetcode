package com.answer.linkedlist;

public class Q707_Design_Linked_List_1 {

    public static void main(String[] args) {
        Q707_Design_Linked_List_1 myLinkedList = new Q707_Design_Linked_List_1();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        int result = myLinkedList.get(1);              // return 2
        System.out.println(result);
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        result = myLinkedList.get(1);              // return 3
        System.out.println(result);
    }

    int size;
    DoublyListNode head;
    DoublyListNode tail;

    public Q707_Design_Linked_List_1() {
        size = 0;
        head = new DoublyListNode(0); // 需要一个哨兵节点作为头节点 head 和一个哨兵节点作为尾节点 tail
        tail = new DoublyListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        if(index < 0 || index > size){
            return -1;
        }
        DoublyListNode cur =  head;
     //   if(index < size - index){
            for(int i = 0; i <= index; i++){
                cur = cur.next;
            }
   /*     }else{
            cur = tail;
            for(int i = 0; i < size - index; i++){
                cur = cur.prev;
            }
        }*/
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index > size) {
            return;
        }
        index = Math.max(0, index);
        size++;
        DoublyListNode pred, succ;
      //  if (index < size - index) {
            pred = head;
            for(int i = 0; i < index; i++){
                pred = pred.next;
            }
            succ = pred.next;
      /*  } else {
            succ = tail;
            for(int i = 0; i < size - index; i++){
                succ = succ.prev;
            }
            pred = succ.prev;
        }*/

        DoublyListNode newNode = new DoublyListNode(val);
        newNode.prev = pred;
        newNode.next = succ;
        pred.next = newNode;
        succ.prev = newNode;
    }

    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size){
            return;
        }
        size--;
        DoublyListNode pred, succ;
     //   if (index < size - index) {
            pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            succ = pred.next.next;
     /*   } else {
            succ = tail;
            for (int i = 0; i < size - index - 1; i++) {
                succ = succ.prev;
            }
            pred = succ.prev.prev;
        }*/
        size--;
        pred.next = succ;
        succ.prev = pred;
    }
}

class DoublyListNode {
    int val;
    DoublyListNode next;
    DoublyListNode prev;

    public DoublyListNode(int val) {
        this.val = val;
    }
}
