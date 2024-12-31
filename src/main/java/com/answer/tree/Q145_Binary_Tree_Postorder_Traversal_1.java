package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q145_Binary_Tree_Postorder_Traversal_1 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;
    }
    /**
     * 递归
     * 中序遍历
     */
    private void inorder(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }
        inorder(root.left, result);  // 左
        inorder(root.right, result); // 中
        result.add(root.value);      // 右
    }
}
