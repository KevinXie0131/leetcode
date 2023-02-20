package com.learn.template;

import com.template.TreeNode;

public class Tree_Recursion {
    /**
     * 二叉树: DFS (递归)
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        // 根据题意补充代码
        dfs(root.left);
        dfs(root.right);
        return ans;
    }
}
