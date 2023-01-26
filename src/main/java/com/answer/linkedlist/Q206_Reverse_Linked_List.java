package com.answer.linkedlist;

public class Q206_Reverse_Linked_List {

    public static void main(String[] args) {
      //  ListNode node6 = new ListNode(6, null);
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        //[1,2,3,4,5]
        ListNode node = reverseList_Recursive_3(node1);
        node.print();
    }
    /**
     * Iterative
     */
    public static ListNode reverseList_Iterative(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    /**
     * Recursive - from tail to head
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode newNode = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newNode;
    }
    /**
     * Recursive - from head to tail
     */
    public static ListNode reverseList_1(ListNode head) {
        return reverseList_fun(head, null );
    }

    public static ListNode reverseList_fun(ListNode curNode, ListNode previous ) {
        if(curNode == null) {
            return curNode;
        }
        ListNode next = curNode.next;
        ListNode nextNext = null;
        if(curNode.next != null) {
            nextNext= curNode.next.next;
            curNode.next.next = curNode;
        }
        curNode.next = previous;

        if(nextNext == null){
            if(next != null){
                return next;
            }else{
                return curNode;
            }
        }

        ListNode newNode = reverseList_fun(nextNext, next);
        return newNode;
    }
    /**
     * Recursive - from head to tail - more simple
     */
    public static ListNode reverseList_Recursive_3(ListNode head) {
        return reverse(null, head);
    }

    private  static ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;// 先保存下一个节点
        cur.next = prev;// 反转
        // 更新prev、cur位置
        // prev = cur;
        // cur = temp;

       // return reverse(cur, temp);
        ListNode newNode = reverse(cur, temp);
        return newNode;
    }
}
