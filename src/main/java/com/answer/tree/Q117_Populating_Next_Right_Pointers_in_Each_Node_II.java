package com.answer.tree;


import java.util.ArrayDeque;
import java.util.Deque;

public class Q117_Populating_Next_Right_Pointers_in_Each_Node_II {
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
}