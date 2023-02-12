package com.answer.tree;

import com.template.TreeNode;

public class Q998_Maximum_Binary_Tree_II {
    /**
     * 方法一：遍历右子节点
     *
     */
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode cur = root;

        while(cur != null){
            if(val > cur.value){
                if(parent == null){
                    return new TreeNode(val, root, null);
                }
                TreeNode node = new TreeNode(val, cur, null);
                parent.right = node;
                return root;
            }else{
                parent = cur;
                cur = cur.right;
            }
        }
        parent.right = new TreeNode(val);
        return root;
    }
    /**
     * Another form
     */
    public TreeNode insertIntoMaxTree_1(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        TreeNode cur = root, prev = null;
        while(cur != null && cur.value > val){
            prev = cur;
            cur = cur.right;
        }
        if(prev == null){
            node.left = cur;
            return node;
        }else{
            prev.right = node;
            node.left = cur;
            return root;
        }
    }
    /**
     * Recursion
     */
    public TreeNode insertIntoMaxTree_2(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        if(root.value < val){
            return new TreeNode(val, root, null);
        }
        root.right = insertIntoMaxTree_2(root.right, val);

        return root;
    }
}
