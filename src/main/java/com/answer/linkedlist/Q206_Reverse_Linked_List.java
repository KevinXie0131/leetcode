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
     * Iterative 双指针法
     */
    public ListNode reverseList_Iterative1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null; // 保存cur的下一个节点
        while (cur != null) {
            temp = cur.next; // 保存一下 cur的下一个节点，因为接下来要改变cur->next
            cur.next = prev; // 翻转操作
            // 更新pre 和 cur指针
            prev = cur;
            cur = temp;
        }
        return prev;
    }
    /**
     * Recursive - from tail to head // 从后向前递归
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {  // 边缘条件判断
            return head;
        }
        // 递归调用，翻转第二个节点开始往后的链表
        ListNode last  = reverseList(head.next);
        // 翻转头节点与第二个节点的指向
        head.next.next = head;
        // 此时的 head 节点为尾节点，next 需要指向 NULL
        head.next = null;

        return last ;
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
