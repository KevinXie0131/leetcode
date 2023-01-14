package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q102_Binary_Tree_Level_Order_Traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
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
            list.add(sublist);
        }


        return list;

    }
}
