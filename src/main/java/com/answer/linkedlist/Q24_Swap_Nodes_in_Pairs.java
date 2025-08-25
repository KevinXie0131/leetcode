package com.answer.linkedlist;

public class Q24_Swap_Nodes_in_Pairs {
    /**
     * 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
     * 示例 1：
     *  输入：head = [1,2,3,4]
     *  输出：[2,1,4,3]
     */
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        ListNode node = swapPairs_Recursive(node1);
        node.print();
    }
    /**
     * dummy node
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            first.next = second.next;
            second.next = first;
            cur.next = second;

            cur = first;
        }
        return dummy.next;
    }
    /**
     * dummy node + pre node + extra node
     * 使用两个辅助指针 pre 和 cur，每次交换 cur 和 cur->next 两个结点，交换终止条件为 pre 后面不存在两个未交换结点。
     */
    public static ListNode swapPairs_1(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null && cur.next != null){
            ListNode start = cur;
            ListNode end = cur.next;

            start.next = end.next;
            end.next = start;
            pre.next = end;

            pre = start;
            cur = start.next;
        }
        return dummy.next;
    }
    /**
     * dummy node + pre node 同上
     */
    public static ListNode swapPairs_2(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null && cur.next != null){
            ListNode nxt = cur.next;

            cur.next = cur.next.next;
            nxt.next = cur;
            pre.next = nxt;

            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
    /**
     * 虚拟头结点
     */
    public ListNode swapPairs_3(ListNode head) {
        ListNode dummy = new ListNode(); // 设置一个虚拟头结点
        dummy.next = head; // 将虚拟头结点指向head，这样方便后面做删除操作
        ListNode cur = dummy;
        ListNode last; // 临时节点，保存两个节点后面的节点
        ListNode first; // 临时节点，保存两个节点之中的第一个节点
        ListNode second; // 临时节点，保存两个节点之中的第二个节点

        while(cur.next != null &&  cur.next.next != null){ //剩下的链表长度大于等于2
            first = cur.next;
            second = cur.next.next;
            last = second.next;

            cur.next = second;  // 步骤一
            second.next = first; // 步骤二
            first.next = last;  // 步骤三

            cur = first; // cur移动，准备下一轮交换
        }
        return dummy.next;
    }
    /**
     * 将步骤 2,3 交换顺序，这样不用定义 temp 节点
     */
    public ListNode swapPairs_4(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode node1 = cur.next;// 第 1 个节点
            ListNode node2 = cur.next.next;// 第 2 个节点
            cur.next = node2; // 步骤 1
            node1.next = node2.next;// 步骤 3
            node2.next = node1;// 步骤 2
            cur = cur.next.next;
        }
        return dummy.next;
    }
    /**
     * 万能方法: dummy假头 + 插头法
     * refer to Q92_Reverse_Linked_List_II
     */
    public ListNode swapPairs_4a(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode nxt = cur.next.next;
            cur.next.next = nxt.next;
            nxt.next = cur.next;;
            cur.next = nxt;
            cur = cur.next.next;
        }
        return dummy.next;
    }
    /**
     * 方法一：迭代
     */
    public ListNode swapPairs7(ListNode head) {
        ListNode dummy = new ListNode(0, head); // 用哨兵节点简化代码逻辑
        ListNode node0 = dummy;
        ListNode node1 = head;
        while (node1 != null && node1.next != null) { // 至少有两个节点
            ListNode node2 = node1.next;
            ListNode node3 = node2.next;

            node0.next = node2; // 0 -> 2
            node2.next = node1; // 2 -> 1
            node1.next = node3; // 1 -> 3

            node0 = node1; // 下一轮交换，0 是 1
            node1 = node3; // 下一轮交换，1 是 3
        }
        return dummy.next; // 返回新链表的头节点
    }
    /**
     * 方法二：递归
     */
    public ListNode swapPairs9(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode node3 = node2.next;

        node1.next = swapPairs9(node3); // 1 指向递归返回的链表头
        node2.next = node1; // 2 指向 1

        return node2; // 返回交换后的链表头节点
    }
    /**
     * 递归
     * 需要交换的两个点为 head 和 next，head 连接后面交换完成的子链表，next 连接 head，完成交换
     */
    public ListNode swapPairs_Recursive_0(ListNode head) {
        if(head == null || head.next == null){ // 递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
            return head;
        }

        ListNode next = head.next;
        head.next = swapPairs_Recursive_0(head.next.next); // 将其余节点进行两两交换
        next.next = head;

        return next;
    }
    /**
     * 同上
     * 两两翻转链表中的节点+返回翻转后的新的头结点
     */
    public static ListNode swapPairs_Recursive_2(ListNode first) {
        if(first == null || first.next == null) {  // base case 退出提交
            return first;
        }
        // 我们知道翻转后新的头结点必然是第二个节点. 举例子:1->2->3->4 翻转后:2->1->4->3
        ListNode second = first.next;   // 获取当前节点的下一个节点 // 这句就先保存节点2
        //继续递归，处理节点3->4. 当递归结束返回后，就变成了4->3. 于是head节点就指向了4，变成1->4->3
        ListNode third = swapPairs_Recursive_2(second.next);  // 进行递归
        //将2节点指向1
        second.next = first; // 这里进行交换
        first.next = third;

        return second;
    }
    /**
     * Recursive - from head to tail 递归
     */
    public static ListNode swapPairs_Recursive(ListNode first) {
        if(first == null || first.next == null){
            return first;
        }
        ListNode third = first.next.next;

        ListNode second = first.next;
        second.next = first;

        first.next = swapPairs_Recursive(third); // head.next = swapPairs_Recursive(head.next.next); // not work
        return second;
    }
    /**
     * 递归 同上
     * 标记好1、2、3号节点
     */
    public ListNode swapPairs_5(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode one = head;
        ListNode two = one.next;
        ListNode three = two.next;

        two.next = one;
        one.next = swapPairs_5(three);

        return two;
    }
    /**
     * Recursive - from tail to head 递归
     */
    public static ListNode swapPairs_Recursive_1(ListNode first) {
        if(first == null || first.next == null){
            return first;
        }

        ListNode third = swapPairs_Recursive_1(first.next.next);

        ListNode second = first.next;
        second.next = first;
        first.next = third;

        return second;
    }
}
