package com.answer.linkedlist;

public class Q876_Middle_of_the_Linked_List {
    /**
     * 快慢指针
     * https://leetcode.cn/problems/middle-of-the-linked-list/solutions/165152/kuai-man-zhi-zhen-zhu-yao-zai-yu-diao-shi-by-liwei/
     */
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
     * fast-slow pointers 快慢指针法
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){ // 快指针fast向前移动的条件
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    /**
     * 数组
     * 链表的缺点在于不能通过下标访问对应的元素。因此我们可以考虑对链表进行遍历，同时将遍历到的元素依次放入数组 A 中。
     * 如果我们遍历到了 N 个元素，那么链表以及数组的长度也为 N，对应的中间节点即为 A[N/2]。
     */
    public ListNode middleNode1(ListNode head) {
        ListNode[] A = new ListNode[100];
        int t = 0;
        while (head != null) {
            A[t++] = head;
            head = head.next;
        }
        return A[t / 2];
    }
    /**
     * 单指针法
     */
    public ListNode middleNode2(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            ++n;
            cur = cur.next;
        }
        int k = 0;
        cur = head;
        while (k < n / 2) {
            ++k;
            cur = cur.next;
        }
        return cur;
    }
}
