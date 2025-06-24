package com.answer.linkedlist;

public class Q708_Insert_into_a_Sorted_Circular_Linked_List {
    /**
     * Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.
     * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.
     * If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.
     * 循环有序列表的插入
     * 给定循环升序（非严格）链表中的一个节点，写一个函数向这个链表中插入一个新节点，使得链表依然保持有序。链表中的节点具有唯一的值，但可能有多个节点有相同的值。输入的节点可以是链表中的任意一个节点，不一定是最小值节点。
     * 如果链表为空（即给定的节点为 null），则创建一个新的循环链表并返回新节点。
     * 否则，返回插入后的任意一个节点（保持循环特性即可）。
     *
     * 示例 1
     *   输入：head = [3,4,1], insertVal = 2
     *  输出：[3,4,1,2]
     *  解释：在该示例中，有一个包含 3 个节点的循环有序列表，其中各个节点的值分别是 3、4 和 1，插入值 2 后，链表变为 3->4->1->2，保持循环有序。
     * 示例 2
     *  输入：head = [], insertVal = 1
     *  输出：[1]
     *  解释：链表为空，创建一个新节点 1，构成循环链表。
     * 示例 3
     *  输入：head = [1], insertVal = 0
     *  输出：[1,0]
     */
}
