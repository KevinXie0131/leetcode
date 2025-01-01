package com.answer.tree;

import com.template.TreeNode;

import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q637_Average_of_Levels_in_Binary_Tree {
    /**
     * 本题就是层序遍历的时候把一层求个总和再取一个均值。
     * 解法：队列，迭代。
     * 每次返回每层的最后一个字段即可。
     */
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.value; // 统计每⼀层的和

                if (cur.left != null) {queue.offer(cur.left);}
                if (cur.right != null) {queue.offer(cur.right);}

            }
            double result = sum/size; // 将每⼀层均值放进结果集
            list.add(sum/size);
        }


        return list;


    }
}
