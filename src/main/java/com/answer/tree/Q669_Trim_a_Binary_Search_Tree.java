package com.answer.tree;

import com.template.TreeNode;

public class Q669_Trim_a_Binary_Search_Tree {
    /**
     * 修剪⼆叉搜索树
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {

        if(root == null){
            return null;
        }
        // 如果root（当前节点）的元素⼩于low的数值，那么应该递归右⼦树，并返回右⼦树符合条件的头结点。
        if (root.value < low) {
            return trimBST( root.right,  low,  high); // 寻找符合区间[low, high]的节点
            /**
             * TreeNode right = trimBST( root.right,  low,  high);
             * return right;
             */
        }
        // 如果root(当前节点)的元素⼤于high的，那么应该递归左⼦树，并返回左⼦树符合条件的头结点
        if (root.value > high) {
            return trimBST( root.left,  low,  high); // 寻找符合区间[low,high]的节点
            /**
             * TreeNode left = trimBST( root.left,  low,  high);
             * return left;
             */
        }
        if (root.value >= low && root.value <= high) {
            root.left = trimBST( root.left,  low,  high); // root->left接⼊符合条件的左孩⼦
            root.right = trimBST( root.right,  low,  high); // root->right接⼊符合条件的右孩⼦
        }

        return root;
    }
    /**
     *
     */
    public TreeNode trimBST1(TreeNode root, int low, int high) {
        // 找到修剪之后的二叉搜索树的头节点 root
        if(root == null){
            return null;
        }
        while(root != null && (root.value > high || root.value < low)){
            if(root.value > high){
                root = root.left;
            }
            else{
                root = root.right;
            }
        }
        TreeNode cur = root;
        // 修剪 root 的左子树，将 < low 的节点删除
        while(cur != null){
            while(cur.left != null && cur.left.value < low){
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }
        // 修剪 root 的右子树，将 > high 的节点删除
        cur = root;
        while(cur != null){
            while(cur.right != null && cur.right.value > high){
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }
        return root;
    }
    /**
     *
     */
    public TreeNode trimBST2(TreeNode root, int low, int high) {

        if(root == null){
            return null;
        }
        while(root != null && (root.value < low || root.value > high)){
            if (root.value < low) {
                root = root.right;
            }
            if (root.value > high) {
                root = root.left;
            }
        }

        TreeNode cur = root;
        while(cur != null){
            while(cur.left != null && cur.left.value < low){
                cur.left = cur.left.right;
            }
            cur = cur.left;
        }

        cur = root;
        while(cur != null){
            while(cur.right != null && cur.right.value > high){
                cur.right = cur.right.left;
            }
            cur = cur.right;
        }

        return root;
    }
}
