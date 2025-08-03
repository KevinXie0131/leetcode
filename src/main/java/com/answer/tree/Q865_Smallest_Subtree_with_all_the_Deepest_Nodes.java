package com.answer.tree;

import com.template.TreeNode;

public class Q865_Smallest_Subtree_with_all_the_Deepest_Nodes {
    /**
     * 具有所有最深节点的最小子树
     * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
     * 返回包含原始树中所有 最深节点 的 最小子树 。
     * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
     * 一个节点的 子树 是该节点加上它的所有后代的集合。
     * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
     * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
     * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
     * The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
     */
    /**
     * similar with Q1123 最深叶节点的最近公共祖先. The same solution can be used
     * refer to Q1123_Lowest_Common_Ancestor_of_Deepest_Leaves
     *
     * 思路：从每个树开始，获得当前节点的左右子树的最大深度
     *      深度相同，说明最深的节点在这个节点两边，那这个节点就是结果
     *      如果深度不相同，则去深度大的子树继续判断，最终就能得到结果
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) {
            return null;
        }
        int left = maxDepth(root.left);  // 获取当前节点的左右子树的最大深度
        int right = maxDepth(root.right);

        if(left == right) {  // 如果两边最大深度相同，则这个节点就是结果
            return root;
        } else if (left > right) {    // 不相等，那就去深度大的子树那边继续找
            return subtreeWithAllDeepest(root.left);
        } else {
            return subtreeWithAllDeepest(root.right);
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
