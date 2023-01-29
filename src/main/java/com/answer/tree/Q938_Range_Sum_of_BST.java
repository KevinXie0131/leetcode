package com.answer.tree;

import com.template.TreeNode;

public class Q938_Range_Sum_of_BST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(18);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;

        System.out.println(rangeSumBST( root, 7, 15));
    }

    public static int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null || (root.value < low && root.value > high)){
            return 0;
        }
        int left = rangeSumBST(root.left, low, high);
        int right = rangeSumBST(root.right, low, high);

        if(root.value >= low && root.value <= high){
           return root.value + left + right;
        } else {
            return left + right;
        }
    }
}
