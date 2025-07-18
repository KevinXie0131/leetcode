package com.answer.linkedlist;

import java.util.*;

public class Offer_22_Get_Kth_From_End {
    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第 k 个节点。为了符合大多数人的习惯，本题从 1 开始计数，即链表的尾节点是倒数第 1 个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6 。
     * 这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     * 示例：
     *  给定一个链表: 1->2->3->4->5, 和 k = 2.
     *  返回链表 4->5.
     * 限制：1 ≤ k ≤ 链表的长度
     */
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode res = getKthFromEnd_8(node1 , 2);
        System.out.println(res);
    }
    /**
     * Two pointers 双指针
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode cur = head;
        ListNode end = head;
        while(k > 0){ // end指针先走k步
            end = end.next;
            k--;
        }
        while(end != null){  // 然后两个指针在同时前进
            end = end.next;
            cur = cur.next;
        }
        return cur;
    }
    /**
     * Two pointers, but one loop
     */
    public ListNode getKthFromEnd_1(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        int count = 0;
        while(fast != null){
            if(count >= k){
                slow = slow.next;
            }
            fast = fast.next;
            count++;
        }
        return slow;
    }
    /**
     * 使用栈解决
     */
   static public ListNode getKthFromEnd_2(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        //链表节点压栈
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        //在出栈串成新的链表
        ListNode firstNode = stack.pop();
        while (--k > 0) {
            ListNode temp = stack.pop();
            temp.next = firstNode;
            firstNode = temp;
        }
        return firstNode;
    }
    /**
     * 用一个集合记录所有的链表元素，返回集合中第 集合长度 - K 个元素
     */
    static public ListNode getKthFromEnd_6(ListNode head, int k) {
        List<ListNode> list = new ArrayList<>();  // 用一个集合记录所有的链表元素
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        return list.get(list.size() - k);
    }
    /**
     * 递归
     */
    static int size;

    static public ListNode getKthFromEnd_3(ListNode head, int k) {
        if (head == null) //边界条件判断
            return null;
        ListNode val = getKthFromEnd_3(head.next, k);
        ++size;

        if (size < k) {
            return null;  //从后面数结点数小于k，返回空
        } else if (size == k) {
            return head; //从后面数访问结点等于k，直接返回传递的结点k即可
        } else {
            return val;  //从后面数访问的结点大于k，说明我们已经找到了，直接返回node即可
        }
    }
    /**
     * another form
     */
    static public ListNode getKthFromEnd_3a(ListNode head, int k) {
        if (head == null) //边界条件判断
            return null;
        ListNode val = getKthFromEnd_3a(head.next, k);
        ++size;

        if  (size == k) {
            return head; //从后面数访问结点等于k，直接返回传递的结点k即可
        }
        return val;
    }
    /**
     * 递归
     * 利用了递归的特性，先进后出，将链表元素都压栈，每次返回都 K -1，这样K= 0 时，就是倒数第 K 个元素
     */
    static int num = 0;
    static ListNode res = null;

    static public ListNode getKthFromEnd_7(ListNode head, int k) {
        num = k;
        helper(head);
        return res;
    }

    // 递归，利用了递归的先进出的特点，K-- ，直到等于 0，就是第 K 个元素
    static public void helper(ListNode node) {
        if (node == null) {
            return;
        }
        helper(node.next);
        num--;

        if (num == 0) {
            res = node;
        }
    }
    /**
     * another form / from head to tail 链表中第k个节点
     */
    static public ListNode getKthFromEnd_8(ListNode head, int k) {
        if (head == null) //边界条件判断
            return null;
        size++;
        if  (size == k) {
            return head;
        }
        ListNode val = getKthFromEnd_8(head.next, k);

        return val;
    }
}
