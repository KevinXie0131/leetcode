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
    /**
     * 从前往后找插入点
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1, head); // 虚拟头结点
        ListNode cur = head.next; // 维护 curr 为待插入的元素，初始时 curr = head.next
        ListNode lastSort = head; // 维护 lastSorted 为链表的已排序部分的最后一个节点，初始时 lastSorted = head

        while(cur != null){
            if (lastSort.val <= cur.val) { //无需排序, 因为待排序结点比前面所有的都大
                lastSort = lastSort.next; // 说明 curr 应该位于 lastSorted 之后，将 lastSorted 后移一位，curr 变成新的 lastSorted。
            } else {
                ListNode pre = dummy; // 从链表头开始遍历 prev是插入节点curr位置的前一个节点
                while(pre.next.val <= cur.val){  // 循环退出的条件是找到curr应该插入的位置
                    pre = pre.next;
                }
                // 否则，从链表的头节点开始往后遍历链表中的节点，寻找插入 curr 的位置。令 prev 为插入 curr 的位置的前一个节点，进行如下操作，完成对 curr 的插入
                lastSort.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;

            }
            cur = lastSort.next; // 此时 curr 为下一个待插入的元素
        }
        return dummy.next;
    }
    /**
     * 先找个排头dummy,然后依次从head节点放入dummy,只需要依次dummy现有节点比较,插入其中!
     * 在模拟插入排序的过程中，一共要有三次改变节点指针的操作
     */
    public static ListNode insertionSortList8(ListNode head) {
        if (head == null) return head;

        ListNode dummyHead = new ListNode(0); // 定一个虚拟头结点
        ListNode cur = head;
        ListNode pre = dummyHead;

        while (cur != null) {
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            // 在pre和prenext之间插入数据
            ListNode next = cur.next; // 步骤一：保存cur.next
            cur.next = pre.next;      // 步骤二
            pre.next = cur;            // 步骤三
            pre = dummyHead;            // 步骤四：pre重新指向虚拟头结点来找下一个插入位置
            cur = next;                 // 步骤五：cur的前一个节点的下一个节点指向保存的next
        }
        return dummyHead.next;
    }
    /**
     * 因为每次都要从头比较, 但是测试集很多都是顺序排列的, 没必要从头开始, 我们直接比较最后一个tail, 放在后面
     */
    public ListNode insertionSortList4(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode pre = dummy;
        ListNode tail = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (tail.val < cur.val) {
                tail.next = cur;
                tail = cur;
                cur = cur.next;
            } else {
                ListNode nxt = cur.next;
                tail.next = nxt;
                while (pre.next != null && pre.next.val < cur.val) {
                    pre = pre.next;
                }
                cur.next = pre.next;
                pre.next = cur;
                pre = dummy;
                cur = nxt;
            }
        }
        return dummy.next;
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
            ListNode prev = dummy; // At each iteration, we insert an element into the resulting list.
            while (prev.next != null && prev.next.val <= curr.val) {    // find the position to insert the current node
                prev = prev.next;
            }

            ListNode next = curr.next;
            curr.next = prev.next; // insert the current node to the new list
            prev.next = curr;
            curr = next;  // moving on to the next iteration
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
