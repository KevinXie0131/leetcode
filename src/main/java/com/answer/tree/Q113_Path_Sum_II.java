package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q113_Path_Sum_II {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    ArrayList<Integer> pathSum = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        dfs(root, targetSum);
        return result;
    }

    public void dfs(TreeNode root, int targetSum){
        if (root == null) return;

        pathSum.add(root.value);
        if (root.left == null && root.right == null) {
            if(pathSum.stream().reduce(0, (a, b) -> a + b) == targetSum){
                ArrayList newList = new ArrayList(pathSum);
                result.add(newList);
            }
        }
        if (root.left != null) {
            pathSum(root.left,  targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
        if (root.right != null) {
            pathSum(root.right,  targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
    }
}
