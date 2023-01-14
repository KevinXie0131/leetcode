package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q98_Validate_Binary_Search_Tree {

    public boolean isValidBST(TreeNode root) {
        double result = -Double.MAX_VALUE;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            if(cur.value <= result ) {
                return false;
            } else {
                result = cur.value;
            }
            root = cur.right;
        }
        return true;
    }
    /**
     *
     */
    public boolean isValidBST1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long pre = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode cur = stack.pop();
            if (cur.value > pre) {
                pre = cur.value;
            } else {
                return false;
            }
            root = cur.right;

        }
        return true;
    }
    /**
     *
     */
    TreeNode pre = null;

    public boolean isValidBST2(TreeNode root) {

        if (root == null) return true;

        boolean left = isValidBST(root.left);

        if (pre != null && pre.value >= root.value) {
            return false;
        }
        pre = root;

        boolean right = isValidBST(root.right);

        return left && right;
    }
    /**
     *
     */
    public boolean isValidBST3(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean dfs(TreeNode root,  long lower, long upper){
        if(root==null){
            return true;
        }
        if (root.value >= upper || root.value <= lower){
            return false;
        }

        boolean left = dfs(root.left, lower, root.value);
        boolean right = dfs(root.right, root.value, upper);
        return left && right;

    }
}