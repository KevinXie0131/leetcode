package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q199_Binary_Tree_Right_Side_View_1 {

    List<Integer> list = new ArrayList<Integer>();

    public List<Integer> rightSideView(TreeNode root) {

        dfs(root, 0);

        return list;
    }
    /**
     * 递归
     */
    public void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }

        if (depth == list.size()) {
            list.add(root.value);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }
}
