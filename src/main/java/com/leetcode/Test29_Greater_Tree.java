package com.leetcode;

import com.template.TreeNode;

import java.util.*;

public class Test29_Greater_Tree {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        root1.setLeft(node2);
        root1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

        TreeNode root = convertBST(root1);
        System.out.println(root);
    }

   static int pre = 0;
   static public TreeNode convertBST(TreeNode root) {
       TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.right;
            }
            TreeNode node = stack.pop();

            node.value += pre;
            pre= node.value;

            cur = node.left;
        }
        return root;
    }
}
