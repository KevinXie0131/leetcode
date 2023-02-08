package com.answer.linkedlist;

public class Q92_Reverse_Linked_List_II {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        //[1,2,3,4,5]
        ListNode node = reverseBetween_1(node1, 2, 4);
        node.print();
    }

    /**
     * Approach 2: Iterative Link Reversal.
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode post = dummy.next;

        for(int i = 0; i< m - 1; i++){
            pre = pre.next;
            post = post.next;
        }
        for(int i = 0; i< n - m; i++){
            ListNode temp = post.next;
            post.next = post.next.next;
            temp.next = pre.next;
            pre.next = temp;

        }
        return dummy.next;
    }
    /**
     * Approach 1: Recursion
     */
    public static ListNode reverseBetween_1(ListNode head, int m, int n) {
        return recursion(head, m, n ,1);
    }

    public static ListNode recursion(ListNode node, int i, int j, int cur){
        if(cur == j){
            return node;
        }
        if(cur < i){
            node.next = recursion(node.next, i, j,cur + 1);
            return node;
        }else{
            ListNode temp = recursion(node.next, i, j,cur + 1);
            ListNode next = node.next.next;
            node.next.next = node;
            node.next = next;
            return temp;
        }
    }
}
