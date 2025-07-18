package com.answer.linkedlist;

public class Q147_Insertion_Sort_List {
    /**
     * 对链表进行插入排序
     * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
     *
     * 插入排序 算法的步骤:
     *  插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     *  每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     *  重复直到所有输入数据插入完为止。
     * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
     *
     * Given the head of a singly linked list, sort the list using insertion sort, and return the sorted list's head.
     *
     * The steps of the insertion sort algorithm:
     *  Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
     *  At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list and inserts it there.
     *  It repeats until no input elements remain.
     * The following is a graphical example of the insertion sort algorithm. The partially sorted list (black) initially contains only the first element in the list. One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
     *
     * 示例 1：
     *  输入: head = [4,2,1,3]
     *  输出: [1,2,3,4]
     */
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
     * 定义一个虚拟头结点，用于存储已排序列表，遍历原始链表，每遇到一个结点，就将其插入到已排序链表中，最后返回虚拟头结点的下一个结点即可。
     * 4 -> 2 -> 1 -> 3 ->
     * --------------------
     * #1
     * 2 -> 1 -> 3 ->
     * 0 -> 4 ->
     * --------------------
     * #2
     * 1 -> 3 ->
     * 0 -> 2 -> 4 ->
     * --------------------
     * #3
     * 3 ->
     * 0 -> 1 -> 2 -> 4 ->
     * --------------------
     * #4
     * null
     * null
     * --------------------
     * 1 -> 2 -> 3 -> 4 ->
     */
    public static ListNode insertionSortList_2(ListNode head) {
        head.print();
        int step = 1;

        ListNode dummy = new ListNode(); // 虚拟头结点
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
