package com.answer.linkedlist;

public class Q237_Delete_Node_in_a_Linked_List {
    /**
     * 删除链表中的节点
     * 有一个单链表(singly-linked list)的 head，我们想删除它其中的一个节点 node。
     * 给你一个需要删除的节点 node 。你将 无法访问 第一个节点  head(You will not be given access to the first node of head.)。
     * 链表的所有值都是 唯一的(unique)，并且保证给定的节点 node 不是链表中的最后一个节点。
     * 删除给定的节点。注意，删除节点并不是指从内存中删除它。
     * 这里的意思是：
     *  给定节点的值不应该存在于链表中。
     *  链表中的节点数应该减少 1。
     *  node 前面的所有值顺序相同。
     *  node 后面的所有值顺序相同。
     *  The value of the given node should not exist in the linked list.
     *  The number of nodes in the linked list should decrease by one.
     *  All the values before node should be in the same order.
     *  All the values after node should be in the same order.
     *
     * 自定义测试：
     *  对于输入，你应该提供整个链表 head 和要给出的节点 node。node 不应该是链表的最后一个节点，而应该是链表中的一个实际节点。
     *  我们将构建链表，并将节点传递给你的函数。
     *  输出将是调用你函数后的整个链表。
     *
     * 示例 1：
     *  输入：head = [4,5,1,9], node = 5
     *  输出：[4,1,9]
     *  解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
     */
    /**
     * Approach 1: Delete next node instead of current one
     * It is guaranteed that the node to be deleted is not a tail node in the list.
     *
     * 1. Update current node with next node details
     * 2. Update current node next pointer with next node next pointer
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
