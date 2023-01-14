package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q543_Diameter_of_Binary_Tree {

    int result = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return result;
    }

    public int dfs(TreeNode root){
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);
        result = Math.max(result, left + right);
        return Math.max(left,  right) + 1;
    }
}
