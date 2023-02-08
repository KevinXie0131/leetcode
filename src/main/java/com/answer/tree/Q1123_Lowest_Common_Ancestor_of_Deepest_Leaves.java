package com.answer.tree;

import com.template.TreeNode;

public class Q1123_Lowest_Common_Ancestor_of_Deepest_Leaves {
    public static void main(String[] args) {
        // root = [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left= node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;

        TreeNode node = lcaDeepestLeaves(node1);
        System.out.println(node.value);
    }
    /**
     * Q104 Maximum Depth of Binary Tree
     * Q236 Lowest Common Ancestor of a Binary Tree
     */
    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null) {
            return null;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        if(left == right){
            return root;
        }else if(left > right){
            return lcaDeepestLeaves(root.left);
        }else{
            return lcaDeepestLeaves(root.right);
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

}
