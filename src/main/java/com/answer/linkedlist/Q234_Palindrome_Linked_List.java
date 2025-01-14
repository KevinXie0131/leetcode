package com.answer.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class Q234_Palindrome_Linked_List {
    public static void main(String[] args) {
    //    ListNode node4 = new ListNode(1, null);
     //   ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(0, null);
        ListNode node1= new ListNode(0,node2);
        System.out.println(isPalindrome(node1));
    }
    /**
     * 递归（另一种形式）
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
    /**
     * 最直接的想法，就是把链表装成数组，然后再判断是否回文。
     */
    public boolean isPalindrome2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while(cur != null){
            list.add(cur);
            cur = cur.next;
        }
        int i = 0 ; int j = list.size() -1;
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
        // 如果为空或者仅有一个节点，返回true
        if (head == null && head.next == null) return true;
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
    ListNode reverseList(ListNode head){
        // 反转链表
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
