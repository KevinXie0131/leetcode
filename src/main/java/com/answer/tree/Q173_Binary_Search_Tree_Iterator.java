package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q173_Binary_Search_Tree_Iterator {
    /**
     * Approach 1: Flattening the BST
     * 本质是中序遍历
     * 参考Q94 Binary Tree Inorder Traversal
     */
    private int index;
    private ArrayList<Integer> list;

    public Q173_Binary_Search_Tree_Iterator(TreeNode root) {
        index = 0 ;
        list = new ArrayList<>();
        inorder(root, list);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    private void inorder(TreeNode root, ArrayList<Integer> list){
        if(root == null){
            return;
        }
        inorder(root.left, list);
        list.add(root.value);
        inorder(root.right, list);
    }
}
