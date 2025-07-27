package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q637_Average_of_Levels_in_Binary_Tree {
    /**
     * 二叉树的层平均值
     * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10^-5 以内的答案可以被接受。
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
            int size = queue.size(); // 这里一定要使用固定大小size，不要使用que.size()，因为que.size是不断变化的
            double sum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.value; // 统计每⼀层的和

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);

            }
            double result = sum / size; // 将每⼀层均值放进结果集
            list.add(sum / size);
        }
        return list;
    }
    /**
     * 深度优先搜索
     * counts 用于存储二叉树的每一层的节点数，sums 用于存储二叉树的每一层的节点值之和。搜索过程中需要记录当前节点所在层，
     * 如果访问到的节点在第 i 层，则将 counts[i] 的值加 1，并将该节点的值加到 sums[i]。
     */
    public List<Double> averageOfLevels1(TreeNode root) {
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> sums = new ArrayList<Double>();
        dfs(root, 0, counts, sums);

        List<Double> averages = new ArrayList<Double>();

        int size = sums.size();
        for (int i = 0; i < size; i++) {
            averages.add(sums.get(i) / counts.get(i));
        }
        return averages;
    }

    public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
        if (root == null) return;

        if(counts.size() > level){
            counts.set(level, counts.get(level) + 1);
            sums.set(level, sums.get(level) + root.value);
        } else {
            sums.add(1.0 * root.value);
            counts.add(1);
        }

        dfs(root.left,  level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }
    /**
     * 递归 + 哈希表
     */
    Map<Integer,List<Integer>> result = new HashMap<>();

    public List<Double> averageOfLevels2(TreeNode root) {
        dfs(root, 0);
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            double val = 0;
            for (int j = 0; j < result.get(i).size(); j++) {
                val += result.get(i).get(j);
            }
            list.add(val / result.get(i).size());
        }
        return list;
    }

    public void dfs(TreeNode root, int index) {
        if (root == null) {
            return;
        }

        List<Integer> list = result.getOrDefault(index, new ArrayList<>());
        list.add(root.value);
        result.put(index, list);
        dfs(root.left, index + 1);
        dfs(root.right, index + 1);
    }
}
