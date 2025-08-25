package com.answer.linkedlist;

import java.util.*;

public class Q160_Intersection_of_Two_Linked_Lists {
    /**
     * 相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
     * 图示两个链表在节点 c1 开始相交：
     * 题目数据 保证 整个链式结构中不存在环。
     * 注意，函数返回结果后，链表必须 保持其原始结构 (retain  their original structure ) 。
     *
     * 自定义评测：
     * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
     *  intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
     *  listA - 第一个链表
     *  listB - 第二个链表
     *  skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
     *  skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
     * 评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。
     * 示例 1：
     *  输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
     *  输出：Intersected at '8'
     *  解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
     *      从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
     *      在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *      — 请注意相交节点的值不为 1，因为在链表 A 和链表 B 之中值为 1 的节点 (A 中第二个节点和 B 中第三个节点) 是不同的节点。换句话说，它们在内存中指向两个不同的位置，而链表 A 和链表 B 中值为 8 的节点 (A 中第三个节点，B 中第四个节点) 在内存中指向相同的位置。
     */
    /**
     * two pointers 双指针
     * 相交节点
     * 如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针 pB 为空，则将指针 pB 移到链表 headA 的头节点。
     * 当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null。
     *
     * 假设：链表 A 长度为 m = a + c （前段 a，公共段 c）
     *       链表 B 长度为 n = b + c （前段 b，公共段 c）
     * 指针 pA 先走 A，再走 B；
     * 指针 pB 先走 B，再走 A。
     * 走的路径长度： pA: a + c + b
     *               pB: b + c + a
     * 都走了 a + b + c 的长度后，会在交点处 相遇，或者同时走到 None（如果没有交点）。
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
     * Use set 判断两个链表是否相交，可以使用哈希集合存储链表节点。
     */
    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
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
     * another form
     */
    public ListNode getIntersectionNode6(ListNode headA, ListNode headB) {
        Set<ListNode> aNodes = new HashSet<>();
        for (ListNode node = headA; node != null; node = node.next) {
            aNodes.add(node);
        }
        for (ListNode node = headB; node != null; node = node.next) {
            if (aNodes.contains(node)) {
                return node;
            }
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
