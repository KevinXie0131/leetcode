package com.answer.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class Q143_Reorder_List {

    public static void main(String[] args) {
        //head = [1,2,3,4,5]
        //  ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1,node2);

        reorderList(node1);
        node1.print();
    }

    public static void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur);
            cur = cur.next;
        }
        int i = 0, j = list.size() - 1;
        while(i < j){
            list.get(i).next = list.get(j);
            i++;
            if(i == j){ //偶数个节点的情况，会提前相遇
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }
    /**
     * Approach 1: Reverse the Second Part of the List and Merge Two Sorted Lists
     * This problem is a combination of these three easy problems:
     *
     * Middle of the Linked List.
     * Reverse the Second Part of the List.
     * Merge Two Sorted Lists.
     */
    public void reorderList_1(ListNode head) {
        ListNode fast = head, slow = head;
        //求出中点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //right就是右半部分 12345 就是45  1234 就是34
        ListNode right = slow.next;
        //断开左部分和右部分
        slow.next = null;
        //反转右部分 right就是反转后右部分的起点
        right = reverseList(right);
        //左部分的起点
        ListNode left = head;
        //进行左右部分来回连接
        //这里左部分的节点个数一定大于等于右部分的节点个数 因此只判断right即可
        while (right != null) {
            ListNode curLeft = left.next;
            left.next = right;
            left = curLeft;

            ListNode curRight = right.next;
            right.next = left;
            right = curRight;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode headNode = new ListNode(0);
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = headNode.next;
            headNode.next = cur;
            cur = next;
        }
        return headNode.next;
    }
}
