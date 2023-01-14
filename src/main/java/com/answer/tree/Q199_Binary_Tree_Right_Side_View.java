package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.*;
import java.util.Deque;
import java.util.List;

public class Q199_Binary_Tree_Right_Side_View {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                TreeNode cur = queue.poll();
                if (size == 1) {
                    list.add(cur.value);
                }

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;

            }

        }

        return list;
    }
}
