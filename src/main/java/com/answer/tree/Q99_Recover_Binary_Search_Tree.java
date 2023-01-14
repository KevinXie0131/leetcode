package com.answer.tree;

import com.template.TreeNode;

import java.util.*;

public class Q99_Recover_Binary_Search_Tree {

    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);

        TreeNode x= null;
        TreeNode y = null;

        for(int i =0 ;i < list.size()-1; i++){
            if(list.get(i).value > list.get(i + 1).value){
                y = list.get(i + 1);
                if(x==null) {
                    x = list.get(i);
                }
            }
        }

        if(x!= null && y != null){
            int temp = x.value;
            x.value = y.value;
            y.value = temp;
        }
    }

    public void dfs(TreeNode node, List<TreeNode> list) {
        if (node== null) return;

        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
    /**
     *
     */
    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree1(TreeNode root) {
        in_order(root);
        int tmp = firstNode.value;
        firstNode.value = secondNode.value;
        secondNode.value = tmp;
    }

    private void in_order(TreeNode root) {
        if (root == null) return;
        in_order(root.left);
        if (firstNode == null && preNode.value > root.value) {
            firstNode = preNode;
        }
        if (firstNode != null && preNode.value > root.value) {
            secondNode = root;
        }
        preNode = root;
        in_order(root.right);
    }
}
