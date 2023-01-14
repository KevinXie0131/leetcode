package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q104_Maximum_Depth_of_Binary_Tree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     *
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    /**
     *
     */
    public int maxDepth2(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode cur = queue.poll();

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
            depth++;
        }
        return depth;
    }

    /**
     *
     */
    int depth = 0;

    public int maxDepth3(TreeNode root) {
        dfs(root, 0);
        return depth;
    }

    public void dfs(TreeNode root, int deep) {
        if (root == null) return;

        if (depth == deep) {
            depth++;
        }

        dfs(root.left,  deep + 1);
        dfs(root.right, deep + 1);
    }
}