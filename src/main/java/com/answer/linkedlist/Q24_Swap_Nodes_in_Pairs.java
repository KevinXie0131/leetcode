package com.answer.linkedlist;

public class Q24_Swap_Nodes_in_Pairs {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);

        ListNode node = swapPairs_Recursive_1(node1);
        node.print();
    }
    /**
     * dummy node
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            ListNode start = cur.next;
            ListNode end = cur.next.next;

            start.next = end.next;
            end.next = start;

            cur.next = end;
            cur = start;
        }

        return dummy.next;
    }
    /**
     * dummy node + pre node + extra node
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
     * dummy node + pre node
     */
    public static ListNode swapPairs_2(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = head;
        ListNode pre = dummy;
        while(cur != null && cur.next != null){
            ListNode temp = cur.next;

            cur.next = cur.next.next;
            temp.next = cur;

            pre.next = temp;
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
        dummy.next =head; // 将虚拟头结点指向head，这样方便后面做删除操作
        ListNode cur = dummy;
        ListNode temp; // 临时节点，保存两个节点后面的节点
        ListNode first; // 临时节点，保存两个节点之中的第一个节点
        ListNode second; // 临时节点，保存两个节点之中的第二个节点

        while(cur.next != null &&  cur.next.next != null){ //剩下的链表长度大于等于2
            first = cur.next;
            second = cur.next.next;
            temp = second.next;

            cur.next = second;  // 步骤一
            second.next = first; // 步骤二
            first.next = temp;  // 步骤三

            cur = first;// cur移动，准备下一轮交换
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
     * 递归
     */
    public ListNode swapPairs_Recursive_0(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode next = head.next;
        head.next = swapPairs_Recursive_0(head.next.next);
        next.next = head;

        return next;
    }
    /**
     * Recursive - from head to tail
     */
    public static ListNode swapPairs_Recursive(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode nextNextNode = head.next.next;

        ListNode newNode = head.next;
        newNode.next = head;

        head.next = swapPairs_Recursive(nextNextNode); // 相当于head.next = swapPairs_Recursive(head.next.next);

        return newNode;

    }
    /**
     * Recursive - from tail to head
     */
    public static ListNode swapPairs_Recursive_1(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode temp = swapPairs_Recursive_1(head.next.next);

        ListNode newNode = head.next;
        newNode.next = head;
        head.next = temp;

        return newNode;
    }

    public static ListNode swapPairs_Recursive_2(ListNode first) {
        // base case 退出提交
        if(first == null || first.next == null) {
            return first;
        }
        // 获取当前节点的下一个节点
        ListNode second = first.next;
        // 进行递归
        ListNode third = swapPairs_Recursive_2(second.next);
        // 这里进行交换
        second.next = first;
        first.next = third;

        return second;
    }
}
