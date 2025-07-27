package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q515_Find_Largest_Value_in_Each_Tree_Row_1 {
    List<Integer> resListRec = new ArrayList<Integer>();

    public List<Integer> largestValues(TreeNode root) {
        dfs(root, 0);
        return resListRec;
    }
    /**
     * 递归
     */
    public void dfs(TreeNode root, int deep) {
        if (root == null) return;

        if (resListRec.size() == deep) {
            resListRec.add(Integer.MIN_VALUE);
        }

        int max = resListRec.get(deep);
        resListRec.set(deep, Math.max(max, root.value));
    //    int max = Math.max(resListRec.get(deep), root.value);
    //    resListRec.set(deep , max);

        dfs(root.left,  deep + 1);
        dfs(root.right, deep + 1);
    }
}
