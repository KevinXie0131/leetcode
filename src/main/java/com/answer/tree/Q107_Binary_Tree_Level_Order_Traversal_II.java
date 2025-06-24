package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.*;

public class Q107_Binary_Tree_Level_Order_Traversal_II {
    /**
     * 二叉树的层序遍历 II
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
     */
    /**
     * 相对于102.二叉树的层序遍历，就是最后把result数组反转一下就可以了
     *
     * 解法：队列，迭代。
     * 在层序遍历的基础上, 将遍历结果翻转即可。
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
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

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
            list.add(0,sublist); // 在这里反转一下数组即可
        }
/*        List<List<Integer>> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i-- ) {
            result.add(list.get(i));
        }*/

        return list;
    }
    /**
     * 思路和模板相同, 对收集答案的方式做了优化, 最后不需要反转
     */
    public List<List<Integer>> levelOrderBottom_1(TreeNode root) {
        // 利用链表可以进行 O(1) 头部插入, 这样最后答案不需要再反转
        LinkedList<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.offer(root);
        }
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();
                temp.add(node.value);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            // 新遍历到的层插到头部, 这样就满足按照层次反序的要求
            ans.addFirst(temp);
        }

        return ans;
    }
}
