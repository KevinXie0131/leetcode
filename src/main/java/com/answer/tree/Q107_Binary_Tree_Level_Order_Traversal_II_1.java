package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q107_Binary_Tree_Level_Order_Traversal_II_1 {

    List<List<Integer>> resListRec = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        dfs(root, 0);
        Collections.reverse(resListRec); // 在这⾥反转⼀下数组即可
        return resListRec;
    }
    /**
     * 递归
     */
    public void dfs(TreeNode root, int deep) {
        if (root == null){
            return;
        }
        deep++;

        if (resListRec.size() < deep) {
            List<Integer> sublist = new ArrayList<>();
            resListRec.add(sublist);
        }

        resListRec.get(deep - 1).add(root.value);

        dfs(root.left,  deep);
        dfs(root.right, deep);
    }
    /**
     * 递归 （另一种形式）
     */
    public void dfs_1(TreeNode root, int level) {
        if (root == null){
            return;
        }

        if (resListRec.size() == level) {
            List<Integer> sublist = new ArrayList<>();
            resListRec.add(sublist);
        }

        resListRec.get(level).add(root.value);

        dfs(root.left,  level + 1);
        dfs(root.right, level + 1);
    }
    /**
     * 无需进行反转, 只要改了这两处就可以了
     *    代码变为ans.get(ans.size() - 1 - level).add(root.val);
     *    代码改成ans.add(0，new ArrayList<>());
     */
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {
        dfs_2(root, 0);
        return resListRec;
    }

    public void dfs_2(TreeNode root, int level) {
        if (root == null){
            return;
        }

        if (resListRec.size() == level) {
            List<Integer> sublist = new ArrayList<>();
            resListRec.add(0, sublist);   // 当前层数还没有元素，先 new 一个空的列表
        }

        resListRec.get(resListRec.size() - 1 - level).add(root.value);   // 当前值加入

        dfs_2(root.left,  level + 1);
        dfs_2(root.right, level + 1);
    }
}
