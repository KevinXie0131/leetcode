package com.answer.tree;

import com.template.TreeNode;

public class Q437_Path_Sum_III {
    public static void main(String[] args) {
    //   [10,5,-3,3,2,null,11,3,-2,null,1]
    //    8
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(11);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(-2);
        TreeNode node9 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;
        node5.right = node8;
        node3.right = node9;
        System.out.println(pathSum(node1, 8));
    }

    static int res = 0;
    static public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        dfs(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return res;
    }
    static public void dfs(TreeNode node, long targetSum){
        if(node == null){
            return;
        }
        if(targetSum == node.value) {
            res++;
        }
        if(node.left != null) dfs(node.left, targetSum - node.value);
        if(node.right != null) dfs(node.right, targetSum - node.value);
    }
}
