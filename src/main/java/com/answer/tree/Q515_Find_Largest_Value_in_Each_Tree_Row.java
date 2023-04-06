package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q515_Find_Largest_Value_in_Each_Tree_Row {
    public List<Integer> largestValues(TreeNode root) {

        List<Integer> list = new ArrayList<Integer>();

        if (root == null) {
            return list;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE; // 取每⼀层的最⼤值
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.value > max) {
                    max = cur.value;
                }

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

                size--;
            }
            list.add(max); // 把最⼤值放进数组
        }


        return list;
    }
}
