package com.answer.tree;

import com.template.TreeNode;
import java.util.*;

public class Q144_Binary_Tree_Preorder_Traversal_1 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;
    }
    /**
     * 递归
     * 前序遍历
     */
    private void inorder(TreeNode root, List<Integer> result) {
        if(root == null) {
            return;
        }
        result.add(root.value);      // 中
        inorder(root.left, result);  // 左
        inorder(root.right, result); // 右
    }
}
