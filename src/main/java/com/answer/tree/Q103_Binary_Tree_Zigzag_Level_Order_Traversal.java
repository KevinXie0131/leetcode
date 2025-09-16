package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q103_Binary_Tree_Zigzag_Level_Order_Traversal {
    /**
     * 二叉树的锯齿形层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
     * (i.e., from left to right, then right to left for the next level and alternate between).
     * 示例 1：
     *      3
     *    /   \
     *   9     20
     *        /  \
     *       15   7
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[20,9],[15,7]]
     */
    /**
     * 递归
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            List<Integer> sublist = new LinkedList<>();
            res.add(sublist);
        }
        if(level % 2 == 0) {
            res.get(level).add(root.value);
        } else {
            ((LinkedList<Integer>)res.get(level)).addFirst(root.value);
        }

        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }
    /**
     * 迭代
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> sublist = new ArrayList<>();
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                sublist.add(cur.value);

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                size--;
            }
            if(list.size() % 2 == 1) {
                Collections.reverse(sublist);
            }
            list.add(sublist);
        }
        return list;
    }
}
