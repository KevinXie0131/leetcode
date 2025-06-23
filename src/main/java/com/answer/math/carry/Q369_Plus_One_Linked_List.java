package com.answer.math.carry;

import  com.answer.linkedlist.ListNode;

public class Q369_Plus_One_Linked_List {
    /**
     * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
     * You may assume the integer do not contain any leading zero, except the number 0 itself.
     * The digits are stored such that the most significant digit is at the head of the list.
     * Example :
     *  Input: [1,2,3]
     *  Output: [1,2,4]
     * 给单链表加一
     * 给定一个非空的单链表，表示一个非负整数，链表的每个节点代表该整数的一位数字（高位在链表头部）。在该整数的基础上加一，并返回表示加一后结果的链表。
     * 例如：
     *  输入: 1->2->3
     *  输出: 1->2->4
     * 注意：
     *  链表中的数字不包含前导零，除了数字0本身。
     */
    public static void main(String[] args) {
        //head = [1,2,3]
/*        ListNode node3 = new ListNode(9, null);
        ListNode node2 = new ListNode(9, node3);
        ListNode node1= new ListNode(1,node2);*/

     /*   ListNode node1= new ListNode(9,null);*/

        ListNode node4 = new ListNode(9, null);
        ListNode node3 = new ListNode(9, node4);
        ListNode node2 = new ListNode(9, node3);
        ListNode node1= new ListNode(8,node2);

        ListNode res = plusOne_1(node1);
        res.print();
    }
    /**
     * it doesn't work for [9]
     */
    public static ListNode plusOne(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode newNode = plusOne(head.next);

        if(newNode == null || newNode.val == 10){
            head.val +=  1;
            head.val %= 10;
            if(head.val == 0){
                return new ListNode(10, head.next);
            } else {
                return head;
            }
        }
        return head;
    }
    /**
     * it works well
     */
    static int carryOver = 0;
    public static ListNode plusOne_1(ListNode head) {
        helper(head);

        if(carryOver > 0){
            ListNode newNode = new ListNode(1, head);
            return newNode;
        }
        return head;
    }

    public static ListNode helper(ListNode head) {
        if(head == null){
            return head;
        }
        ListNode newNode = helper(head.next);

        if(newNode == null){
            carryOver = (head.val + 1) / 10;
            head.val = (head.val + 1) % 10;
        }else{
            int sum = head.val + carryOver;
            head.val = sum % 10;
            carryOver = sum / 10;
        }
        return head;
    }
    /**
     *
     */
    int carry = 0;
    public ListNode plusOne_2(ListNode head) {
        help(head);

        if(carry > 0){
            ListNode newNode = new ListNode(1, head);
            return newNode;
        }
        return head;
    }

    public void help(ListNode head){
        if(head == null){
            carry = 1;
            return;
        }
        help(head.next);

        if(carry == 1){
            carry = (head.val + 1 ) / 10;
            head.val = (head.val + 1 ) % 10;
        }
        return;
    }
    /**
     * The addOne method recursively traverses to the end of the list, adds 1, and handles the carry as the recursion unwinds.
     * If after processing all nodes there is still a carry (the most significant digit had a carryover), a new node is created at the head.
     */
    public ListNode plusOne3(ListNode head) {
        int carry = addOne(head);
        if (carry > 0) {
            ListNode newHead = new ListNode(carry);
            newHead.next = head;
            return newHead;
        }
        return head;
    }
    // Helper function to recursively add one from the tail
    private int addOne(ListNode node) {
        if (node == null) {
            return 1; // Initial carry for +1
        }
        int carry = addOne(node.next);
        int sum = node.val + carry;
        node.val = sum % 10;
        return sum / 10;
    }
    /**
     * Reverse the linked list to make it easy to add one starting from the least significant digit.
     * Traverse the list, add one, and manage carry.
     * If a carry remains, add a new node at the end.
     * Reverse the list back to restore the original order.
     */
    public ListNode plusOne4(ListNode head) {
        // Step 1: Reverse the list
        head = reverse(head);

        // Step 2: Add one to the reversed list
        ListNode curr = head;
        int carry = 1;
        ListNode prev = null;
        while (curr != null) {
            int sum = curr.val + carry;
            curr.val = sum % 10;
            carry = sum / 10;
            prev = curr;
            curr = curr.next;
        }
        // Step 3: If there's still a carry, add a new node
        if (carry > 0) {
            prev.next = new ListNode(carry);
        }

        // Step 4: Reverse the list again to restore original order
        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}
