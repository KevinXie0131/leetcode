package com.answer.linkedlist;

public class Q82_Remove_Duplicates_from_Sorted_List_II {
    /**
     * 删除排序链表中的重复元素 II
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表
     * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     * Return the linked list sorted as well.
     * 示例 1：
     *  输入：head = [1,2,3,3,4,4,5]
     *  输出：[1,2,5]
     * 示例 2：
     *  输入：head = [1,1,1,2,3]
     *  输出：[2,3]
     */
    public static void main(String[] args) {
/*        ListNode node9 = new ListNode(5, null);
        ListNode node8 = new ListNode(5, node9);
        ListNode node7 = new ListNode(4, node8);
        ListNode node6 = new ListNode(4, node7);
        ListNode node5 = new ListNode(3, node6);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);*/

        // [1,1,1,2,3]
        ListNode node5 = new ListNode(3, null);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(1, node4);
        ListNode node2 = new ListNode(1, node3);
        ListNode node1= new ListNode(1,node2);

        ListNode node = deleteDuplicates_Recursive_1(node1);
        node.print();
    }
    /**
     * dummy node
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            if(cur.next.val == cur.next.next.val){
                int val = cur.next.val;
                cur.next = cur.next.next;

                while(cur.next != null && cur.next.val == val){
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /**
     * Use dummy node and pre node
     */
    public static ListNode deleteDuplicates_1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null && cur.next != null){
            if(cur.val == cur.next.val){
                int val = cur.val;

                while(cur != null && cur.val == val){
                    cur = cur.next;
                }
                pre.next = cur;
            }else{
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    /**
     * 删除原始链表中所有重复数字的节点，只留下不同的数字
     * delete elements within two pointers
     *
     * 由于头结点可能被删除，因此需要一个增加一个虚拟结点。
     * 遍历链表，当遇到重复结点时，循环向后移动，直到下一个不重复结点，将中间结点删除即可。
     */
    public ListNode deleteDuplicates_3(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode p = dummy;
        while(p.next != null){
            ListNode q = p.next;
            while(q.next != null && q.next.val == q.val){
                q = q.next;
            }
            if(q == p.next){
                p = p.next;
            }else{
                p.next = q.next; // delete elements within two pointers
            }
        }
        return dummy.next;
    }
    /**
     * Recursive - from head to tail
     * 1 -> 2 -> 3 -> 3 -> 3 -> 4 -> 4 -> 5
     */
    public static ListNode deleteDuplicates_Recursive(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        if(head.val == head.next.val){
            while(head.next != null && head.val == head.next.val){
                head = head.next;
            }
            return deleteDuplicates_Recursive(head.next); // the current node doesn't need to be kept.
        }else{
            head.next = deleteDuplicates_Recursive(head.next);
        }

        return head;
    }
    /**
     * Recursive - from tail to head
     */
    public static ListNode deleteDuplicates_Recursive_1(ListNode head) {
        return deleteDuplicates_Recursive_func(head, Integer.MAX_VALUE, head == null || head.next == null ? Integer.MAX_VALUE : head.next.val);
    }

    public static ListNode deleteDuplicates_Recursive_func(ListNode head, int preValue, int nextValue) {
        if(head == null){
            return head;
        }

        head.next = deleteDuplicates_Recursive_func(head.next, head.val,
                head.next == null || head.next.next == null? Integer.MAX_VALUE : head.next.next.val);

        if(head.val == preValue || head.val == nextValue){
            return head.next;
        }else{
            return head;
        }

    }
}
