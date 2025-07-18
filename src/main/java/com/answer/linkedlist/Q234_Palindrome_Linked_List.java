package com.answer.linkedlist;

import java.util.*;

public class Q234_Palindrome_Linked_List {
    /**
     * 回文链表
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
     * 示例 1：
     *  输入：head = [1,2,2,1]
     *  输出：true
     *
     * Follow up: Could you do it in O(n) time and O(1) space?
     * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     */
    public static void main(String[] args) {
    //    ListNode node4 = new ListNode(1, null);
     //   ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(0, null);
        ListNode node1= new ListNode(0,node2);
        System.out.println(isPalindrome(node1));
    }
    /**
     * 递归（另一种形式）+ 双指针
     */
    ListNode root2 = null;

    public boolean isPalindrome_0(ListNode head) {
        if(head == null || head.next == null) return true;
        root2 = head;
        return dfs_2(head);
    }

    public boolean dfs_2(ListNode node){
        if(node == null) {
            return true;
        }
        boolean isFound = dfs_2(node.next);
        if(root2.val != node.val){
            return false;
        }
        root2 = root2.next;
        return isFound;
    }
    /**
     * 递归 + 双指针 （另一种形式）
     * 如果使用递归反向迭代节点，同时使用递归函数外的变量向前迭代，就可以判断链表是否为回文。
     */
    ListNode root3 = null;
    public boolean isPalindrome5(ListNode head) {
        if(head == null || head.next == null) return true;
        root3 = head;
        return dfs_3(head);
    }

    public boolean dfs_3(ListNode node){
        if(node == null) {
            return true;
        }
        if(!dfs_3(node.next)){
            return false;
        }
        if(root3.val != node.val){
            return false;
        }
        root3 = root3.next;
        return true;
    }
    /**
     * Recursion 递归
     */
    static ListNode root = null;
    static boolean res = true; // 加一个全局变量

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
            res = false;
        }
    }
    // works too
    static public void dfs_a(ListNode node){
        if(node == null) return;
        dfs_a(node.next);
        if(res == false) return; // works too
        if(root.val == node.val){
            root = root.next;
        }else {
            res = false;
        }
    }
    /**
     * Recursion 递归
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
    /**
     * 最直接的想法，就是把链表装成数组，然后再判断是否回文。
     * 数组 + 双指针
     */
    public boolean isPalindrome2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur);
            cur = cur.next;
        }
        int i = 0; int j = list.size() - 1;
        while(i < j){ // 比较数组回文
            if(list.get(i).val != list.get(j).val){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    /**
     * 方法二，快慢指针
     * 用快慢指针，快指针有两步，慢指针走一步，快指针遇到终止位置时，慢指针就在链表中间位置
     * 同时用pre记录慢指针指向节点的前一个节点，用来分割链表
     * 将链表分为前后均等两部分，如果链表长度是奇数，那么后半部分多一个节点
     * 将后半部分反转 ，得cur2，前半部分为cur1
     * 按照cur1的长度，一次比较cur1和cur2的节点数值
     */
    public boolean isPalindrome3(ListNode head) {
        if (head == null && head.next == null) return true;// 如果为空或者仅有一个节点，返回true
        ListNode slow = head; // 慢指针，找到链表中间分位置，作为分割
        ListNode fast = head;
        ListNode pre = head;  // 记录慢指针的前一个节点，用来分割链表
        while (fast != null && fast.next != null){
            pre = slow;  // 记录slow的前一个结点
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;  // 分割两个链表

        // 前半部分
        ListNode cur1 = head;
        // 后半部分。这里使用了反转链表
        ListNode cur2 = reverseList(slow); // 反转后半部分，总链表长度如果是奇数，cur2比cur1多一个节点
        // 开始两个链表的比较
        while (cur1 != null){
            if (cur1.val != cur2.val) return false;
            // 注意要移动两个结点
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return true;
    }
    // 反转链表
    ListNode reverseList(ListNode head){
        ListNode tmp = null; // 保存cur的下一个节点
        ListNode pre = null;
        while (head != null){
            tmp = head.next; // 保存一下 cur的下一个节点，因为接下来要改变cur->next
            head.next = pre; // 翻转操作
            pre = head;  // 更新pre 和 cur指针
            head = tmp;
        }
        return pre;
    }
}
