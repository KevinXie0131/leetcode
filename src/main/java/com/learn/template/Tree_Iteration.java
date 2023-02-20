package com.learn.template;

import com.template.TreeNode;

import java.util.Stack;

public class Tree_Iteration {
    /**
     * 二叉树: DFS (迭代)
     */
    public int dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int ans = 0;

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            // 根据题意补充代码
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return ans;
    }
}
