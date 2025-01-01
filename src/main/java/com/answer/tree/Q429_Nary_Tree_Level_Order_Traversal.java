package com.answer.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q429_Nary_Tree_Level_Order_Traversal {
    /**
     * 这道题依旧是模板题，只不过一个节点有多个孩子了
     * 解法1：队列，迭代。
     */
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

