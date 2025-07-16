package com.answer.linkedlist;

import java.util.*;

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
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode res = removeNthFromEnd10(node1 , 2);
        System.out.println(res);
    }
    /**
     * 计算链表长度: 首先从头节点开始对链表进行一次遍历，得到链表的长度 L
     */
    public ListNode removeNthFromEnd0(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) { // 为了与题目中的 n 保持一致，节点的编号从 1 开始，头节点为编号 1 的节点。
            cur = cur.next; // 从哑节点开始遍历 L−n+1 个节点。当遍历到第 L−n+1 个节点时，它的下一个节点就是我们需要删除的节点
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }
    /**
     * 栈
     */
    public ListNode removeNthFromEnd_0a(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        return dummy.next;
    }
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
     * 双指针 + 虚拟头节点
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
     * another form
     */
    public ListNode removeNthFromEnd_0b(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        while(n-- > 0){  // 右指针先向右走 n 步
            fast = fast.next;
        }

        while(fast.next != null){  // 左右指针一起走
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next; // 左指针的下一个节点就是倒数第 n 个节点
        return dummy.next;
    }
    /**
     * 虚拟头节点
     */
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0); // 新建一个虚拟头节点指向head
        dummyNode.next = head;
        ListNode fastIndex = dummyNode;   // 快慢指针指向虚拟头节点
        ListNode slowIndex = dummyNode;

        for (int i = 0; i <= n; i++) { // 只要快慢指针相差 n 个结点即可
            fastIndex = fastIndex.next;
        }
        while (fastIndex != null) {
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }
        // 此时 slowIndex 的位置就是待删除元素的前一个位置。具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
        if (slowIndex.next != null) { // 检查 slowIndex.next 是否为 null，以避免空指针异常
            slowIndex.next = slowIndex.next.next;
        }
        return dummyNode.next;
    }
    /**
     * 递归
     */
    static public ListNode removeNthFromEnd_3(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0); // dummy node不需要对头节点进行特殊的判断
        dummyNode.next = head;

        deleteNode(dummyNode, n); // for [1] , 1

        return dummyNode.next;
    }

    static int size;

    static private void deleteNode(ListNode head, int n){
        if (head == null) {//边界条件判断
            return;
        }
        deleteNode(head.next, n);
        size++;
        if(size == n + 1){
            if (head.next != null) head.next = head.next.next; // head.next = head.next.next; // works too
            return;
        }
    }
    /**
     * another form
     */
    static int deleteNode_1(ListNode head, int n){
        if (head == null) {//边界条件判断
            return 0;
        }
        int count = deleteNode_1(head.next, n);

        if(count == n){
            head.next = head.next.next;
        }
        return count + 1;
    }
    /**
     * 递归
     */
    static int pos = 0;

    static public ListNode removeNthFromEnd10(ListNode head, int n) {
        if (head == null){
            return null;
        }
        head.next = removeNthFromEnd10(head.next, n);
        return ++pos == n ? head.next : head;
    }
}
