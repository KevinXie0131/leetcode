package com.answer.tree;

import com.template.TreeNode;

public class Q700_Search_in_a_Binary_Search_Tree {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.value == val)
            return root;

        TreeNode result = null;
        if (root.value > val)
            result = searchBST(root.left, val);
        if (root.value < val)
            result = searchBST(root.right, val);

        return result;

    }
    /**
     *
     */
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null || root.value == val) {
            return root;
        }
        if (root.value < val) {
            return searchBST(root.right, val);
        }
        if (root.value > val) {
            return searchBST(root.left, val);
        }

        return null;

    }
    /**
     *
     */
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null) {
            if (root.value == val) {
                return root;
            } else if (root.value > val) {
                root = root.left;
            } else if (root.value < val) {
                root = root.right;
            }

        }

        return null;

    }
}
