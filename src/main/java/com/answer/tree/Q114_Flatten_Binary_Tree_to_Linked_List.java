package com.answer.tree;

import com.template.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q114_Flatten_Binary_Tree_to_Linked_List {

    public void flatten(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        inorder(root, result);

        TreeNode cur = root;
        for(int i = 1; i < result.size(); i++){
            cur.left = null;
            cur.right = result.get(i);
            cur = cur.right;
        }
    }

    void inorder(TreeNode root, List<TreeNode> result) {
        if(root == null) {
            return;
        }
        result.add(root);      // 中
        inorder(root.left, result);  // 左
        inorder(root.right, result); // 右
    }
}
