package com.answer.linkedlist;

public class Q92_Reverse_Linked_List_II {
    /**
     * 反转链表 II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
     *
     * 示例 1：
     *  输入：head = [1,2,3,4,5], left = 2, right = 4
     *  输出：[1,4,3,2,5]
     */
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        //[1,2,3,4,5]
        ListNode node = reverseBetween_1(node1, 2, 4);
        node.print();
    }
    /**
     * Approach 2: Iterative Link Reversal.
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;

        for(int i = 0; i < m - 1; i++){ //找到第一个节点
            pre = pre.next;
            cur = cur.next;
        }
        for(int i = 0; i < n - m; i++){// 反转
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;

        }
        return dummy.next;
    }
    /**
     * Approach 1: Recursion
     */
    public static ListNode reverseBetween_1(ListNode head, int m, int n) {
        return recursion(head, m, n ,1);
    }

    public static ListNode recursion(ListNode node, int i, int j, int cur){
        if(cur == j){
            return node;
        }
        if(cur < i){
            node.next = recursion(node.next, i, j,cur + 1);
            return node;
        }else{
            ListNode temp = recursion(node.next, i, j,cur + 1);
            ListNode next = node.next.next;
            node.next.next = node;
            node.next = next;
            return temp;
        }
    }
}
