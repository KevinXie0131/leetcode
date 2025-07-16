package com.answer.linkedlist;

public class Q21_Merge_Two_Sorted_Lists {
    /**
     * 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * You are given the heads of two sorted linked lists list1 and list2.
     * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
     * Return the head of the merged linked list.
     *
     * 示例 1：
     *  输入：l1 = [1,2,4], l2 = [1,3,4]
     *  输出：[1,1,2,3,4,4]
     * l1 和 l2 均按 非递减顺序 排列 / Both list1 and list2 are sorted in non-decreasing order.
     */
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
     * Recursive 递归
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
     * 递归
     */
    public ListNode mergeTwoLists_3(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            return new ListNode(l1.val, mergeTwoLists(l1.next, l2));
        } else {
            return new ListNode(l2.val, mergeTwoLists(l1, l2.next));
        }
    }
    /**
     * Iterative
     * 同时遍历两个链表，从中选取较小的元素添加到结果链表，直至两个链表都遍历完成。
     */
    public ListNode mergeTwoLists_1(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1, null); // 增加虚拟节点 简化代码逻辑
        ListNode cur = dummy;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                cur.next = list1; // 把 list1 加到新链表中
                list1 = list1.next;
            } else {  // 注：相等的情况加哪个节点都是可以的
                cur.next = list2; // 把 list2 加到新链表中
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 拼接剩余链表
        cur.next = list1 == null ? list2 : list1; // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        return dummy.next;
    }
}
