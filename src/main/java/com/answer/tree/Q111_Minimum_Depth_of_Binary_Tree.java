package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q111_Minimum_Depth_of_Binary_Tree {
    /**
     * 最⼩深度是从根节点到最近叶⼦节点的最短路径上的节点数量。，注意是叶⼦节点。
     * 什么是叶⼦节点，左右孩⼦都为空的节点才是叶⼦节点
     *
     * 可以看出：求⼆叉树的最⼩深度和求⼆叉树的最⼤深度的差别主要在于处理左右孩⼦不为空的逻辑
     */
    public int minDepth0(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = minDepth(root.left); // 左
        int rightDepth = minDepth(root.right); // 右
        // 中
        // 当⼀个左⼦树为空，右不为空，这时并不是最低点
        if (root.left == null && root.right != null) {
            return 1 + rightDepth;
        }
        // 当⼀个右⼦树为空，左不为空，这时并不是最低点
        if (root.left != null && root.right == null) {
            return 1 + leftDepth;
        }

        int result = 1 + Math.min(leftDepth, rightDepth);

        return result;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;

        int left = minDepth(root.left);  // 左
        int right = minDepth(root.right);  // 右
        /**
         * 当⼀个左⼦树为空，右不为空，这时并不是最低点
         *    if (node->left == NULL && node->right != NULL) {
         *       return 1 + rightDepth;
         *    }
         * 当⼀个右⼦树为空，左不为空，这时并不是最低点
         *    if (node->left != NULL && node->right == NULL) {
         *       return 1 + leftDepth;
         *    }
         */
        if (root.left == null || root.right == null) {
            return left + right +1 ;
        }

        return Math.min(left, right) + 1;
    }

    /**
     *
     */
    public int minDepth1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        int ans = Integer.MAX_VALUE;
        if(root.left != null) {
            ans = Math.min(minDepth(root.left), ans);
        }
        if(root.right != null) {
            ans = Math.min(minDepth(root.right), ans);
        }
        return ans + 1;
    }

    /**
     *
     */
    public int minDepth2(TreeNode root) {
        int result = Integer.MAX_VALUE;
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            depth++;
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    result = Math.min(result, depth);
                }

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
        }


        return result;
    }
}