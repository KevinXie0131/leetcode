package com.answer.linkedlist;

import java.util.*;

public class Q83_Remove_Duplicates_from_Sorted_List {
    /**
     * 删除排序链表中的重复元素
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
     * Return the linked list sorted as well.
     * 示例 1：
     *  输入：head = [1,1,2]
     *  输出：[1,2]
     * 示例 2：
     *  输入：head = [1,1,2,3,3]
     *  输出：[1,2,3]
     */
    /**
     * 双指针法
     */
    public ListNode deleteDuplicates_6(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;  //pre指向cur的前一个节点
        ListNode cur = head.next;  //cur指向pre的下一个节点
        while(cur != null){
            if(pre.val == cur.val){ //相邻节点值相等，则修改pre和cur的指向
                cur = cur.next;
                pre.next = cur;
                continue;
            }
            //不相等 pre和cur分别向后移动
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
    /**
     * 虚拟头结点
     */
    public ListNode deleteDuplicates8(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MAX_VALUE); // 考虑写一个不在数据范围中的数
        dummy.next = head;
        ListNode cur = dummy;
        while(cur.next != null){
            if(cur.next.val == cur.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /**
     * 虚拟头结点
     */
    public ListNode deleteDuplicates_0(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        while(p.next != null){
            ListNode q = p.next;
            while(q.next != null && q.next.val == q.val){
                q = q.next;
            }
            if(p.next != q){
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
     * Approach 1: Straight-Forward Approach 一次遍历
     * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        // 当 cur 和 cur.next 的存在为循环结束条件，当二者有一个不存在时说明链表没有去重复的必要了
        while(cur != null && cur.next != null){ // 当我们遍历到链表的最后一个节点时，cur.next 为空节点，如果不加以判断，访问 cur.next 对应的元素会产生运行错误。
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }
    /**
     * Recursive - from head to tail 递归
     */
    public ListNode deleteDuplicates_Recursive(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        if(head.val == head.next.val){
            return deleteDuplicates(head.next);  // the current node doesn't need to be kept.
        }else{
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }
    /**
     * Recursive - from tail to head 递归
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
    /**
     * 递归 - from tail to head 递归
     */
    public static ListNode deleteDuplicates_Recursive_2(ListNode head) {
        if(head == null || head.next == null) return head;

        head.next = deleteDuplicates_Recursive_2(head.next);

        if(head.val == head.next.val){
            return head.next;
        }
        return head;
    }
    /**
     * 递归: 当相邻节点元素值相同时进行重连接操作
     */
    public static ListNode deleteDuplicates_Recursive_3(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode cur = head.next;
        while(cur != null && cur.val == head.val){
            cur = cur.next;
        }

        head.next = deleteDuplicates_Recursive_3(cur);  // 跳过所有重复节点
        return head;
    }
    /**
     * 根据HashSet判断即可
     */
    public ListNode deleteDuplicates7(ListNode head) {
        if(head == null){
            return head;
        }
        Set<Integer> set = new HashSet<Integer>();
        set.add(head.val);
        ListNode cur = head;
        while(cur.next != null){
            if(set.add(cur.next.val)){
                cur = cur.next;
            }else{
                cur.next = cur.next.next;
            }
        }
      //  cur.next = null; // can be commented
        return head;
    }
}
