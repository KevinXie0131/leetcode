package com.answer.linkedlist;

public class Q92_Reverse_Linked_List_II {
    /**
     * 反转链表 II
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
     * 示例：
     *  输入：head = [1,2,3,4,5], left = 2, right = 4
     *  输出：[1,4,3,2,5]
     */
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        //[1,2,3,4,5]
        ListNode node = reverseBetween8(node1, 2, 4);
        node.print();
    }
    /**
     * 第 1 步：先将待反转的区域反转；
     * 第 2 步：把 pre 的 next 指针指向反转以后的链表头节点，把反转以后的链表的尾节点的 next 指针指向 succ。
     */
    public ListNode reverseBetween0(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1); // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        dummyNode.next = head;
        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点. 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;
        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;
        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);
        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }
    // 也可以使用递归反转一个链表
    private void reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }
    /**
     * 一次遍历 反转链表（头插法）
     */
    static public ListNode reverseBetween_3(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);   // 设置 dummyNode 是这一类问题的一般做法
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // curr: 指向待反转区域的第一个节点 left
        // next: 永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化
        // pre: 永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变
        // 操作步骤:
        //  先将 curr 的下一个节点记录为 next
        //   执行操作1: 把 curr 的下一个节点指向 next 的下一个节点
        //   执行操作2: 把 next 的下一个节点指向 pre 的下一个节点
        //   执行操作3: 把 pre 的下一个节点指向 next
        //        pre
        //         |
        // step 1: 1 -> 2 -> 3 -> 4 -> 5
        // step 2: 1 -> 3 -> 2 -> 4 -> 5
        // step 2: 1 -> 4 -> 3 -> 2 -> 5
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next; //  next.next = cur; // not work
            pre.next = next;
        }
        return dummyNode.next;
    }
    /**
     * Approach 2: Iterative Link Reversal. 同上
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;

        for(int i = 0; i < m - 1; i++){ //找到第一个节点
            pre = pre.next;
            cur = cur.next;
        }
        for(int i = 0; i < n - m; i++){ // 反转
            ListNode nxt = cur.next;
            cur.next = nxt.next;
            nxt.next = pre.next;
            pre.next = nxt;
        }
        return dummy.next;
    }
    /**
     * 注意：left 和 right 是位置范围，不是元素值的范围。
     */
    public static ListNode reverseBetween8(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0, head);// 1. 初始化一个虚拟头节点，它的 next 是 head。
        ListNode cur = dummy;   // 操作指针

        for (int i = 0; i < left - 1; i++) { // 2. 移动操作指针 left - 1 次，实际上是到了要反转位置的前一个位置
            cur = cur.next;
        }
        // 3. 第三部分：正常的一维反转逻辑，但是 pre 节点不能到达 head 尾部了，而是到达反转区域的下一个节点的位置。
        // 比如要反转 [2,4]，那就移动到 5，方便原链表节点连接反转后的链表。
        ListNode pre = cur.next;
        ListNode tail = null;
        for (int i = 0; i < right - left + 1; i++) {    // 原版的是while(pre != null)
            ListNode nxt = pre.next;
            pre.next = tail;
            tail = pre;
            pre = nxt;
        }
        // 4. 第四部分：拼接上反转后的链表.
        // - cur 此时还在反转区域的前一个节点，用它进行连接即可。
        // - 此时pre 节点在反转区域的后面一个节点。tail 就在反转区域的最后一个节点
        // 拼接：cur.next.next，实际上是操作反转区域的第一个节点的 next 到 pre。然后是cur.next表示换成 tail节点，就拼好了。
        cur.next.next = pre;
        cur.next = tail;
        return dummy.next;
    }
    /**
     * Approach 1: Recursion
     */
    public static ListNode reverseBetween_1(ListNode head, int m, int n) {
        return recursion1(head, m, n ,1);
    }

    public static ListNode recursion(ListNode node, int i, int j, int cur){
        if(cur == j){
            return node;
        }
        if(cur < i){
            node.next = recursion(node.next, i, j,cur + 1);
            return node;
        }else{
            ListNode last = recursion(node.next, i, j,cur + 1);
            ListNode next = node.next.next;
            node.next.next = node;
            node.next = next;
            return last;
        }
    }
    // 好理解的递归
    // step 1: 1 -> 2 -> 3 -> 4 -> 5
    // step 2: 1 -> 2
    //               \
    //           4 -> 3 -> 5
    // step 3:          1
    //                   \
    //          4 -> 3 -> 2 -> 5
    // step 4: 1 -> 4 -> 3 -> 2 -> 5
    public static ListNode recursion1(ListNode head, int i, int j, int cur){
        if(cur == j){
            return head;
        }
        if(cur < i){
            head.next = recursion1(head.next, i, j,cur + 1);
            return head;
        }else{
            ListNode last = recursion1(head.next, i, j,cur + 1);
            ListNode tail = head.next;
            head.next = tail.next;
            tail.next = head;
            return last;
        }
    }
    /**
     * 递归
     */
    public ListNode reverseBetween9(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        dummy.next = head;
        for (int i = 0; i < m - 1; i++) {
            cur = cur.next;
        }
        cur.next = reverseList(cur.next,n - m);
        return dummy.next;
    }

    public ListNode reverseList(ListNode head, int n) {
        if (n == 0) {
            return head;
        }
        ListNode last = reverseList(head.next,n - 1); //1-2-3-4-5-null
        ListNode tail = head.next;
        head.next = tail.next;
        tail.next = head;
        return last;
    }
}
