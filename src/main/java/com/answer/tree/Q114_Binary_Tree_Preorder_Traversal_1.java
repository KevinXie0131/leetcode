package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q114_Binary_Tree_Preorder_Traversal_1 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;

    }

    private void inorder(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }
        result.add(root.value);
        inorder(root.left, result);
        inorder(root.right, result);
    }
}
