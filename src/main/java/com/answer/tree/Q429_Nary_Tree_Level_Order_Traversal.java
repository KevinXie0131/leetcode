package com.answer.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q429_Nary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();

        if (root == null) {
            return list;
        }

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

                size--;
            }
            list.add(sublist);
        }


        return list;
    }
}

