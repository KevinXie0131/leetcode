package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q515_Find_Largest_Value_in_Each_Tree_Row {
    /**
     * 在每个树行中找最大值
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
     * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
     */
    /**
     * 层序遍历，取每一层的最大值
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE; // 取每⼀层的最⼤值
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.value > max) {
                    max = cur.value;
                }
                //  max = Math.max(max, cur.val); // works too
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);

                size--;
            }
            list.add(max); // 把最⼤值放进数组
        }
        return list;
    }
}
