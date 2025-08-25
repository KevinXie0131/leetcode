package com.answer.linkedlist;

import java.util.*;

public class Q203_Remove_Linked_List_Elements {
    /**
     * 移除链表元素
     * 一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
     *
     * 示例 1：
     *  输入：head = [1,2,6,3,4,5,6], val = 6
     *  输出：[1,2,3,4,5]
     */
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
        ListNode node = removeElements_recursive_1(node1, val);
        node.print();
    }
    /**
     * Iterative / fast-slow pointers 迭代
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1 , head);
        ListNode pre = dummy; // 使用dummy节点 (因为头节点也有可能被删)
        ListNode cur = head;

        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next; // cur指针指向的节点被跳过
            }else{
                pre = cur;
            }
            cur = cur.next; // cur指针一直往前走
        }
        return dummy.next;
    }
    /**
     * Iterative / dummy header 迭代
     */
    public static ListNode removeElements_2(ListNode head, int val) {
        ListNode dummy =  new ListNode(-1 , head); // 添加一个虚拟头结点，删除头结点就不用另做考虑
        ListNode cur = dummy;

        while(cur.next != null){ // 只用一个指针
            if(cur.next.val == val){
                cur.next = cur.next.next; // 删除下一个节点
            } else{
                cur = cur.next; // 继续向后遍历链表
            }
        }
        return dummy.next;
    }
    /**
     * 不用哨兵节点
     */
    public ListNode removeElements6(ListNode head, int val) {
        while(head != null && head.val == val){
            head = head.next;
        }
        if(head == null)  {
            return head;
        }
        // 这里需要加入一个吻判断，head.next == null
        // 便于下面ListNode cur = pre.next;这一步的定义
        if(head.next == null){
            return head;
        }
        ListNode pre = head;
        ListNode cur = pre.next;
        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
            }else{
                pre = pre.next;
            }
            cur = cur.next;
        }
        return head;

    }
    /**
     * Recursive - from tail to head 递归
     */
    public static ListNode removeElements_recursive(ListNode head, int val) {
        if(head == null){
            return null; // 如果链表为空，直接返回null
        }
        head.next = removeElements_recursive(head.next, val);
        if(head.val == val){
            return head.next;   // 如果等于val，就跳过当前节点，返回下一个节点. 这样，当前节点就会被移除
        }else{
            return head; // 如果不等于val，就返回当前节点
        }
    }
    /**
     * Recursive - from head to tail
     */
    public static ListNode removeElements_recursive_1(ListNode head, int val) {
        if(head == null){
            return null;
        }
        if(head.val == val){
            return removeElements_recursive_1(head.next, val); // the current node doesn't need to be kept.
        }
        head.next = removeElements_recursive_1(head.next, val);
        return head;
    }
    /**
     * 同上 Recursive - from head to tail
     */
    public ListNode removeElements_recursive_2(ListNode head, int val) {
        if(head == null){
            return null;
        }
        if(head.val == val){
            head = removeElements_recursive_2(head.next, val); // the current node doesn't need to be kept.
            //  return removeElements(head.next, val); // works too
        } else {
            head.next = removeElements_recursive_2(head.next, val);
        }
        return head;
    }
    /**
     * Recursive - from head to tail
     */
    public ListNode removeElements_recursive_3(ListNode head, int val) {
        ListNode dummy = new ListNode (-1, head); // use dummy for input like head = [7,7,7,7], val = 7, Output = [7], Expected = []
        removeElements_rec(dummy,   val);
        return dummy.next;
    }
    // refer to Q83_Remove_Duplicates_from_Sorted_List
    public ListNode removeElements_rec(ListNode head, int val) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode cur = head.next;
        while(cur != null && cur.val == val){ // This part is different from Q83_Remove_Duplicates_from_Sorted_List
            cur = cur.next;
        }

        head.next = removeElements_rec(cur, val);
        return head;
    }
    /**
     * Use stack
     */
    public static ListNode removeElements_3(ListNode head, int val) {
        Deque<ListNode> stack = new ArrayDeque<>();
        while(head != null){
            if(head.val != val){
                stack.push(head);
            }
            head = head.next;
        }

        while(!stack.isEmpty()){
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }
    /**
     * user ArrayList
     */
    public static ListNode removeElements_4(ListNode head, int val) {
        List<ListNode> list = new ArrayList<>();
        while(head != null){
            if(head.val != val){
                list.add(head);
            }
            head = head.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for(int i = 0; i < list.size(); i++){
            cur.next = list.get(i);
            cur = cur.next;
        }
        cur.next = null;

        return dummy.next;
    }
}
