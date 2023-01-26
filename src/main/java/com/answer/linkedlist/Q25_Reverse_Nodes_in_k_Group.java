package com.answer.linkedlist;

public class Q25_Reverse_Nodes_in_k_Group {

    public static void main(String[] args) {
        ListNode node7 = new ListNode(7, null);
        ListNode node6 = new ListNode(6, node7);
        ListNode node5 = new ListNode(5, node6);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        //[1,2,3,4,5]
        ListNode node = reverseKGroup(node1, 3);
        node.print();
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre =dummy;
        ListNode cur = dummy;
        while(cur.next != null){
            for(int i = 0; i < k && cur != null; i++){
                cur = cur.next;
            }
            if(cur == null) break;

            ListNode start = pre.next;
            ListNode end = cur.next;
            cur.next = null;
            pre.next = reverseList(start);
            start.next = end;

            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
