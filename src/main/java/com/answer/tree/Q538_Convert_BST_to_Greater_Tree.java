package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q538_Convert_BST_to_Greater_Tree {

    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    public void dfs(TreeNode node){
        if(node == null) return;

        dfs(node.right);

        node.value += pre;
        pre = node.value;

        dfs(node.left);
    }
    /**
     *
     */
    int pre1 = 0;
    public TreeNode convertBST1(TreeNode root) {
        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.right;
            }
            TreeNode node = stack.pop();

            node.value += pre;
            pre1= node.value;

            cur = node.left;
        }
        return root;
    }
}
