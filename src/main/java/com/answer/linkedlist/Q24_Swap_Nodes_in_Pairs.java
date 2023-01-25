package com.answer.linkedlist;

public class Q24_Swap_Nodes_in_Pairs {

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);

        ListNode node = swapPairs_1(node1);
        node.print();
    }

    /**
     * dummy node
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            ListNode start = cur.next;
            ListNode end = cur.next.next;

            start.next = end.next;
            end.next = start;

            cur.next = end;
            cur = start;
        }

        return dummy.next;
    }
    /**
     * dummy node + pre node + extra node
     */
    public static ListNode swapPairs_1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null && cur.next != null){
            ListNode start = cur;
            ListNode end = cur.next;

            start.next = end.next;
            end.next = start;

            pre.next = end;
            pre = start;
            cur = start.next;
        }

        return dummy.next;
    }
    /**
     * dummy node + pre node
     */
    public static ListNode swapPairs_2(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null && cur.next != null){
            ListNode temp = cur.next;

            cur.next = cur.next.next;
            temp.next = cur;

            pre.next = temp;
            pre = cur;
            cur = cur.next;
        }

        return dummy.next;
    }
}
