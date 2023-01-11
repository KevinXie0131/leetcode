package com.leetcode;

import com.template.TreeNode;

import java.util.*;

public class Test30_Recover_Tree {
    public static void main(String[] args) {
        /**
         * [3,1,4,null,null,2]
         */
        TreeNode root1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
        root1.setLeft(node2);
        root1.setRight(node3);
        node3.setLeft(node4);

        TreeNode root5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(2);
        root5.setLeft(node6);
        node6.setRight(node7);

        recoverTree(root5);
        System.out.println(root5);
    }

    static public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);

        TreeNode x= null;
        TreeNode y = null;
        /**
         * 1 -> 3 -> 2 -> 4
         *
         * 3 -> 2 -> 1
         */
        for(int i =0 ;i < list.size()-1; i++){
            if(list.get(i).value > list.get(i + 1).value){
                y = list.get(i + 1);
                if(x == null) {
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

    static public void dfs(TreeNode node, List<TreeNode> list) {
        if (node== null) return;

        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
}
