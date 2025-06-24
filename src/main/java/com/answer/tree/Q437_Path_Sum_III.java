package com.answer.tree;

import com.template.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Q437_Path_Sum_III {
    /**
     * 路径总和 III
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
     * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
     */
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
        System.out.println(pathSum_1(node1, 8));
    }

    /**
     * Recursion
     * Time: N2
     * Space: N
     */
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

    static public void dfs(TreeNode node, long targetSum) {
        if (node == null) {
            return;
        }
        if (targetSum == node.value) {
            res++;
        }
        if (node.left != null) dfs(node.left, targetSum - node.value);
        if (node.right != null) dfs(node.right, targetSum - node.value);
    }

    /**
     * Official answer
     */
    public static int pathSum_1(TreeNode root, long targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return dfs_1(root, map, 0, targetSum);
    }

    public static int dfs_1(TreeNode node, Map<Long, Integer> prefix, long curr, long targetSum) {
        if (node == null) {
            return 0;
        }
        int ret = 0;
        curr += node.value;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs_1(node.left, prefix, curr, targetSum);
        ret += dfs_1(node.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }
}
