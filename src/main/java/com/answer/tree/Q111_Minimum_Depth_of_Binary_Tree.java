package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q111_Minimum_Depth_of_Binary_Tree {
    /**
     * 最⼩深度是从根节点到最近叶⼦节点的最短路径上的节点数量。注意是叶⼦节点。
     * 什么是叶⼦节点，左右孩⼦都为空的节点才是叶⼦节点
     *
     * 可以看出：求⼆叉树的最⼩深度和求⼆叉树的最⼤深度的差别主要在于处理左右孩⼦不为空的逻辑
     */
    public int minDepth0(TreeNode root) { // 递归
        if (root == null) {
            return 0;
        }
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
    /**
     * 递归 优化
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = minDepth(root.left);  // 左
        int right = minDepth(root.right);  // 右
        /**
         * 当⼀个左⼦树为空，右不为空，这时并不是最低点
         *    if (node.left == NULL && node.right != NULL) {
         *       return 1 + rightDepth;
         *    }
         * 当⼀个右⼦树为空，左不为空，这时并不是最低点
         *    if (node.left != NULL && node.right == NULL) {
         *       return 1 + leftDepth;
         *    }
         */
        if (root.left == null || root.right == null) {
            return left + right +1 ;
        }

        return Math.min(left, right) + 1;
    }
    /**
     * 递归 有最大初始值
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
            ans = Math.min(minDepth1(root.left), ans);
        }
        if(root.right != null) {
            ans = Math.min(minDepth1(root.right), ans);
        }
        return ans + 1;
    }
    /**
     * 迭代法 层序遍历
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
            depth++; // 记录最⼩深度
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    result = Math.min(result, depth); // 当左右孩⼦都为空的时候，说明是最低点的⼀层了，退出
                }

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
        }

        return result;
    }
    /**
     * 相对于 104.二叉树的最大深度 ，本题还也可以使用层序遍历的方式来解决，思路是一样的。
     * 需要注意的是，只有当左右孩子都为空的时候，才说明遍历的最低点了。如果其中一个孩子为空则不是最低点
     */
    public int minDepth_3(TreeNode root) { // 迭代
        int depth = 0;
        if (root == null) {
            return depth;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++; // 记录深度
            while (size > 0) {
                TreeNode cur = queue.poll();

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}
                if(cur.right == null && cur.left ==null){
                    return depth; // 当左右孩子都为空的时候，说明是最低点的一层了，直接返回最小深度
                }
                size--;
            }

        }
        return depth;
    }
}