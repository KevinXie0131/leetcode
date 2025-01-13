package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q102_Binary_Tree_Level_Order_Traversal_1 {

    List<List<Integer>> resListRec = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return resListRec;
    }
    /**
     * DFS--递归方式
     */
    public void dfs(TreeNode root, int deep) {
        if (root == null) {
            return;
        }
        deep++;

        if (resListRec.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> sublist = new ArrayList<Integer>();
            resListRec.add(sublist);
        }

        resListRec.get(deep - 1).add(root.value);

        dfs(root.left,  deep);
        dfs(root.right, deep);
    }
    /**
     * 递归 （另一种形式）
     */
    public void dfs_1(TreeNode root, int deep) {
        if (root == null) {
            return;
        }

        if (resListRec.size() == deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> sublist = new ArrayList<Integer>();
            resListRec.add(sublist);
        }

        resListRec.get(deep).add(root.value);

        dfs(root.left,  deep + 1);
        dfs(root.right, deep + 1);
    }
}
