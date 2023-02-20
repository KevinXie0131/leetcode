package com.learn;

import com.leetcode.ListNode;

public class TesLlinkedList {
    SinglyListNode head;

    /** Helper function to return the index-th node in the linked list. */
    private SinglyListNode getNode(int index) {
        SinglyListNode cur = head;
        for (int i = 0; i < index && cur != null; ++i) {
            cur = cur.next;
        }
        return cur;
    }
    /** Helper function to return the last node in the linked list. */
    private SinglyListNode getTail() {
        SinglyListNode cur = head;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        SinglyListNode cur = getNode(index);
        return cur == null ? -1 : cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        SinglyListNode cur = new SinglyListNode(val);
        cur.next = head;
        head = cur;
        return;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        if (head == null) {
            addAtHead(val);
            return;
        }
        SinglyListNode prev = getTail();
        SinglyListNode cur = new SinglyListNode(val);
        prev.next = cur;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list,
     *  the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        SinglyListNode prev = getNode(index - 1);
        if (prev == null) {
            return;
        }
        SinglyListNode cur = new SinglyListNode(val);
        SinglyListNode next = prev.next;
        cur.next = next;
        prev.next = cur;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        SinglyListNode cur = getNode(index);
        if (cur == null) {
            return;
        }
        SinglyListNode next = cur.next;
        if (index == 0) {
            // modify head when deleting the first node.
            head = next;
        } else {
            SinglyListNode prev = getNode(index - 1);
            prev.next = next;
        }
    }
    /**
     * Two-Pointer in Linked List
     * 1. If there is no cycle, the fast pointer will stop at the end of the linked list.
     * 2. If there is a cycle, the fast pointer will eventually meet with the slow pointer.
     */
    public boolean twoPointers(ListNode head){
        // Initialize slow & fast pointers
        ListNode slow = head;
        ListNode fast = head;
        /**
         * Change this condition to fit specific problem.
         * Attention: remember to avoid null-pointer error
         **/
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;           // move slow pointer one step each time
            fast = fast.next.next;      // move fast pointer two steps each time
            if (slow == fast) {         // change this condition to fit specific problem
                return true;
            }
        }
        return false;   // change return value to fit specific problem
    }
}

// Definition for singly-linked list.
class SinglyListNode {
    int val;
    SinglyListNode next;
    SinglyListNode(int x) { val = x; }
}