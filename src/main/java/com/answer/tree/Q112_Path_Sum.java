package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q112_Path_Sum {
    /**
     * 路径总和
     * 说明: 叶⼦节点是指没有⼦节点的节点
     *
     * 递归
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        /**
         * 不要去累加然后判断是否等于目标和，那么代码比较麻烦，可以用递减，让计数器count初始为目标和，然后每次减去遍历路径节点上的数值
         */
        targetSum -= root.value;
        if (root.left == null && root.right == null) { // 遇到叶⼦节点，并且计数为0
            /**
             * targetSum == 0，同时到了叶子节点的话，说明找到了目标和。
             * 如果遍历到了叶子节点，targetSum，就是没找到。
             */
            return targetSum == 0;
        }
        if (root.left != null) {
            boolean left = hasPathSum(root.left,  targetSum);
            if (left) {
                return true; // 递归函数是有返回值的，如果递归函数返回true，说明找到了合适的路径，应该立刻返回。
            }
        }
        if (root.right != null) {
            boolean right = hasPathSum(root.right,  targetSum);
            if (right) {
                return true; // 已经找到
            }
        }
        return false;

    }
    /**
     * 递归
     *
     * 如果需要搜索整颗⼆叉树，那么递归函数就不要返回值，如果要搜索其中⼀条符合条件的路径，
     * 递归函数就需要返回值，因为遇到符合条件的路径了就要及时返回
     *
     * 本题我们要找⼀条符合条件的路径，所以递归函数需要返回值，及时返回
     * 遍历的路线，并不要遍历整棵树，所以递归函数需要返回值，可以⽤bool类型表⽰。
     */
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) return false;

        return dfs(root, 0, targetSum);
    }

    public boolean dfs(TreeNode node, int sum, int targetSum){ // 注意函数的返回类型
        if (node == null)  return false;

        if (node.left == null && node.right== null){
            return node.value == targetSum;
        }

        boolean left = dfs(node.left, sum, targetSum - node.value); // 隐藏回溯
        boolean right = dfs(node.right, sum, targetSum - node.value);
        return left || right;
        /**
         * 为了把回溯的过程体现出来
         * if (cur->left) { // 左
         *      count -= cur->left->val; // 递归，处理节点;
         *      if (traversal(cur->left, count)) {
         *          return true;
         *      }
         *      count += cur->left->val; // 回溯，撤销处理结果
         * }
         */
    }
    /**
     * 回溯
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
            pathSum.remove(pathSum.size() - 1); // 回溯
        }
        boolean right = false;
        if (node.right != null) {
            right = dfs3(node.right, pathSum, targetSum);
            pathSum.remove(pathSum.size() - 1); // 回溯
        }
        return left || right;

    }
    /**
     * 迭代
     */
    public boolean hasPathSum3(TreeNode root, int targetSum) {
        if (root == null) return false;
       // 此时栈⾥要放的是<节点指针，路径数值>
        Deque<TreeNode> stackNode = new ArrayDeque<>();
        Deque<Integer> stackVal = new ArrayDeque<>();
        stackNode.push(root);
        stackVal.push(root.value);

        while (!stackNode.isEmpty()) {
            int tmpVal = stackVal.pop();
            TreeNode tmpNode = stackNode.pop();

            if (tmpNode.left == null & tmpNode.right == null) { // 如果该节点是叶⼦节点了，同时该节点的路径数值等于sum，那么就返回true
                if (tmpVal == targetSum) return true;
            }
            // 右节点，压进去⼀个节点的时候，将该节点的路径数值也记录下来
            if (tmpNode.right != null) {
                stackNode.push(tmpNode.right);
                stackVal.push(tmpNode.right.value + tmpVal);
            }
            // 左节点，压进去⼀个节点的时候，将该节点的路径数值也记录下来
            if (tmpNode.left != null) {
                stackNode.push(tmpNode.left);
                stackVal.push(tmpNode.left.value + tmpVal);
            }
        }

        return false;
    }
}
