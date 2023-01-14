package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        targetSum -= root.value;
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        if (root.left != null) {
            boolean left = hasPathSum(root.left,  targetSum);
            if (left) {
                return true;
            }
        }
        if (root.right != null) {
            boolean right = hasPathSum(root.right,  targetSum);
            if (right) {
                return true;
            }
        }
        return false;

    }
    /**
     *
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) return false;

        return dfs(root, 0, targetSum);
    }

    public boolean dfs(TreeNode node, int sum, int targetSum){

        if (node == null)  return false;

        sum += node.value;
        if (node.left == null && node.right== null){
            return sum == targetSum;
        }

        boolean left = dfs(node.left, sum, targetSum);
        boolean right = dfs(node.right, sum, targetSum);
        return left || right;

    }
    /**
     *
     */
    ArrayList<Integer> pathSum = new ArrayList<>();

    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) return false;

        return dfs3(root, pathSum, targetSum);
    }

    public boolean dfs3(TreeNode node, ArrayList<Integer> pathSum, int targetSum){

        if (node == null)  return false;

        pathSum.add(node.value);
        if (node.left == null && node.right == null) {
            return pathSum.stream().reduce(0, (a, b) -> a + b) == targetSum;
        }

        boolean left = false;
        if (node.left != null) {
            left = dfs3(node.left, pathSum, targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
        boolean right = false;
        if (node.right != null) {
            right = dfs3(node.right, pathSum, targetSum);
            pathSum.remove(pathSum.size() - 1);
        }
        return left || right;

    }
    /**
     *
     */
    public boolean hasPathSum3(TreeNode root, int targetSum) {
        if (root == null) return false;

        Deque<TreeNode> stackNode = new ArrayDeque<>();
        Deque<Integer> stackVal = new ArrayDeque<>();
        stackNode.push(root);
        stackVal.push(root.value);

        while (!stackNode.isEmpty()) {
            int tmpVal = stackVal.pop();
            TreeNode tmpNode = stackNode.pop();

            if (tmpNode.left == null & tmpNode.right == null) {
                if (tmpVal == targetSum) return true;
            }
            if (tmpNode.right != null) {
                stackNode.push(tmpNode.right);
                stackVal.push(tmpNode.right.value + tmpVal);
            }
            if (tmpNode.left != null) {
                stackNode.push(tmpNode.left);
                stackVal.push(tmpNode.left.value + tmpVal);
            }
        }

        return false;
    }
}
