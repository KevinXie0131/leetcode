package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q783_Minimum_Distance_Between_BST_Nodes {
    /**
     * 二叉搜索树节点最小距离
     * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     * 差值是一个正数，其数值等于两值之差的绝对值。
     * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
     */
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(27);
        TreeNode node2 = new TreeNode(34);
        TreeNode node3 = new TreeNode(44);
        TreeNode node4 = new TreeNode(50);
        TreeNode node5 = new TreeNode(58);
        node1.right = node2;
        node2.right = node5;
        node5.left = node4;
        node4.left = node3;

        System.out.println(minDiffInBST_3(node1));
    }
    /**
     * Approach 2: In-order Traversal Without List
     * Recursion - Inorder:  The in-order traversal of a binary search tree produces a sorted array.
     */
    static int min = Integer.MAX_VALUE;
    static int pre = Integer.MAX_VALUE;
    // Initially, it will be null.
 //   TreeNode prevValue;
    public static int minDiffInBST(TreeNode root) {
        dfs(root);
        return min;
    }

    public static void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.left);
        if(pre == Integer.MAX_VALUE){
            pre = root.value;
        }else{
            min = Math.min(min, root.value - pre);
            pre = root.value;
        }
/*        if (prevValue != null) {
            minDistance = Math.min(minDistance, root.val - prevValue.val);
        }
        prevValue = root;*/
        dfs(root.right);
    }
    /**
     * Another form of In-order Traversal by recursion
     */
    static TreeNode pre1 = null;
    static int result =  Integer.MAX_VALUE;

    public static int minDiffInBST_3(TreeNode root) {
        if(root == null) return result;

        int left = minDiffInBST(root.left);

        if(pre1 != null){
            result = Math.min(left, root.value - pre1.value);
        }
        pre1 = root;

        int right = minDiffInBST(root.right);

        result = Math.min(right, result);
        return result;
    }
    /**
     * Approach 1: In-order Traversal with List
     */
    List<Integer> inorderNodes = new ArrayList<>();  // List to store the tree nodes in the inorder traversal.

    public int minDiffInBST_0(TreeNode root) {
        inorderTraversal(root);

        int minDistance = Integer.MAX_VALUE;
        // Find the diff between every two consecutive values in the list.
        for (int i = 1; i < inorderNodes.size(); i++) {
            minDistance = Math.min(minDistance, inorderNodes.get(i) - inorderNodes.get(i - 1));
        }
        return minDistance;
    }

    void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        inorderNodes.add(root.value); // Store the nodes in the list.
        inorderTraversal(root.right);
    }
    /**
     * Iteration - Inorder
     */
    public int minDiffInBST_1(TreeNode root) {
        int min = Integer.MAX_VALUE;
        TreeNode pre = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()){
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();

            if(pre != null){
                min = Math.min(min, cur.value - pre.value);
            }
            pre = cur;

            cur = cur.right;
        }
        return min;
    }
}
