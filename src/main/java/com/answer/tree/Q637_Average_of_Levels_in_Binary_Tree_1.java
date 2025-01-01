package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q637_Average_of_Levels_in_Binary_Tree_1 {

    List<Double> resListRec = new ArrayList<>();
    List<Double> sum = new ArrayList<>();
    List<Integer> count = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        dfs(root, 0);
        for (int i = 0; i < resListRec.size(); i++) {
            resListRec.set(i, sum.get(i)/count.get(i));
        }
        return resListRec;
    }
    // 递归
    public void dfs(TreeNode root, int deep) {
        if (root == null) {
            return;
        }
        deep++;

        if (resListRec.size() < deep) {
            count.add(0);
            sum.add(1.0 * 0);
            resListRec.add(1.0 * 0);
        }
        double s = sum.get(deep-1);
        int c = count.get(deep-1);
        sum.set(deep-1, s + root.value);
        count.set(deep-1, c + 1);

        dfs(root.left,  deep);
        dfs(root.right, deep);
    }
    /**
     *
     */
    public void dfs_1(TreeNode root, int deep) {
        if (root == null) {
            return;
        }

        if (resListRec.size() == deep) {
            count.add(0);
            sum.add(1.0 * 0);
            resListRec.add(1.0 * 0);
        }
        double s = sum.get(deep);
        int c = count.get(deep);
        sum.set(deep, s + root.value);
        count.set(deep, c + 1);

        dfs(root.left,  deep + 1);
        dfs(root.right, deep + 1);
    }
}
