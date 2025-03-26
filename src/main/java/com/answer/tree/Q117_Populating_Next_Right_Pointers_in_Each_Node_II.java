package com.answer.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Q117_Populating_Next_Right_Pointers_in_Each_Node_II {
    /**
     * 这道题目说是二叉树，但116题目说是完整二叉树，其实没有任何差别，一样的代码一样的逻辑一样的味道
     * 与Q116的区别就是不一定是满二叉树
     * 相比于 Q116. 填充每个节点的下一个右侧节点指针，本题给出的二叉树没有满二叉树的限制，因此可能存在空节点，
     * 所以需要增加两个辅助指针，用来维护下一层的头结点和尾结点，其中，头结点用于切换，尾结点用于插入。
     */
    public Node connect0(Node root) {
        Node cur = root;
        while (cur != null) {
            // 两个辅助指针
            Node head = new Node(); // 虚拟头节点
            Node tail = head;

            Node p = cur;
            while(p != null){
                if (p.left != null) {
                    tail.next = p.left;
                    tail = tail.next;
                }
                if (p.right != null) {
                    tail.next = p.right;
                    tail = tail.next;
                }
                p = p.next;
            }
            cur = head.next;
        }
        return root;
    }
    /**
     *              1
     *          /       \
     *         p   ->    3
     *       /  \         \
     *      4 -> 5   ->    7
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