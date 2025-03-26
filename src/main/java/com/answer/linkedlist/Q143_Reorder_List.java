package com.answer.linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q143_Reorder_List {

    public static void main(String[] args) {
        //head = [1,2,3,4,5]
        //  ListNode node5 = new ListNode(5, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1,node2);

        reorderList(node1);
        node1.print();
    }
    /**
     * 方法二: 把链表放进双向队列，然后通过双向队列一前一后弹出数据，来构造新的链表。这种方法比操作数组容易一些，不用双指针模拟一前一后了
     */
   static public void reorderList(ListNode head) {
        Deque<ListNode> list = new ArrayDeque<>(); // 使用双端队列，简化了数组的操作，代码相对于前者更简洁（避免一些边界条件）
        ListNode cur = head.next;  // 这里是取head的下一个节点，head不需要再入队了，避免造成重复
        while(cur != null){
            list.offer(cur);
            cur = cur.next;
        }
        int count = 0; // 计数，偶数取后面，奇数取前面
        cur = head;  // 回到头部
        while(!list.isEmpty()){
            ListNode node;
            if(count % 2 != 0){
                node = list.poll(); // 奇数，取出队列左边头部的值

            }else{
                node = list.pollLast();  // 偶数，取出队列右边尾部的值
            }
            count++;
            cur.next = node;
            cur = cur.next;
        }
        cur.next = null; // 注意结尾
    }
    /**
     * 方法一: 使用数组存储节点, 把链表放进数组中，然后通过双指针法，一前一后，来遍历数组，构造链表。
     */
    public static void reorderList0(ListNode head) {
        List<ListNode> list = new ArrayList<>();  // ArrayList底层是数组，可以使用下标随机访问
        ListNode cur = head;
        while(cur != null){
            list.add(cur);
            cur = cur.next;
        }
        int i = 0, j = list.size() - 1; // i j为之前前后的双指针
        while(i < j){
            list.get(i).next = list.get(j);
            i++;
            if(i == j){ //偶数个节点的情况，会提前相遇
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;  // 注意结尾
/*        int i = 0; int j = list.size() - 1;
        while(i <= j){
            ListNode node;
            if(count % 2 == 0){ // 偶数
                node = list.get(j);
                j--;
            }else{ // 奇数
                node =list.get(i);
                i++;
            }
            count++;
            cur.next = node;
            cur = cur.next;  // 每一次指针都需要移动
        }
        cur.next = null;*/  // 注意结尾要结束一波
    }
    /**
     * Approach 1: Reverse the Second Part of the List and Merge Two Sorted Lists
     * This problem is a combination of these three easy problems:
     *
     * Middle of the Linked List.
     * Reverse the Second Part of the List.
     * Merge Two Sorted Lists.
     * 方法三: 将链表分割成两个链表，然后把第二个链表反转，之后在通过两个链表拼接成新的链表。
     * 将链表从中间分成两部分，将后部分链表反转，然后同时遍历两链表进行拼接。
     */
    public void reorderList_1(ListNode head) {
        ListNode fast = head, slow = head;
        //求出中点
        while (fast.next != null && fast.next.next != null) { // if the count of elements is odd, slow is in the middle.
            slow = slow.next;                                 // if the count of elements is even, slow is the left to the middle.
            fast = fast.next.next;
        }
        //right就是右半部分 12345 就是45  1234 就是34
        ListNode right = slow.next;
        //断开左部分和右部分
        slow.next = null;
        //反转右部分 right就是反转后右部分的起点
        right = reverseList(right);
        // ListNode right = reverseList_1(slow.next); // the above 3 lines of code can be replaced with these 2 lines
        // slow.next = null;
        //左部分的起点
        ListNode left = head;
        //进行左右部分来回连接
        //这里左部分的节点个数一定大于等于右部分的节点个数 因此只判断right即可
        while (right != null) {
            ListNode curLeft = left.next;
            left.next = right;
            left = curLeft;

            ListNode curRight = right.next;
            right.next = left;
            right = curRight;
        }
    }
    // 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode headNode = new ListNode(0);
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = headNode.next;
            headNode.next = cur;
            cur = next;
        }
        return headNode.next;
    }
    /**
     * Reverse linkedlist
     */
    public ListNode reverseList_1(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
