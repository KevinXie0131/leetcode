package com.answer.tree;

import java.util.*;

public class Q429_Nary_Tree_Level_Order_Traversal {
    /**
     * N 叉树的层序遍历
     * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
     * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
     * Given an n-ary tree, return the level order traversal of its nodes' values.
     * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
     */
    /**
     * 这道题依旧是模板题，只不过一个节点有多个孩子了
     * 解法1：队列，迭代。
     */
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> sublist = new ArrayList<>();
            int size = queue.size();

            while (size > 0) {
                Node cur = queue.poll();
                sublist.add(cur.val);

                for (Node node : cur.children) { // 将节点孩⼦加⼊队列
                    queue.offer(node);
                }
/*              List<Node> children = poll.children;
                if (children == null || children.size() == 0) {
                    continue;
                }
                for (Node child : children) {
                    if (child != null) {
                        que.offerLast(child);
                }*/
                size--;
            }
            list.add(sublist);
        }
        return list;
    }
}

