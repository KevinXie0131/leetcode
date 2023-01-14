package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.*;

public class Q107_Binary_Tree_Level_Order_Traversal_II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
            list.add(0,sublist);
        }


        return list;
    }
}
