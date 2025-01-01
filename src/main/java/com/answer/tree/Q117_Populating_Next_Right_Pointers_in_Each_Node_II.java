package com.answer.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Q117_Populating_Next_Right_Pointers_in_Each_Node_II {
    /**
     * 这道题目说是二叉树，但116题目说是完整二叉树，其实没有任何差别，一样的代码一样的逻辑一样的味道
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();

                if (i < size - 1) {
                    cur.next = queue.peek();
                }

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}
            }
        }

        return root;
    }
    /**
     * 迭代
     */
    public Node connect_1(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node node = null;
            Node nodePre = null;

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    nodePre = queue.poll(); // 取出本层头一个节点
                    node = nodePre;
                } else {
                    node = queue.poll();
                    nodePre.next = node; // 本层前一个节点 next 指向当前节点
                    nodePre = nodePre.next;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            nodePre.next = null; // 本层最后一个节点 next 指向 null
        }
        return root;
    }
}