package com.answer.linkedlist;

public class Q83_Remove_Duplicates_from_Sorted_List {
    /**
     * 删除排序链表中的重复元素
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
     * Return the linked list sorted as well.
     *
     * 示例 1：
     *  输入：head = [1,1,2]
     *  输出：[1,2]
     * 示例 2：
     *  输入：head = [1,1,2,3,3]
     *  输出：[1,2,3]
     */
    public ListNode deleteDuplicates_0(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        while(p.next != null){
            ListNode q = p.next;
            while(q.next != null && q.next.val == q.val){
                q = q.next;
            }
            if(q != p.next){
                p.next = q; // delete elements within two pointers
            }
            p = p.next;
        }
        return dummy.next;
    }
    /**
     * 另一种形式
     * 用一个指针来遍历链表，每遍历到一个节点，就用另一个指针来找到后面第一个和当前节点值不同的节点，
     * 然后把中间的重复节点全部删去，重复这个过程，直至链表结尾。
     */
    public ListNode deleteDuplicates0(ListNode head) {
        ListNode p = head;
        while(p != null && p.next != null) {
            ListNode q = p.next;
            while(q!= null && q.val == p.val){
                q = q.next;
            }
            p.next = q;
            p = p.next;
        }
        return head;
    }
    /**
     * Iterative
     * Approach 1: Straight-Forward Approach
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }

        return head;
    }
    /**
     * Recursive - from head to tail
     */
    public ListNode deleteDuplicates_Recursive(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        if(head.val == head.next.val){
            return deleteDuplicates(head.next);
        }else{
            head.next = deleteDuplicates(head.next);
        }

        return head;
    }
    /**
     * Recursive - from tail to head
     */
    public static ListNode deleteDuplicates_Recursive_1(ListNode head) {
        return deleteDuplicates_Recursive_func(head, Integer.MAX_VALUE);
    }

    public static ListNode deleteDuplicates_Recursive_func(ListNode head, int value) {
        if(head == null){
            return head;
        }

        head.next = deleteDuplicates_Recursive_func(head.next, head.val);

        if(head.val == value){
            return head.next;
        }else{
            return head;
        }
    }

}
