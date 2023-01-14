package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q94_Binary_Tree_Inorder_Traversal_1 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;

    }

    private void inorder(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.value);
        inorder(root.right, result);
    }
}
