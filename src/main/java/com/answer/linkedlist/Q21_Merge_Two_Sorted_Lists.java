package com.answer.linkedlist;

public class Q21_Merge_Two_Sorted_Lists {

    public static void main(String[] args) {
        // list1 = [1,2,4], list2 = [1,3,4]
        ListNode node3 = new ListNode(4, null);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);

        ListNode node3a = new ListNode(4, null);
        ListNode node2a = new ListNode(3, node3a);
        ListNode node1a = new ListNode(1,node2a);

        ListNode result = mergeTwoLists(node1, node1a);
        result.print();
    }
    /**
     * Recursive
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        else if(list2 == null){
            return list1;
        }
        else if(list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }
}
