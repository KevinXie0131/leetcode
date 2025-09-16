package com.answer.tree;

import com.template.TreeNode;

public class Q1372_Longest_ZigZag_Path_in_a_Binary_Tree {
    /**
     * 二叉树中的最长交错路径
     * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
     *  选择二叉树中 任意 节点和一个方向（左或者右）。
     *  如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
     *  改变前进方向：左变右或者右变左。
     *  重复第二步和第三步，直到你在树中无法继续移动。
     * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
     * 请你返回给定树中最长 交错路径 的长度。
     * You are given the root of a binary tree.
     * A ZigZag path for a binary tree is defined as follow:
     *  Choose any node in the binary tree and a direction (right or left).
     *  If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
     *  Change the direction from right to left or from left to right.
     *  Repeat the second and third steps until you can't move in the tree.
     * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
     * Return the longest ZigZag path contained in that tree.
     */
    /**
     * 交错路径指的是，从一个节点到另一个节点的路径上，每一步的方向都与前一步相反。例如，从父节点向左，下一步就必须向右，再下一步又必须向左，以此类推。
     * 需要注意的是，这条路径可以从树中的任意一个节点开始，也可以在任意一个节点结束。这意味着我们不能只从根节点开始搜索，而是要考虑以每个节点作为起点的最长交错路径。
     */
    int maxLen = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, true, 0);  // 起步向左
        dfs(root, false, 0);  // 起步向右
        return maxLen;
    }
    // 每次切换方向，长度加 1；如果尝试相同方向则重置长度（从 1 开始，从该节点新起步）。
    // 朝着 isLeft 方向走的最长交错路径的长度。 true 表示向左，false 表示向右。

    // 思考：「重置」为什么是把 len 变成 1 而不是 0？ 因为当前的点下传到它的子节点的时候已经走了一条长度为 1 的边。
    // 那么为什么 main 函数中传入的 len 值是 0 而不是 1 呢？ 因为 main 函数中的 root 是没有父亲节点的，所以当前已经走过的路为 0。
    public void dfs(TreeNode root, boolean isLeft, int len) {
        if (root == null) {
            return;
        }
        maxLen = Math.max(maxLen, len); // 更新最大路径长度
        if (isLeft) { // 如果当前方向是左，下一步应该是右
            dfs(root.right, false, len + 1); // 如果转向右，路径长度加1
            dfs(root.left, true, 1); // 如果继续向左，路径长度重置为1
        } else {
            dfs(root.left, true, len + 1); // 如果转向左，路径长度加1
            dfs(root.right, false, 1);  // 如果继续向右，路径长度重置为1
        }
    }
}
