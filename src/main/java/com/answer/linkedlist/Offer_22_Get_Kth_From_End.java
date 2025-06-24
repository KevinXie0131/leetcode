package com.answer.linkedlist;

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
    /**
     * Two pointers
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode cur = head;
        ListNode end = head;
        while(k > 0){
            end = end.next;
            k--;
        }
        while(end != null){
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
}
