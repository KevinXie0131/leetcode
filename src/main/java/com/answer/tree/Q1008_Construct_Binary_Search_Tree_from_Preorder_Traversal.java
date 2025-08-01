package com.answer.tree;

import com.template.TreeNode;

public class Q1008_Construct_Binary_Search_Tree_from_Preorder_Traversal {
    /**
     * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
     * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
     * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , Node.right 的任何后代的值 严格大于 Node.val。
     * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
     * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree), construct the tree and return its root.
     * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the given test cases.
     * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
     * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then traverses Node.right.
     */
    /**
     * refer to Q449_Serialize_and_Deserialize_BST
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return build2(preorder, 0, preorder.length - 1);
    }

    private TreeNode build2(int[] vals, int left, int right) {
        if(left > right) return null;

        int val = vals[left];
        TreeNode node = new TreeNode(val);
        int j = left + 1;
        while (j <= right && vals[j] <= val){
            j++;
        }
        node.left = build2(vals, left + 1, j - 1);
        node.right = build2(vals, j, right);
        return node;
    }
    /**
     * refer to Q449_Serialize_and_Deserialize_BST
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        int[] idx = {0};
        return build(preorder, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] vals, int[] idx, int lower, int upper) {
        if (idx[0] == vals.length) {// 递归终止条件
            return null;
        }
        int val = vals[idx[0]];
        // 当前值必须在有效区间
        if (val < lower || val > upper) { // 当前值不在合法区间，不能放在这里
            return null;
        }
        TreeNode node = new TreeNode(val);
        idx[0]++;   // 构建当前节点
        node.left = build(vals, idx, lower, val - 1); // 构建左子树，区间为：[lower, val-1]
        node.right = build(vals, idx, val + 1, upper); // 构建右子树，区间为：[val+1, upper]
        return node;
    }
}
