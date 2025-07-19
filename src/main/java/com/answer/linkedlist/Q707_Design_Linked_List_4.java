package com.answer.linkedlist;

public class Q707_Design_Linked_List_4 {
    //记录链表中元素的数量
    int size;
    //记录链表的虚拟头结点和尾结点
    DoublyListNode1 head, tail;

    public Q707_Design_Linked_List_4() {
        //初始化操作
        this.size = 0;
        this.head = new DoublyListNode1(0);
        this.tail = new DoublyListNode1(0);
        //这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        //判断index是否有效
        if(index >= size){
            return -1;
        }
        DoublyListNode1 cur = this.head;
        //判断是哪一边遍历时间更短
        if(index >= size / 2){
            //tail开始
            cur = tail;
            for(int i = 0; i < size-index; i++){
                cur = cur.prev;
            }
        }else{
            for(int i = 0; i <= index; i++){
                cur = cur.next;
            }
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        //等价于在第0个元素前添加
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        //等价于在最后一个元素(null)前添加
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if(index>size){  //index大于链表长度
            return;
        }
        size++;
        //找到前驱
        DoublyListNode1 pre = this.head;
        for(int i=0; i < index; i++){
            pre = pre.next;
        }
        //新建结点
        DoublyListNode1 newNode = new DoublyListNode1(val);
        newNode.next = pre.next;
        pre.next.prev = newNode;
        newNode.prev = pre;
        pre.next = newNode;

    }

    public void deleteAtIndex(int index) {
        if(index >= size){  //判断索引是否有效
            return;
        }
        //删除操作
        size--;
        DoublyListNode1 pre = this.head;
        for(int i=0; i<index; i++){
            pre = pre.next;
        }
        pre.next.next.prev = pre;
        pre.next = pre.next.next;
    }

}


//双链表
class DoublyListNode1{
    int val;
    DoublyListNode1 next, prev;
    DoublyListNode1() {};
    DoublyListNode1(int val){
        this.val = val;
    }
}
