package com.answer.linkedlist;

public class Q147_Insertion_Sort_List {
    public static void main(String[] args) {
        // [4,2,1,3]
        ListNode node4 = new ListNode(3, null);
        ListNode node3 = new ListNode(1, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(4,node2);
        ListNode result = insertionSortList_2(node1);
        result.print();
    }

    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1, head); // 虚拟头结点
        ListNode cur = head.next;
        ListNode lastSort = head;

        while(cur != null){
            if (lastSort.val <= cur.val) {
                lastSort = lastSort.next;
            } else {
                ListNode pre = dummy;
                while(pre.next.val <= cur.val){
                    pre = pre.next;
                }
                lastSort.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;

            }
            cur = lastSort.next;
        }

        return dummy.next;

    }
    /**
     *
     */
    public static ListNode insertionSortList_1(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newHead = new ListNode(0);
        ListNode node = head;
        while(node != null){
            ListNode next = node.next;
            //在排好序的链表中找到合适的位置, 插入
            ListNode prevNode = newHead;
            ListNode sortNode = newHead.next;
            while(sortNode != null){
                if(node.val > sortNode.val){
                    prevNode = sortNode;
                    sortNode = sortNode.next;
                }else{
                    break;
                }
            }
            prevNode.next = node;
            node.next = sortNode;

            node = next;
        }
        return newHead.next;

    }
    /**
     * Official answer
     */
    public static ListNode insertionSortList_2(ListNode head) {
        head.print();
        int step = 1;

        ListNode dummy = new ListNode();
        ListNode curr = head;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            ListNode prev = dummy;
            // find the position to insert the current node
            while (prev.next != null && prev.next.val <= curr.val) {
                prev = prev.next;
            }

            ListNode next = curr.next;
            // insert the current node to the new list
            curr.next = prev.next;
            prev.next = curr;
            // moving on to the next iteration
            curr = next;
            // log
            System.out.println("#" + step++);
            if(curr != null) curr.print();
            else System.out.println("null");
            if(curr != null) prev.print();
            else System.out.println("null");
            System.out.println("----------");
        }

        return dummy.next;
    }
}
