package com.answer.tree;

import java.util.*;

public class Q117_Populating_Next_Right_Pointers_in_Each_Node_II {
    /**
     * 填充每个节点的下一个右侧节点指针 II
     * 给定一个二叉树：
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
     * 初始状态下，所有 next 指针都被设置为 NULL 。
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.
     */
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
            Node dummy  = new Node(); // 虚拟头节点
            Node tail = dummy ;
            //遍历 cur 的当前层
            while(cur != null){
                if (cur.left != null) {
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right != null) {
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next; // cur 指针利用 next 不停的遍历当前层。
            }
            cur = dummy.next; // 当前层遍历完毕， 更新 cur 到下一层
        }
        return root;
    }
    /**
     *              1
     *          /       \
     *         p   ->    3
     *       /  \         \
     *      4 -> 5   ->    7
     * 层次遍历
     * 按照层的顺序遍历二叉树，在遍历第 i 层前，一定会遍历完第i − 1层
     * 可以在遍历每一层的时候修改这一层节点的 next 指针，这样就可以把每一层都组织成链表
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
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }
    /**
     * BFS: 只需要把每一层的节点，从左到右依次用 next 指针连接起来。
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
            nodePre.next = null; // 本层最后一个节点 next 指向 null // can be commented
        }
        return root;
    }
}
