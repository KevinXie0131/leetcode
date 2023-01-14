package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q111_Minimum_Depth_of_Binary_Tree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;

        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (root.left == null || root.right == null) {
            return left + right +1 ;
        }

        return Math.min(left, right) + 1;
    }

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