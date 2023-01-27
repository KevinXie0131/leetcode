package com.answer.array;

import  com.answer.linkedlist.ListNode;

import java.util.*;

public class Q445_Add_Two_Numbers_II {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1, null);
        int carry = 0;
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while(l1 != null){
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
}
