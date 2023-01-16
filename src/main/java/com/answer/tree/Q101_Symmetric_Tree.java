package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q101_Symmetric_Tree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.value != right.value) {
            return false;
        }

        boolean isLeft = dfs(left.left, right.right);
        boolean isRight = dfs(left.right, right.left);

        return isLeft && isRight;
    }

    /**
     *
     */
    public boolean isSymmetric_1c(TreeNode root) {

        if (root == null) {
            return true;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root.left);
        queue.offerLast(root.right);
        while (!queue.isEmpty()) {

            TreeNode left = queue.pollFirst();
            TreeNode right = queue.pollLast();

            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.value != right.value) {
                return false;
            }
            queue.offerFirst(left.left);
            queue.offerFirst(left.right);
            queue.offerLast(right.right);
            queue.offerLast(right.left);
        }

        return true;
    }
}
