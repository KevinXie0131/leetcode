package com.answer.linkedlist;

import javax.imageio.stream.ImageInputStream;
import java.util.ArrayDeque;
import java.util.Deque;

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
     * Iterative / fast-slow pointers
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
     * Iterative / dummy header
     */
    public static ListNode removeElements_2(ListNode head, int val) {
        ListNode dummy =  new ListNode(-1 , head);
        ListNode cur = dummy;

        while(cur.next != null){ // 只用一个指针
            if(cur.next.val == val){
                cur.next = cur.next.next;
            } else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /**
     * Recursive - from tail to head
     */
    public static ListNode removeElements_recursive(ListNode head, int val) {
        if(head == null){
            return null;
        }
        head.next = removeElements_recursive(head.next, val);
        if(head.val == val){
            return head.next;
        }else{
            return head;
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
}
