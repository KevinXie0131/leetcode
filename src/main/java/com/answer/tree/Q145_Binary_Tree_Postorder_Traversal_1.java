package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q145_Binary_Tree_Postorder_Traversal_1 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;

    }

    private void inorder(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }
        inorder(root.left, result);
        inorder(root.right, result);
        result.add(root.value);
    }
}
