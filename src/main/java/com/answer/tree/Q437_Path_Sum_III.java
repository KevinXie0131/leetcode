package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

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
     * Time: N^2
     * Space: N
     */
    static int res = 0;

    static public int pathSum(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        dfs(root, targetSum);
        pathSum(root.left, targetSum); //路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
        pathSum(root.right, targetSum);
        return res;
    }

    static public void dfs(TreeNode node, long targetSum) {
        if (node == null) {
            return;
        }
        if (targetSum == node.value) {
            res++;
            // return; // not working. cannot add return here
        }
        if (node.left != null) dfs(node.left, targetSum - node.value);
        if (node.right != null) dfs(node.right, targetSum - node.value);
        // dfs(node.left, targetSum - node.val ); // works too
        // dfs(node.right, targetSum - node.val );
    }
    /**
     * 穷举所有的可能，我们访问每一个节点 node，检测以 node 为起始节点且向下延深的路径有多少种。我们递归遍历每一个节点的所有可能的路径，
     * 然后将这些路径数目加起来即为返回结果。
     * 时间复杂度：O(N^2)
     */
    public int pathSum6(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }
        int ret = rootSum6(root, targetSum);
        ret += pathSum6(root.left, targetSum);
        ret += pathSum6(root.right, targetSum);
        return ret;
    }

    public int rootSum6(TreeNode root, long targetSum) {
        int ret = 0;
        if (root == null) {
            return 0;
        }
        if (root.value == targetSum) {
            ret++;
        }
        ret += rootSum6(root.left, targetSum - root.value);
        ret += rootSum6(root.right, targetSum - root.value);
        return ret;
    }
    /**
     * Official answer 前缀和
     * 定义节点的前缀和为：由根结点到当前结点的路径上所有节点的和。我们利用先序遍历二叉树，记录下根节点 root 到当前节点 p 的路径上
     * 除当前节点以外所有节点的前缀和，在已保存的路径前缀和中查找是否存在前缀和刚好等于当前节点到根节点的前缀和 curr 减去 targetSum
     *
     * 两个节点的前缀和差值时，有一个前提：一个节点必须是另一个节点的祖先节点
     */
    public static int pathSum_1(TreeNode root, long targetSum) {
        Map<Long, Integer> map = new HashMap<>(); // 记录当前路径上出现的前缀和以及数量, 记录数量是因为有出现复数路径的可能。
        map.put(0L, 1);  // 有一个默认的前缀和0
        return dfs_1(root, map, 0, targetSum);  // 从根节点开始搜索
    }
    /**
     * 深度优先搜索，返回到达当前节点及其子节点可以得到满足条件的路径
     * @param node: 当前节点
     * @param targetSum: 目标和
     * @param curr: 根节点到当前节点的路径
     * @return: 以当前节点为最后一个节点的，节点和等于目标和的路径数
     */
    public static int dfs_1(TreeNode node, Map<Long, Integer> prefix, long curr, long targetSum) {
        if (node == null) {
            return 0;  // 空节点，满足条件路径数为0
        }
        int ret = 0;
        curr += node.value;  // 更新节点和
        // 以当前节点为最后一个节点的，满足条件的路径数就等于根节点到当前节点路径上出现的前缀和 curr - target 的个数，通过哈希表 prefix，不存在则返回 0；
        ret = prefix.getOrDefault(curr - targetSum, 0);    // 从哈希表中获取能和curr配对的前缀和个数
        // 然后将当前前缀和 curr 加入哈希表中；【返回 curr 在哈希表中的值如果不存在返回0，值+1后重新存入哈希表】
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);  // 将当前前缀和加入哈希表
        ret += dfs_1(node.left, prefix, curr, targetSum); // 递归处理左右子树
        ret += dfs_1(node.right, prefix, curr, targetSum);
        // 累加左右子节点处理的结果，同时将当前前缀和 curr 从哈希表移除【不是真的移除，而是将 preSum 的次数-1】
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1); // 这个节点所在的路径都处理完了，这个前缀和也就没用了

        return ret;   // 返回总路径数
    }
}
