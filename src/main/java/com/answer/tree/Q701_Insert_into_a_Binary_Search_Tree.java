package com.answer.tree;

import com.template.TreeNode;

public class Q701_Insert_into_a_Binary_Search_Tree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        if (root.value > val) {
            root.left = insertIntoBST(root.left, val);
        }
        if (root.value < val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        TreeNode parent = root;
        TreeNode cur = root;
        while(cur != null){
            parent = cur;
            if(cur.value > val){
                cur = cur.left;
            } else if (cur.value < val){
                cur = cur.right;
            }
        }
        TreeNode node = new TreeNode(val);

        if(parent.value > val){
            parent.left =  node;
        }
        if(parent.value < val){
            parent.right = node;
        }

        return root;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) {
            TreeNode node = new TreeNode(val);
            return node;
        }
        TreeNode cur = root;
        while(cur != null){
            if(cur.value > val){
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    cur.left = new TreeNode(val);
                    break;
                }
            } else if (cur.value < val){
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
