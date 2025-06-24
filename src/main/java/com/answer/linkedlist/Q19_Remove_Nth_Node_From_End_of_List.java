package com.answer.linkedlist;

public class Q19_Remove_Nth_Node_From_End_of_List {
    /**
     * 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * Given the head of a linked list, remove the nth node from the end of the list and return its head.
     *
     * 示例 1：
     *  输入：head = [1,2,3,4,5], n = 2
     *  输出：[1,2,3,5]
     *
     * 进阶：你能尝试使用一趟扫描实现吗？
     * Follow up: Could you do this in one pass?
     */
    /**
     * fast-slow pointers 快慢指针
     * 快慢双指针，令快指针先走 n 步，之后两指针再同步移动，直到快指针移动到链表结尾，此时慢指针指向的下一个位置即为要删除元素。
     * 注意
     *   在头部插入一个虚拟结点，可以避免对头结点的特殊处理。
     *   边界情况可以测试几个 case 确定。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode (-1, head); // 增加虚拟节点
        ListNode slow = dummy;
        ListNode fast = head;
        for(int i = 0; i < n; i++){
            fast = fast.next; // 快指针找到需要删除的节点
        }
        while(fast != null){ // 快慢指针同时移动
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; // slow.next指向要删除的节点

        return dummy.next;
    }
    /**
     * 虚拟头节点
     */
    public ListNode removeNthFromEnd_0(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        while(n > 0){
            fast = fast.next;
            n--;
        }
        fast = fast.next; // fast再提前走一步，因为需要让slow指向删除节点的上一个节点

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;

    }
    /**
     * 虚拟头节点
     */
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        //新建一个虚拟头节点指向head
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        //快慢指针指向虚拟头节点
        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        // 只要快慢指针相差 n 个结点即可
        for (int i = 0; i <= n; i++) {
            fastIndex = fastIndex.next;
        }
        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        // 此时 slowIndex 的位置就是待删除元素的前一个位置。
        // 具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
        // 检查 slowIndex.next 是否为 null，以避免空指针异常
        if (slowIndex.next != null) {
            slowIndex.next = slowIndex.next.next;
        }
        return dummyNode.next;
    }
}
