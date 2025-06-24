package com.answer.tree;

import com.template.TreeNode;

import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q637_Average_of_Levels_in_Binary_Tree {
    /**
     * 二叉树的层平均值
     * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
     * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
     */
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
