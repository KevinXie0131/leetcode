package com.answer.tree;

import com.template.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Q222_Count_Complete_Tree_Nodes {

    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    /**
     *
     */
    public int countNodes_1(TreeNode root) {
        if (root == null){
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }
    /**
     *
     */
    public int countNodes_2(TreeNode root) {
        if (root == null){
            return 0;
        }

        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        int size = 0;
        while (!que.isEmpty()) {
            TreeNode tmpNode = que.poll();
            size++;

            if (tmpNode.left != null) que.offer(tmpNode.left);
            if (tmpNode.right != null) que.offer(tmpNode.right);
        }
        return size;
    }
    /**
     *
     */
    public int countNodes_3(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth) {// 左子树是满二叉树
            // 2^leftDepth其实是 （2^leftDepth - 1） + 1 ，左子树 + 根结点
            return (1 << leftDepth) + countNodes(root.right);
        } else {// 右子树是满二叉树
            return (1 << rightDepth) + countNodes(root.left);
        }

    }

    private int getDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            root = root.left;
            depth++;
        }
        return depth;
    }
}
