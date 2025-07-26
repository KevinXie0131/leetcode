package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q559_Maximum_Depth_of_N_ary_Tree {
    /**
     * N 叉树的最大深度
     * 给定一个 N 叉树，找到其最大深度。
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
     * Given a n-ary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
     */
    /**
     * Recursion 递归法
     * refer to Q104_Maximum_Depth_of_Binary_Tree
     * 递归法，后序遍历求root节点的高度
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for(Node child : root.children) {
            int depth = maxDepth(child);
            if(depth > max){ // max = Math.max(max, depth);
                max = depth;  // depth = max (depth, maxDepth(root.children[i]));
            }
        }
        return max + 1; //中节点
    }
    /**
     * 递归 同上
     */
    public int maxDepth_0(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for(Node child : root.children) {
            depth = Math.max (depth, maxDepth(child));
        }
        return depth + 1;
    }
    /**
     * 前序遍历 / 自顶向下
     */
    private int ans;

    public int maxDepth_3(Node root) {
        dfs1(root, 0);
        return ans;
    }

    private void dfs1(Node node, int depth) {
        if (node == null) {
            return;
        }
        depth++;
        ans = Math.max(ans, depth);
        for(Node child : node.children) {
            dfs1(child, depth);
        }
    }
    /**
     * Iteration 迭代法
     * 迭代法，使用层序遍历
     */
    public int maxDepth_1(Node root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node cur = queue.poll();

                for(Node child : cur.children) {
                    if (child != null) {
                        queue.offer(child);
                    }
                }
                size--;
            }
            depth++; // 记录深度
        }
        return depth;
    }
}
