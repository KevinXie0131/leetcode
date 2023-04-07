package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q113_Path_Sum_II {
    /**
     * 说明: 叶⼦节点是指没有⼦节点的节点
     */
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    ArrayList<Integer> pathSum = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        dfs(root, targetSum); // 把根节点放进路径
        return result;
    }

    public void dfs(TreeNode root, int targetSum){ // 递归函数不需要返回值，因为我们要遍历整个树
        if (root == null) return;

        pathSum.add(root.value);
        if (root.left == null && root.right == null) { // 遇到了叶⼦节点切找到了和为sum的路径
            if(pathSum.stream().reduce(0, (a, b) -> a + b) == targetSum){
                ArrayList newList = new ArrayList(pathSum);
                result.add(newList);
            }
        }
        if (root.left != null) { // 左 （空节点不遍历）
            pathSum(root.left,  targetSum); // 递归
            pathSum.remove(pathSum.size() - 1); // 回溯
        }
        if (root.right != null) { // 右 （空节点不遍历）
            pathSum(root.right,  targetSum);
            pathSum.remove(pathSum.size() - 1); // 回溯
        }
    }
}
