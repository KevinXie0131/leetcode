package com.answer.linkedlist;

import java.util.*;

public class Q141_Linked_List_Cycle {
    /**
     * 环形链表
     * 一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
     * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     * 示例 1：
     *  输入：head = [3,2,0,-4], pos = 1
     *  输出：true
     *  解释：链表中有一个环，其尾部连接到第二个节点。
     */
    /**
     * 本题是 Q142. 环形链表 II 的简化版，只需判断快慢指针能否相遇即可。
     * fast-slow pointers
     * 快慢指针法， 分别定义 fast 和 slow指针，从头结点出发，fast指针每次移动两个节点，slow指针每次移动一个节点，如果 fast 和 slow指针在途中相遇 ，说明这个链表有环。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        // 空链表、单节点链表一定不会有环
        while(fast != null && fast.next != null){
            fast = fast.next.next; // 快指针，一次移动两步
            slow = slow.next;      // 慢指针，一次移动一步
            if(fast == slow){       // 快慢指针相遇，表明有环
                return true;
            }
        }
        return false;  // 正常走到链表末尾，表明没有环
    }
    /**
     * another from
     */
    public boolean hasCycle_0(ListNode head) {
        ListNode slow = head, fast = head;
        do{
            if(fast == null || fast.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (slow != fast);
        return true;
    }
    /**
     * another from
     */
    public boolean hasCycle_6(ListNode head) {
        ListNode slow = head, fast = head;
        while (true) {
            if (fast == null || fast.next == null) { // 指向空节点则无环
                return false;
            }
            fast = fast.next.next;    // fast 和 slow 异速前进
            slow = slow.next;

            if (slow == fast) { // 相遇
                return true;
            }
        }
    }
    /**
     * another from
     */
    public boolean hasCycle_2(ListNode head) {
        if (head == null) {
            return false;
        }
        while (head.next != null) {
            if (head.next.val == Integer.MAX_VALUE) {
                return true;
            }
            head.val = Integer.MAX_VALUE;
            head = head.next;
        }
     /*   while (head != null) { // works too
            if (head.val == Integer.MAX_VALUE) {
                return true;
            }
            head.val = Integer.MAX_VALUE;
            head = head.next;
        }*/
        return false;
    }
    /**
     * Use set 哈希表
     */
    public boolean hasCycle_1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null){
/*            if(!set.add(head)){
                return true;
            }*/
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
