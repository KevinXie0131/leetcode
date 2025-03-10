package com.answer.tree;

import com.template.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Q437_Path_Sum_III {
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
