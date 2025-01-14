package com.answer.tree;

import com.template.TreeNode;

public class Q129_Sum_Root_to_Leaf_Numbers {
    /**
     * 本题和113.路径总和II 是类似的思路
     */
    StringBuilder sb = new StringBuilder();
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root);
        return sum;
    }

    public void dfs(TreeNode root){
        if(root == null) return;

        sb.append(root.value);

        if(root.left == null && root.right == null) sum +=  Integer.valueOf(sb.toString());

        if(root.left != null) {
            dfs(root.left);
            sb.deleteCharAt(sb.length() -1 );
        }
        if(root.right != null) {
            dfs(root.right);
            sb.deleteCharAt(sb.length() -1 );
        }
    }
}
