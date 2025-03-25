package com.answer.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Q160_Intersection_of_Two_Linked_Lists {
    /**
     * two pointers 双指针
     * 相交节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // curA 指向 A 链表头结点，curB 指向 B 链表头结点
        ListNode curA = headA;
        ListNode curB = headB;
        while(curA != curB){
            // curA 走一步，如果走到 A 链表末尾，转到 B 链表
            if(curA != null){
                curA = curA.next;
            } else {
                curA = headB;
            }
            // curB 走一步，如果走到 B 链表末尾，转到 A 链表
            if(curB != null){
                curB = curB.next;
            } else {
                curB = headA;
            }
        }
        return curA; // 不相交，返回null
    }
    /**
     * Use set
     */
    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode cur = headA;
        while(cur != null){
            visited.add(cur);
            cur = cur.next;
        }

        cur = headB;
        while(cur != null){
            if(visited.contains(cur)){
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }
    /**
     * 先行移动长链表实现同步移动
     */
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0, lenB = 0;
        while (curA != null) { // 求链表A的长度
            lenA++;
            curA = curA.next;
        }
        while (curB != null) { // 求链表B的长度
            lenB++;
            curB = curB.next;
        }
        curA = headA;
        curB = headB;
        // 让curA为最长链表的头，lenA为其长度
        if (lenB > lenA) {
            //1. swap (lenA, lenB);
            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
            //2. swap (curA, curB);
            ListNode tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }
        // 求长度差
        int gap = lenA - lenB;
        // 让curA和curB在同一起点上（末尾位置对齐）
        while (gap-- > 0) {
            curA = curA.next;
        }
        // 遍历curA 和 curB，遇到相同则直接返回
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
    /**
     * 优化版
     */
    public ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = getLen(headA);
        int lenB = getLen(headB);

        if (lenA > lenB) {
            while (lenA > lenB) {
                headA = headA.next;
                lenA--;
            }
        } else {
            while (lenA < lenB) {
                headB = headB.next;
                lenB--;
            }
        }

        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    public int getLen(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
}
