package com.answer.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q429_Nary_Tree_Level_Order_Traversal_1 {
    List<List<Integer>> resListRec = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(Node root) {
        dfs(root, 0);
        return resListRec;
    }
    /**
     * 递归
     */
    public void dfs(Node root, int deep) {
        if (root == null) return;
        deep++;

        if (resListRec.size() < deep) {
            List<Integer> sublist = new ArrayList<Integer>();
            resListRec.add(sublist);
        }

        resListRec.get(deep-1).add(root.val);

        for (Node child : root.children) {
            dfs(child,  deep);
        }
    }
    /**
     * 递归(另一种形式)
     */
    public void dfs_1(Node root, int deep) {
        if (root == null) {
            return;
        }

        if (resListRec.size() == deep) {
            List<Integer> sublist = new ArrayList<Integer>();
            resListRec.add(sublist);
        }

        resListRec.get(deep).add(root.val);

        for (Node child : root.children) {
            dfs(child,  deep + 1);
        }
    }
}

