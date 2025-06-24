package com.answer.linkedlist;

public class Q876_Middle_of_the_Linked_List {
    /**
     * 链表的中间结点
     * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     * Given the head of a singly linked list, return the middle node of the linked list.
     * If there are two middle nodes, return the second middle node.
     *
     * 示例 1：
     *  输入：head = [1,2,3,4,5]
     *  输出：[3,4,5]
     *  解释：链表只有一个中间结点，值为 3 。
     */
    /**
     * fast-slow pointers
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
