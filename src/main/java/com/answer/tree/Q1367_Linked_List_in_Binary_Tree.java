package com.answer.tree;

import com.leetcode.ListNode;
import com.template.TreeNode;

import java.util.*;

public class Q1367_Linked_List_in_Binary_Tree {
    /**
     * 二叉树中的链表
     * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
     * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
     * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
     * Given a binary tree root and a linked list with head as the first node.
     * Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
     * In this context downward path means a path that starts at some node and goes downwards.
     *
     * 示例 1：
     * 输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * 输出：true
     * 解释：树中蓝色的节点构成了与链表对应的子路径。
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur.value == head.value){
                if(isSametree(head, cur)){
                    return true;
                }
            }
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }
        return false;
    }

    private boolean isSametree(ListNode head, TreeNode root){
        if(head == null) return true;
        if(root == null) return false;

        if(root.value == head.value){
            return isSametree(head.next, root.left) || isSametree(head.next, root.right);
        } else {
            return false;
        }
    }
    /**
     * another form
     * refer to Q572_Subtree_of_Another_Tree
     */
    boolean found = false;

    public boolean isSubPath1(ListNode head, TreeNode root) {
        dfs(head, root);
        return found;
    }

    private void dfs(ListNode head, TreeNode root){
        if(root == null) return;
        if(root.value == head.value && isSametree1(head, root)){
            found = true;
        }
        dfs(head, root.left);
        dfs(head, root.right);
    }

    public boolean isSametree1(ListNode head, TreeNode root) {
        if(head == null ) return true;
        if(root == null) return false;
        return head.value == root.value && (isSametree1(head.next, root.left) || isSametree1(head.next,root.right));
    }
    /**
     * 这题就是Q572_Subtree_of_Another_Tree，一模一样，代码简洁
     */
    public boolean isSubPath2(ListNode head, TreeNode root) {
        if (head == null) { // can be commented
            return true;
        }
        if (root == null) {
            return false;
        }
        //先判断当前的节点，如果不对，再看左子树和右子树呗
        return isSub(head, root) || isSubPath2(head, root.left) || isSubPath2(head, root.right);
    }

    private boolean isSub(ListNode head, TreeNode node) {
        // 链表已经全部匹配完，匹配成功
        if (head == null) { //特判：链表走完了，返回true
            return true;
        }
        // 二叉树访问到了空节点，匹配失败
        if (node == null) { //特判：链表没走完，树走完了，这肯定不行，返回false
            return false;
        }
        // 当前匹配的二叉树上节点的值与链表节点的值不相等，匹配失败
        if (head.value != node.value) {  //如果值不同，必定不是啊
            return false;
        }
        return isSub(head.next, node.left) || isSub(head.next, node.right);    //如果值相同，继续看，左边和右边有一个满足即可
    }
}
