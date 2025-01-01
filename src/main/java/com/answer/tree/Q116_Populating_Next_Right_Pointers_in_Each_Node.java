package com.answer.tree;


import java.util.*;

public class Q116_Populating_Next_Right_Pointers_in_Each_Node {
    /**
     * 本题依然是层序遍历，只不过在单层遍历的时候记录一下本层的头部节点，然后在遍历的时候让前一个节点指向本节点就可以了
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
        if (root == null) {
            return root;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        Node nodePre = null;
        Node node = null;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    nodePre = queue.pop(); // 取出一层的头结点
                    node = nodePre;
                } else {
                    node = queue.pop();
                    nodePre.next = node; // 本层前一个节点next指向本节点
                    nodePre = nodePre.next;
                }
                if (node.left != null) {queue.offer(node.left);}
                if (node.right != null) {queue.offer(node.right);}

            }
        }
        return root;
    }
}