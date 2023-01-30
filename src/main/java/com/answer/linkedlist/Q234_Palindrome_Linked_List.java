package com.answer.linkedlist;

public class Q234_Palindrome_Linked_List {
    public static void main(String[] args) {
    //    ListNode node4 = new ListNode(1, null);
     //   ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(0, null);
        ListNode node1= new ListNode(0,node2);
        System.out.println(isPalindrome(node1));
    }
    /**
     * Recursion
     */
    static ListNode root = null;
    static boolean res = true;

    static public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        root = head;
        dfs(head);
        return res;
    }

    static public void dfs(ListNode node){
        if(node == null) return;
        if(res == false) return;
        dfs(node.next);
        if(root.val == node.val){
            root = root.next;
        }else {
            res = false;;
        }

    }
    /**
     * Recursion
     */
    static ListNode root1 = null;

    static public boolean isPalindrome_1(ListNode head) {
        if(head == null || head.next == null) return true;
        root1 = head;
        return dfs_1(head);
    }

    static public boolean dfs_1(ListNode node){
        if(node == null) return true;
        boolean isFound = dfs_1(node.next);
        boolean res = true;
        if(root1.val != node.val){
            res = false;
        }
        root1 = root1.next;
        return res && isFound;
    }
}
