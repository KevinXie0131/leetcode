package com.answer.linkedlist;

public class LinkedListTemplate {
    /**
     * 反转链表 迭代
     * 视频讲解 https://www.bilibili.com/video/BV1sd4y1x7KN/
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
    /**
     * 反转链表 迭代 同上
     */
    public ListNode reverseList2a (ListNode head) {
        if(head == null) return head;
        ListNode pre = null;
        ListNode nxt;
        while(head != null){
            nxt = head.next;
            head.next= pre;
            pre = head;
            head = nxt;
        }
        return pre;
    }
    /**
     * 反转链表 递归 from tail to head
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) { // head == null is for head = [] input
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head; // 把下一个节点指向自己
        head.next = null; // 断开指向下一个节点的连接，保证最终链表的末尾节点的 next 是空节点
        return newHead;
    }
    /**
     * 反转链表 递归 from head to tail
     */
    public ListNode reverseList0 (ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode curr) {
        if(curr == null) {
            return prev;
        }
        ListNode next = curr.next;
        curr.next = prev;
        return reverse(curr, next);
    }
    /**
     * 反转链表 迭代 头插法
     * 用头插法依次把节点 1,2,3 插到这个新链表的头部，就得到了链表 3→2→1，这正是反转后的链表
     */
    public ListNode reverseList1(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode.next;
        ListNode next;
        while (cur != null && cur.next != null) {
            next = cur.next;
            cur.next = next.next;
            next.next = dummyNode.next;
            dummyNode.next = next;
        }
        return dummyNode.next;
    }
    /**
     * 两两交换链表中的节点
     * dummy node
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            first.next = second.next;
            second.next = first;
            cur.next = second;

            cur = first; // cur = cur.next.next; // works too
        }
        return dummy.next;
    }
    /**
     * 两两交换链表中的节点 -
     * 递归 - from head to tail
     */
    public static ListNode swapPairs_Recursive(ListNode first) {
        if(first == null || first.next == null){
            return first;
        }
        ListNode third = first.next.next;

        ListNode second = first.next;
        second.next = first;

        first.next = swapPairs_Recursive(third); // head.next = swapPairs_Recursive(head.next.next); // not work
        return second;
    }
    /**
     * 两两交换链表中的节点
     * 递归 - from tail to head
     */
    public static ListNode swapPairs_Recursive_1(ListNode first) {
        if(first == null || first.next == null){
            return first;
        }

        ListNode third = swapPairs_Recursive_1(first.next.next);

        ListNode second = first.next;
        second.next = first;
        first.next = third;

        return second;
    }
}
