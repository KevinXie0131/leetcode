package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q783_Minimum_Distance_Between_BST_Nodes {
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

        System.out.println(minDiffInBST(node1));

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
     * Approach 1: In-order Traversal with List
     */
    // List to store the tree nodes in the inorder traversal.
    List<Integer> inorderNodes = new ArrayList<>();

    public int minDiffInBST_0(TreeNode root) {
        inorderTraversal(root);

        int minDistance = Integer.MAX_VALUE;
        // Find the diff between every two consecutive values in the list.
        for (int i = 1; i < inorderNodes.size(); i++) {
            minDistance = Math.min(minDistance, inorderNodes.get(i) - inorderNodes.get(i-1));
        }

        return minDistance;
    }
    void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        // Store the nodes in the list.
        inorderNodes.add(root.value);
        inorderTraversal(root.right);
    }
    /**
     * Iteration - Inorder
     */
    public int minDiffInBST_1(TreeNode root) {
        int min = Integer.MAX_VALUE;
        int pre = Integer.MAX_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()){
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();

                if(pre == Integer.MAX_VALUE){
                    pre = cur.value;
                }else{
                    min = Math.min(min, cur.value - pre);
                    pre = cur.value;
                }

                cur = cur.right;
            }
        }
        return min;
    }
}
