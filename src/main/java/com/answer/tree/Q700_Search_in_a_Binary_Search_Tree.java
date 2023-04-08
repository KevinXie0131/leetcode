package com.answer.tree;

import com.template.TreeNode;

public class Q700_Search_in_a_Binary_Search_Tree {
    /**
     * ⼆叉搜索树中的搜索
     * ⼆叉搜索树是⼀个有序树：
     *    若它的左⼦树不空，则左⼦树上所有结点的值均⼩于它的根结点的值；
     *    若它的右⼦树不空，则右⼦树上所有结点的值均⼤于它的根结点的值；
     *    它的左、右⼦树也分别为⼆叉搜索树
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.value == val)
            return root;
        // 如果root->val > val，搜索左⼦树，如果root->val < val，就搜索右⼦树，最后如果都没有搜索到，就返回NULL。
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
