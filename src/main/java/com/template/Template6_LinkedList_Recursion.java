package com.template;

public class Template6_LinkedList_Recursion {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        printValueForward(node1);
        System.out.println();

        printValueBackward(node1);
    }

    public static void printValueForward(ListNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " -> ");
        printValueForward(node.next);
    }

    public static void printValueBackward(ListNode node) {
        if (node == null) {
            return;
        }
        printValueBackward(node.next);
        System.out.print(node.value + " -> ");
    }
}
