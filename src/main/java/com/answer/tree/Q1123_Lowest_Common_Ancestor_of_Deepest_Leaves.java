package com.answer.tree;

import com.template.TreeNode;

public class Q1123_Lowest_Common_Ancestor_of_Deepest_Leaves {
    /**
     * 最深叶节点的最近公共祖先
     * 给你一个有根节点 root 的二叉树，返回它 最深的叶节点的最近公共祖先 。
     * 回想一下：
     *  叶节点 是二叉树中没有子节点的节点
     *  树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
     *  如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
     * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
     * Recall that:
     *  The node of a binary tree is a leaf if and only if it has no children
     *  The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
     *  The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.
     */
    public static void main(String[] args) {
        // root = [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left= node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;

        TreeNode node = lcaDeepestLeaves(node1);
        System.out.println(node.value);
    }
    /**
     * DFS
     * 如果左子树深度比右子树深，那么最深的叶子结点肯定在左边，最近公共祖先也肯定在左子树那边；
     * 如果右子树深度比左子树深，那么最深的叶子结点肯定在右边，最近公共祖先也肯定在右子树那边；
     * 如果左右两边深度一样，那么最下面那一层肯定都是叶子节点，所以最近公共祖先就是当前结点；
     *
     * Q104 Maximum Depth of Binary Tree
     * Q236 Lowest Common Ancestor of a Binary Tree
     */
    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null) {
            return null;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        if(left == right){
            return root;
        }else if(left > right){
            return lcaDeepestLeaves(root.left);
        }else{
            return lcaDeepestLeaves(root.right);
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

}
