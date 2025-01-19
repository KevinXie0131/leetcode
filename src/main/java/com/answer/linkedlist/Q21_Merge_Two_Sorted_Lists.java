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

        ListNode result = mergeTwoLists0(node1, node1a);
        result.print();
    }
    /**
     * 递归（另一种形式）
     */
    public static ListNode mergeTwoLists0(ListNode list1, ListNode list2) {
        ListNode result =  resurse(list1, list2);
        return result;
    }

    public static ListNode resurse(ListNode list1, ListNode list2 ){
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }

        ListNode head = new ListNode(); //创建一个新节点
        head.val = list1.val < list2.val ? list1.val: list2.val;
        head.next = list1.val < list2.val ? resurse(list1.next, list2) : resurse(list1, list2.next);
        return head;
    }
    /**
     * Recursive
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
/*        if(list1 == null || list2 == null){ // 同上
            return list1 == null ? list2 : list1;
        }*/

        if(list1.val < list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
    /**
     * Iterative
     */
    public ListNode mergeTwoLists_1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1, null);
        ListNode cur = dummy;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1 == null ? list2 : list1;

        return dummy.next;
    }
}
