package com.answer.math.carry;


import com.answer.linkedlist.ListNode;

import java.util.*;

public class Q445_Add_Two_Numbers_II {
    /**
     * given two non-empty linked lists representing two non-negative integers. The most significant digit
     * comes first and each of their nodes contains a single digit. Add the two numbers and return the sum
     * as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会
     * 返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     */
    /**
     * 方法一：栈
     * 为了逆序处理所有数位，我们可以使用栈：把所有数字压入栈中，再依次取出相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, null);
        int carry = 0;
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while(l1 != null){ // should be while
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry > 0){
            int x = !stack1.isEmpty() ? stack1.pop() : 0;
            int y = !stack2.isEmpty() ? stack2.pop() : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            ListNode cur = new ListNode(sum % 10);
            cur.next = dummy.next;
            dummy.next = cur;
        }

        return dummy.next;
    }

/*      ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
*/
    /**
     * 方法一：递归写法
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2); // l1 和 l2 反转后，就变成【2. 两数相加】了
        ListNode l3 = addTwo(l1, l2, 0);
        return reverseList(l3);
    }
    // 反转链表
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head; // 把下一个节点指向自己
        head.next = null; // 断开指向下一个节点的连接，保证最终链表的末尾节点的 next 是空节点
        return newHead;
    }
    // l1 和 l2 为当前遍历的节点，carry 为进位
    private ListNode addTwo(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) { // 递归边界：l1 和 l2 都是空节点
            return carry != 0 ? new ListNode(carry) : null; // 如果进位了，就额外创建一个节点
        }
        if (l1 == null) { // 如果 l1 是空的，那么此时 l2 一定不是空节点
            l1 = l2;
            l2 = null; // 交换 l1 与 l2，保证 l1 非空，从而简化代码
        }
        carry += l1.val + (l2 != null ? l2.val : 0); // 节点值和进位加在一起
        l1.val = carry % 10; // 每个节点保存一个数位
        l1.next = addTwo(l1.next, (l2 != null ? l2.next : null), carry / 10); // 进位
        return l1;
    }
    /**
     * 方法二：迭代写法
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        l1 = reverseList2(l1);
        l2 = reverseList2(l2); // l1 和 l2 反转后，就变成【2. 两数相加】了
        ListNode l3 = addTwo2(l1, l2);
        return reverseList2(l3);
    }
    // 视频讲解 https://www.bilibili.com/video/BV1sd4y1x7KN/
    private ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    private ListNode addTwo2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(); // 哨兵节点
        ListNode cur = dummy;
        int carry = 0; // 进位
        while (l1 != null || l2 != null || carry != 0) { // 有一个不是空节点，或者还有进位，就继续迭代
            if (l1 != null) carry += l1.val; // 节点值和进位加在一起
            if (l2 != null) carry += l2.val; // 节点值和进位加在一起
            cur.next = new ListNode(carry % 10); // 每个节点保存一个数位
            cur = cur.next;
            carry /= 10; // 新的进位
            if (l1 != null) l1 = l1.next; // 下一个节点
            if (l2 != null) l2 = l2.next; // 下一个节点
        }
        return dummy.next; // 哨兵节点的下一个节点就是头节点
    }
}
