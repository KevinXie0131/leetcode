package com.answer.linkedlist;

public class Q237_Delete_Node_in_a_Linked_List {

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
