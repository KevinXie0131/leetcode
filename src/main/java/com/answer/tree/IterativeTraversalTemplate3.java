package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class IterativeTraversalTemplate3 {
    /**
     * 二叉树的层序遍历
     * 迭代方式--借助队列
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> sublist = new ArrayList<>();
            int size = queue.size();  // 这里一定要使用固定大小size，不要使用queue.size()，因为queue.size是不断变化的

            while (size > 0) {
                TreeNode cur = queue.poll();
                sublist.add(cur.value);

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);

                size--;
            }
            list.add(sublist);
        }
        return list;
    }
    /**
     * 递归
     */
    public List<List<Integer>> levelOrder_1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(list, root, 0);
        return list;
    }

    public void dfs(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (list.size() == level) {
            List<Integer> sublist = new ArrayList<>(); //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            list.add(sublist);
        }

        list.get(level).add(root.value);

        dfs(list, root.left,  level + 1);
        dfs(list , root.right, level + 1);
    }
}
