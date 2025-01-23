package com.answer.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.*;

public class Q234_Palindrome_Linked_List_1 {
    public static void main(String[] args) {
        ListNode node4 = new ListNode(1, null);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1= new ListNode(1,node2);
        System.out.println(isPalindrome_3(node1));
    }
    /**
     * Approach 3: Reverse Second Half In-place 快慢指针
     * Find mid node + reverse + compare
     */
    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null){
            slow = slow.next;
        }
        slow = reverse(slow);

        fast = head;
        while(slow != null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static ListNode reverse(ListNode head){
        ListNode pre = null;
        while(head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    /**
     * Use stack 使用栈
     */
    static public boolean isPalindrome_1(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode temp = head;
        while(temp != null){
            stack.push(temp);
            temp = temp.next;
        }
        temp = head;
        while(!stack.isEmpty()){
            if(temp.val != stack.pop().val){
                return false;
            }
            temp = temp.next;
        }
        return true;
    }
    /**
     * Approach 1: Copy into Array List and then Use Two Pointer Technique
     * use list and two pointers. 使用队列和双指针
     */
    public static boolean isPalindrome_3(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        int left = 0, right = list.size() - 1;
        while(left <= right){
            if(list.get(left).val != list.get(right).val){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
