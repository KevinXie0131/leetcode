package com.answer.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q234_Palindrome_Linked_List_1 {
    public static void main(String[] args) {
    //    ListNode node4 = new ListNode(1, null);
     //   ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(2, null);
        ListNode node1= new ListNode(1,node2);
        System.out.println(isPalindrome(node1));
    }

    /**
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
     * Use stack
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

}
